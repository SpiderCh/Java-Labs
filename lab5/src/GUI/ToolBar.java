package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;
import Listener.iListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar implements iObservable
{
	private JButton m_startButton;
	private JButton m_stopButton;
	private JButton m_timeButton;
	private JPanel m_simulationPanel;
	private iListener m_listener;
	private boolean m_showTime;

	private void subscribe()
	{
		m_listener.subscribe(this, Actions.Stop);
		m_listener.subscribe(this, Actions.Start);
		m_listener.subscribe(this, Actions.ShowTime);
		m_listener.subscribe(this, Actions.HideTime);
	}

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

		m_timeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(m_showTime) {
					m_listener.addAction(ToolBar.this, Actions.HideTime);
				} else {
					m_listener.addAction(ToolBar.this, Actions.ShowTime);
				}
				m_showTime = !m_showTime;
			}
		});
	}

	public ToolBar()
	{
		m_listener = Listener.getInstance();
		m_stopButton.setEnabled(false);
		m_showTime = true;
		initHandlers();
		subscribe();
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
			case ShowTime:
				m_showTime = true;
				break;
			case HideTime:
				m_showTime = false;
				break;
		}
	}

	@Override
	public void newAction(Actions action, Object data) {}
}
