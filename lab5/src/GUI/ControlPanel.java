package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;

import javax.swing.*;

public class ControlPanel implements iObservable
{
	private JButton m_startButton;
	private JButton m_stopButton;
	private JTabbedPane tabbedPane1;
	private JButton liveObjectsButton;
	private JCheckBox m_showSimulationInfoBox;
	private JRadioButton m_showTimeRadioButton;
	private JRadioButton m_hideTimeRadioButton;
	private JButton m_devThreadButton;
	private JComboBox m_devThreadPriorityBox;
	private JTextField m_devCreationPeriod;
	private JTextField m_devLifeTimeField;
	private JComboBox m_devCreationPossibilityBox;
	private JButton m_managersThreadButton;
	private JComboBox m_managersThreadPriorityBox;
	private JPanel m_controlPanel;
	private JTextField m_managerCreationPeriodField;
	private JTextField m_managersLifeTimeField;
	private JList m_managersPercentList;

	public ControlPanel()
	{
		m_stopButton.setEnabled(false);
		Listener.getInstance().addObservable(this);
	}

	@Override
	public void newAction(Actions action)
	{
		switch (action)
		{
			case Start:
				m_startButton.setEnabled(false);
				m_stopButton.setEnabled(true);
				break;
			case Stop:
				m_startButton.setEnabled(true);
				m_stopButton.setEnabled(false);
				break;
		}
	}

	@Override
	public void newAction(Actions action, Object data) {

	}
}
