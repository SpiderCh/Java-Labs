package lab1;

import java.applet.Applet;
import java.awt.*;
import utils.Pair;

enum LoadStatus
{
	WAITING,
	LOADING,
	DONE,
	ERROR
}

public class MainWindow extends Applet implements WindowActions {
	private Pair<Integer, Integer> m_mouse_position;
	private Pair<Integer, Integer> m_window_size;
    private MouseListener m_mouse_input;
    private KeyboardListener m_keyboard_input;
    private String m_message_text;
    private AppType m_type;

    private Image m_image;
    private Image m_offscreen_image;
    private String m_image_URL;
	private LoadStatus m_image_status;

    public MainWindow()
    {
	    m_mouse_position = new Pair<Integer, Integer>(10, 10);
	    m_window_size = new Pair<Integer, Integer>(640, 480);

        m_mouse_input = new MouseListener(this);
        m_keyboard_input = new KeyboardListener(this);
        this.addMouseListener(m_mouse_input);
        this.addKeyListener(m_keyboard_input);

        m_image = null;
        m_image_URL = new String("ico_linux.gif");
        m_message_text = new String("Loading pic ...");

        m_type = AppType.APPLET;
	    m_image_status = LoadStatus.WAITING;
    }

    public void set_app_type(AppType type) {
        m_type = type;
    }

    @Override
    public void init()
    {
	    if(m_type == AppType.APPLET) {
		    get_applet_parameters();
	    }

        try{
            if(m_type == AppType.APPLET) {
                m_image = getImage(getDocumentBase(), m_image_URL);
            }else {
                m_image = getToolkit().getImage(m_image_URL);
            }
        } catch (SecurityException e) {
            System.out.println("Cannot load pic. Permission Denied...");
            e.printStackTrace();
        }
	    m_image_status = LoadStatus.LOADING;
        if(m_image == null) {
            System.out.println("Cannot load pic...");
            m_message_text = "Cannot load pic... " + m_image_URL;
	        m_image_status = LoadStatus.ERROR;
        }
	    super.resize(m_window_size.first, m_window_size.second);
    }

	public void resize(int width, int height)
	{
		m_window_size.first = width;
		m_window_size.second = height;
	}

    public void paint(Graphics graphics)
    {
        int x = m_mouse_position.first;
        int y = m_mouse_position.second;
	    graphics.drawString(m_message_text, x, y);

        if((m_image_status != LoadStatus.ERROR ||
            m_image_status != LoadStatus.DONE) &&
		    m_offscreen_image == null) {
            m_offscreen_image = createImage(getSize().width, getSize().height);
        }

        if(m_image_status == LoadStatus.LOADING &&
		   m_offscreen_image != null){
            Graphics g = m_offscreen_image.getGraphics();
            g.drawImage(m_image, 0, 0, this);
        }

        if(m_image_status == LoadStatus.DONE) {
            graphics.drawImage(m_image, x, y + 10, null);
            m_offscreen_image = null;
        }
    }

	public Pair<Integer, Integer> get_window_size()
	{
		return m_window_size;
	}

    @Override
    public Pair<Integer, Integer> get_point()
    {
        return m_mouse_position;
    }

    @Override
    public void repaint()
    {
        super.repaint();
    }

    @Override
    public boolean imageUpdate(Image img, int info_flags, int x, int y,int w, int h)
    {
        if(info_flags == ALLBITS) {
            m_message_text = "Pic loaded...";
	        m_image_status = LoadStatus.DONE;
            repaint();
        }
        return m_image_status != LoadStatus.DONE;
    }

	private void get_applet_parameters()
	{
		String step = null;
		String width = null;
		String height = null;
		String image_URL = null;


		step = getParameter("step");
		width = getParameter("width");
		height = getParameter("height");
		image_URL = getParameter("image");

		if(step != null) {
			m_keyboard_input.set_step(Integer.parseInt(step));
		}

		if(image_URL != null) {
			m_image_URL = image_URL;
		}

		if(width != null && height != null) {
			m_window_size.first = Integer.parseInt(width);
			m_window_size.second = Integer.parseInt(height);
		}
	}

	public void set_parameters(String[] params)
	{
		if(params.length < 2) {
			return;
		}

		int size = params.length;
		for(int i = 0; i != size; ++i) {
			if(params[i].equals("--image")) {
				m_image_URL = params[i + 1];
				++i;
				continue;
			}
			if(params[i].equals("--size")) {
				m_window_size.first = Integer.parseInt(params[i + 1]);
				m_window_size.second = Integer.parseInt(params[i + 2]);
				i += 2;
				continue;
			}
			if(params[i].equals("--step")) {
				m_keyboard_input.set_step(Integer.parseInt(params[i + 1]));
				++i;
				continue;
			}
		}
	}
}
