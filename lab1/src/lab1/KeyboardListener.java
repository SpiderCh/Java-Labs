package lab1;

import utils.Pair;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    private WindowActions m_window;
    private int m_step;

    public KeyboardListener(WindowActions window)
    {
        m_window = window;
        m_step = 5;
    }

    public void set_step(int step)
    {
        m_step = step;
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        int key_code = event.getKeyCode();
	    Pair<Integer, Integer> point = m_window.get_point();
        boolean key_pressed = true;

        switch(key_code) {
            case KeyEvent.VK_UP:
	            point.second -= m_step;
                break;
            case KeyEvent.VK_DOWN:
	            point.second += m_step;
                break;
            case KeyEvent.VK_LEFT:
	            point.first -= m_step;
                break;
            case KeyEvent.VK_RIGHT:
	            point.first += m_step;
                break;
            default:
                key_pressed = false;
        }

        if(key_pressed){
            m_window.repaint();
        }
    }
}
