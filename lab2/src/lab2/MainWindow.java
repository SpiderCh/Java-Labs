package lab2;

import utils.Pair;

import java.applet.Applet;
import java.awt.*;
import java.util.Timer;

public class MainWindow extends Applet implements WindowActions {
    private Pair<Integer, Integer> m_windowSize;
    private Timer m_timer;
    private KeyboardListener m_keyboard_input;
    private Habitat m_office;
    private boolean m_running;

    public MainWindow()
    {
        m_office = new Habitat(this.getWidth(), this.getHeight());
        m_running = false;
        m_keyboard_input = new KeyboardListener(this);
        this.addKeyListener(m_keyboard_input);
        m_windowSize = new Pair<Integer, Integer>(0, 0);
    }

    public void set_size(int width, int height)
    {
        m_windowSize.first = width;
        m_windowSize.second = height;
        m_office.set_size(m_windowSize);
    }

    @Override
    public void paint(Graphics graphics)
    {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, m_windowSize.first, m_windowSize.second);
        m_office.paint(graphics);
    }

    @Override
    public Pair<Integer, Integer> get_point() {
        return null;
    }

    @Override
    public void update(int time)
    {
        m_office.update(time);
        repaint();
    }

    @Override
    public void start_timer()
    {
        if(!m_running) {
            m_timer = new Timer();
            m_timer.schedule(new Updater(this), 1, 1000);
            m_office.start();
            m_running = true;
        }
    }

    @Override
    public void stop_timer()
    {
        if(m_running) {
            m_timer.cancel();
            m_timer = null;
            m_office.stop();
            m_running = false;
            repaint();
        }
    }

    @Override
    public void show_time()
    {
        m_office.switch_show_time();
        repaint();
    }
}
