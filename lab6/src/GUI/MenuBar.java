package GUI;

import Signal.SignalType;
import Signal.Signal;
import Listener.Listener;
import Listener.Message;
import Listener.iListener;
import Listener.iObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements iObservable
{
	private iListener m_listener;
	private JMenuItem m_startStopItem;
	private JCheckBoxMenuItem m_changeShowTimeItem;
	private JCheckBoxMenuItem m_changeShowInfoItem;
	private boolean m_startEnabled;
	private boolean m_pauseEnabled;

	private void subscribe()
	{
		m_listener.subscribe(this, SignalType.SIGNAL);
		m_listener.subscribe(this, SignalType.INFO);
	}

	private void initMenuBar()
	{
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						Listener.getInstance().stopQueue();
						try {
							Listener.getInstance().join();
						} catch (InterruptedException e1) {
					/*
					 * Add some kind of usefull output
					 */
						}
						System.exit(0);
					}
				}
		);
		fileMenu.add(exitItem);

		JMenu simulation = new JMenu("Simulation");
		m_startStopItem = new JMenuItem("Start");
		m_startStopItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				if(m_startEnabled) {
					if(m_pauseEnabled) {
						m_listener.signal(MenuBar.this, new Message(SignalType.SIGNAL, Signal.Pause));
					} else {
						m_startEnabled = false;
						m_startStopItem.setText("Start");
						m_listener.signal(MenuBar.this, new Message(SignalType.SIGNAL, Signal.Stop));
					}
				} else {
					m_startEnabled = true;
					m_startStopItem.setText("Stop");
					m_listener.signal(MenuBar.this, new Message(SignalType.SIGNAL, Signal.Start));
				}
			}
		});
		simulation.add(m_startStopItem);
		m_changeShowTimeItem = new JCheckBoxMenuItem("Show Simulation Time");
		m_changeShowTimeItem.setSelected(true);
		m_changeShowTimeItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				boolean show = m_changeShowTimeItem.isSelected();
				if(show) {
					m_listener.signal(MenuBar.this, new Message(SignalType.INFO, Signal.ShowTime));
				} else {
					m_listener.signal(MenuBar.this, new Message(SignalType.INFO, Signal.HideTime));
				}
			}
		});
		simulation.add(m_changeShowTimeItem);

		m_changeShowInfoItem = new JCheckBoxMenuItem("Show Simulation Menu");
		m_changeShowInfoItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				boolean show = m_changeShowInfoItem.isSelected();
				if(show) {
					m_pauseEnabled = true;
					m_listener.signal(MenuBar.this, new Message(SignalType.INFO, Signal.ShowSimulationInfo));
				} else {
					m_pauseEnabled = false;
					m_listener.signal(MenuBar.this, new Message(SignalType.INFO, Signal.HideSimulationInfo));
				}
			}
		});
		simulation.add(m_changeShowInfoItem);

		add(fileMenu);
		add(simulation);
	}

	public MenuBar()
	{
		m_listener = Listener.getInstance();
		m_startEnabled = false;
		m_pauseEnabled = false;
		initMenuBar();
		subscribe();
	}

	@Override
	public void signal(Message mess)
	{
		switch (mess.m_action) {
			case Start:
				m_startStopItem.setText("Stop");
				m_startEnabled = true;
				break;
			case Stop:
            case ForceStop:
				m_startStopItem.setText("Start");
				m_startEnabled = false;
				break;
			case ShowTime:
				m_changeShowTimeItem.setSelected(true);
				break;
			case HideTime:
				m_changeShowTimeItem.setSelected(false);
				break;
			case ShowSimulationInfo:
				m_changeShowInfoItem.setSelected(true);
				m_pauseEnabled = true;
				break;
			case HideSimulationInfo:
				m_changeShowInfoItem.setSelected(false);
				m_pauseEnabled = false;
				break;
		}
	}
}
