package personal;

import utils.Pair;
import java.awt.*;

public abstract class Human implements Behaviour {
    protected Pair<Integer, Integer> m_position;
    protected int m_id;
    protected String m_image_path;
    protected Image m_image;

    public Human()
    {
        m_position = new Pair<Integer, Integer>(0, 0);
        m_id = -1;
        m_image_path = null;
        m_image = null;
    }

    public Human(int id)
    {
        m_position = new Pair<Integer, Integer>(0, 0);
        m_id = id;
        m_image_path = null;
        m_image = null;
    }

    public Human(int x, int y)
    {
        m_position = new Pair<Integer, Integer>(x, y);
        m_id = -1;
        m_image_path = null;
        m_image = null;
    }

    public Human(int id, int x, int y)
    {
        m_position = new Pair<Integer, Integer>(x, y);
        m_id = id;
        m_image_path = null;
        m_image = null;
    }

    public void update_position(int x, int y)
    {
        m_position.first  = x;
        m_position.second = y;
    }

    public void set_image(Image image)
    {
        m_image = image;
    }

    @Override
    public void paint(Graphics graphics)
    {
        graphics.drawImage(m_image, m_position.first, m_position.second, null);
    }

    @Override
    public abstract void update(int time);
}
