package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar implements iObservable
{
	private JButton m_startButton;
	private JButton m_stopButton;
	private JButton m_timeButton;
	private JPanel m_simulationPanel;

	private void initHandlers()
	{
		m_startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				m_startButton.setEnabled(false);
				m_stopButton.setEnabled(true);
				Listener.getInstance().addAction(ToolBar.this, Actions.Start);
			}
		});

		m_stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				m_startButton.setEnabled(true);
				m_stopButton.setEnabled(false);
				Listener.getInstance().addAction(ToolBar.this, Actions.Stop);
			}
		});
	}

	public ToolBar()
	{
		m_stopButton.setEnabled(false);
		initHandlers();
		Listener.getInstance().addObservable(this);
	}

	@Override
	public void newAction(Actions action)
	{
		switch(action)
		{
			case Start:
				m_stopButton.setEnabled(true);
				m_startButton.setEnabled(false);
				break;
			case Stop:
				m_stopButton.setEnabled(false);
				m_startButton.setEnabled(true);
				break;
		}
	}

	@Override
	public void newAction(Actions action, Object data) {

	}
}
