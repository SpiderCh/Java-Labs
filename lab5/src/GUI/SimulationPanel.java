package GUI;

import Listener.Listener;
import Listener.Message;
import Listener.iListener;
import Listener.iObservable;
import Signal.SignalType;
import Signal.Signal;
import Simulation.Habitat;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel implements iObservable
{
	private JPanel m_simulationPanel;
	private iListener m_listener;
	private Habitat m_habitat;

	private void initListener()
	{
		m_listener = Listener.getInstance();
		m_listener.subscribe(this, SignalType.DATA);
		m_listener.subscribe(this, SignalType.SIGNAL);
		m_listener.subscribe(this, SignalType.INFO);
	}

	public SimulationPanel()
	{
		initListener();
		setBackground(Color.white);
		m_habitat = new Habitat();
		this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	}

	public void update(int time)
	{
		m_habitat.update(time / 10f);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		m_habitat.paint(g);
	}

	@Override
	public void signal(Message mess)
	{
		switch (mess.m_action) {
			case Start:
				m_habitat.start();
				break;
			case Stop:
			case ForceStop:
				m_habitat.stop();
				break;
			case ShowTime:
				m_habitat.showTime(true);
				break;
			case HideTime:
				m_habitat.showTime(false);
				break;
			case ShowLiveObjects:
				break;
			case DevCreationPeriodChanged:
				m_habitat.setDevCreationPeriod((Integer)mess.m_data);
				break;
			case ManagerCreationPeriodChanged:
				m_habitat.setManagerCreationPeriod((Integer)mess.m_data);
				break;
			case DevLiveTimeChanged:
				m_habitat.setDevLifeTime((Integer)mess.m_data);
				break;
			case ManagerLiveTimeChanged:
				m_habitat.setManagerLifeTime((Integer)mess.m_data);
				break;
			case DevPossibilityChanged:
				m_habitat.setDevPossibility((Integer)mess.m_data);
				break;
			case ManagerMaxNumberChanged:
				m_habitat.setMaxNumOfManagers((Integer)mess.m_data);
				break;
			case DevThreadPriority:
				m_habitat.setDevThreadPriority((Integer)mess.m_data);
				break;
			case ManagerThreaadPriority:
				m_habitat.setManagerThreadPriority((Integer)mess.m_data);
				break;
		}
	}
}
