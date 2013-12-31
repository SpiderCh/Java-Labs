package lab2;

import utils.Pair;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    private WindowActions m_window;

    public KeyboardListener(WindowActions window)
    {
        m_window = window;
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        int key_code = event.getKeyCode();

        switch(key_code) {
            case KeyEvent.VK_R:
            case KeyEvent.VK_B:
                m_window.start_timer();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_E:
                m_window.stop_timer();
                break;
            case KeyEvent.VK_T:
                m_window.show_time();
                break;
        }
    }
}
