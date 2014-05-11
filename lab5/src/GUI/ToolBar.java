package GUI;

import Signal.SignalType;
import Signal.Signal;
import Listener.Listener;
import Listener.Message;
import Listener.iObservable;
import Listener.iListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar implements iObservable//, ChangeListener
{
	private JButton   m_startButton;
	private JButton   m_stopButton;
	private JButton   m_timeButton;
	private JPanel    m_controlBar;
	private JLabel    m_periodValue;
	private iListener m_listener;
	private boolean   m_showTime;
    private boolean   m_pauseEnabled;

	private void subscribe()
	{
		m_listener.subscribe(this, SignalType.SIGNAL);
		m_listener.subscribe(this, SignalType.INFO);
	}

	private void initHandlers()
	{
		m_startButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				m_startButton.setEnabled(false);
				m_stopButton.setEnabled(true);
				m_listener.signal(ToolBar.this,
						new Message(SignalType.SIGNAL, Signal.Start));
			}
		});

		m_stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(m_pauseEnabled) {
                    m_listener.signal(ToolBar.this, new Message(SignalType.SIGNAL, Signal.Pause, null));
                } else {
                    m_startButton.setEnabled(true);
                    m_stopButton.setEnabled(false);
                    m_listener.signal(ToolBar.this,
                            new Message(SignalType.SIGNAL, Signal.Stop));
                }
            }
        });

		m_timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (m_showTime) {
                    m_listener.signal(ToolBar.this,
                            new Message(SignalType.INFO, Signal.HideTime));
                } else {
                    m_listener.signal(ToolBar.this,
                            new Message(SignalType.INFO, Signal.ShowTime));
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
        m_pauseEnabled = false;
		initHandlers();
		subscribe();
	}

	public JPanel getContentPane()
	{
		return m_controlBar;
	}

	@Override
	public void signal(Message mess)
	{
		switch(mess.m_action)
		{
			case Start:
				m_stopButton.setEnabled(true);
				m_startButton.setEnabled(false);
				break;
			case Stop:
            case ForceStop:
				m_stopButton.setEnabled(false);
				m_startButton.setEnabled(true);
				break;
			case ShowTime:
				m_showTime = true;
				break;
			case HideTime:
				m_showTime = false;
				break;
            case ShowSimulationInfo:
                m_pauseEnabled = true;
                break;
            case HideSimulationInfo:
                m_pauseEnabled = false;
                break;
		}
	}
}
