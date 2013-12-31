package lab1;

import utils.Pair;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {
    private WindowActions m_window;

	public MouseListener(WindowActions window)
	{
        m_window = window;
	}

    @Override
	public void mousePressed(MouseEvent event)
	{
		Pair<Integer, Integer> point = m_window.get_point();
        point.first = event.getX();
		point.second = event.getY();
        m_window.repaint();
	}
}
