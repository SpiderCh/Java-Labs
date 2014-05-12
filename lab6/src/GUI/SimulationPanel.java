package GUI;

import Listener.Listener;
import Listener.Message;
import Listener.iListener;
import Listener.iObservable;
import Signal.SignalType;
import Signal.Signal;
import Simulation.Habitat;
import Utils.Int;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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
		m_habitat.update(time);
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
        Int iVal = null;
        Float fVal = null;
		switch (mess.m_action) {
			case Start:
				m_habitat.start();
				break;
			case Stop:
			case ForceStop:
				m_habitat.stop();
				break;
            case Pause:
                m_listener.signal(this, new Message(SignalType.DATA, Signal.SimulationResults, m_habitat.getResultStr()));
                break;
			case ShowTime:
				m_habitat.showTime(true);
				break;
			case HideTime:
				m_habitat.showTime(false);
				break;
			case ShowLiveObjects:
                HashMap<Integer, Integer> map = m_habitat.getCurrentPersonalTable();
                m_listener.signal(this, new Message(SignalType.DATA, Signal.LiveObjects, map));
				break;
			case DevCreationPeriodChanged:
                iVal = (Int) mess.m_data;
				m_habitat.setDevCreationPeriod(iVal.get());
				break;
			case ManagerCreationPeriodChanged:
                iVal = (Int) mess.m_data;
				m_habitat.setManagerCreationPeriod(iVal.get());
				break;
			case DevLiveTimeChanged:
                iVal = (Int) mess.m_data;
				m_habitat.setDevLifeTime(iVal.get());
				break;
			case ManagerLiveTimeChanged:
                iVal = (Int) mess.m_data;
				m_habitat.setManagerLifeTime(iVal.get());
				break;
			case DevPossibilityChanged:
                fVal = (Float) mess.m_data;
				m_habitat.setDevPossibility(fVal.intValue());
				break;
			case ManagerMaxNumberChanged:
                fVal = (Float) mess.m_data;
				m_habitat.setMaxNumOfManagers(fVal.intValue());
				break;
			case DevThreadPriority:
//                iVal = (Int) mess.m_data;
				m_habitat.setDevThreadPriority((Integer) mess.m_data);
				break;
			case ManagerThreaadPriority:
//                iVal = (Integer) mess.m_data.;
				m_habitat.setManagerThreadPriority((Integer) mess.m_data);
				break;
            case StartDevThread:
                m_habitat.resumeDevThread();
                break;
            case StopDevThread:
                m_habitat.stopDevThread();
                break;
            case StartManThread:
                m_habitat.resumeManThread();
                break;
            case StopManThread:
                m_habitat.stopManThread();
                break;
		}
	}
}
