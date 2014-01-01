package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iListener;
import Listener.iObservable;
import Utils.Int;

import javax.swing.*;
import java.awt.event.*;

class KeyTyped extends KeyAdapter
{
	@Override
	public void keyTyped(KeyEvent keyEvent) {
		char c = keyEvent.getKeyChar();
		if (c < '0' || c > '9') {
			keyEvent.consume();
		}
	}
}

public class ControlPanel implements iObservable
{
	private JButton      m_startButton;
	private JButton      m_stopButton;
	private JTabbedPane  tabbedPane1;
	private JButton      m_liveObjectsButton;
	private JCheckBox    m_showSimulationInfoBox;
	private JRadioButton m_showTimeRadioButton;
	private JRadioButton m_hideTimeRadioButton;
	private JButton      m_devThreadButton;
	private JComboBox    m_devThreadPriorityBox;
	private JTextField   m_devCreationPeriodField;
	private JTextField   m_devLifeTimeField;
	private JComboBox    m_devCreationPossibilityBox;
	private JButton      m_managersThreadButton;
	private JComboBox    m_managersThreadPriorityBox;
	private JPanel       m_controlPanel;
	private JTextField   m_managerCreationPeriodField;
	private JTextField   m_managersLifeTimeField;
	private JList        m_managersPercentList;
	private iListener    m_listener;
	private Int          m_devCreationPeriod;
	private Int          m_devLifeTime;
	private Int          m_managerCreationPeriod;
	private Int          m_managerLifeTime;
	private boolean      m_pauseEnabled;
	private boolean      m_devThreadRunning;
	private boolean      m_managerThreadRunning;

	private void subscribe()
	{
		m_listener.subscribe(this, Actions.Stop);
		m_listener.subscribe(this, Actions.Start);
		m_listener.subscribe(this, Actions.Pause);
		m_listener.subscribe(this, Actions.ShowTime);
		m_listener.subscribe(this, Actions.HideTime);
		m_listener.subscribe(this, Actions.ShowSimulationInfo);
		m_listener.subscribe(this, Actions.HideSimulationInfo);
	}

	private void initVars()
	{
		m_devLifeTime = new Int(1);
		m_managerLifeTime = new Int(1);
		m_devCreationPeriod = new Int(1);
		m_managerCreationPeriod = new Int(1);
		m_pauseEnabled = false;
		m_devThreadRunning = false;
		m_managerThreadRunning = false;
	}

	private void initHandlers()
	{
		m_startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				m_startButton.setEnabled(false);
				m_stopButton.setEnabled(true);
				m_devThreadButton.setEnabled(true);
				m_managersThreadButton.setEnabled(true);
				m_listener.addAction(ControlPanel.this, Actions.Start);

			}
		});
		m_stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				m_startButton.setEnabled(true);
				m_stopButton.setEnabled(false);
				m_devThreadButton.setEnabled(false);
				m_managersThreadButton.setEnabled(false);
				m_listener.addAction(ControlPanel.this, Actions.Stop);
			}
		});
		m_devThreadButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				if(m_devThreadRunning) {
					m_devThreadButton.setText("Start Thread");
				} else {
					m_devThreadButton.setText("Stop Thread");
				}
				m_devThreadRunning = !m_devThreadRunning;
			}
		});
		m_managersThreadButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				if(m_managerThreadRunning) {
					m_managersThreadButton.setText("Start Thread");
				} else {
					m_managersThreadButton.setText("Stop Thread");
				}
				m_managerThreadRunning = !m_managerThreadRunning;
			}
		});

		m_devCreationPeriodField.addKeyListener(new KeyTyped());
		m_devLifeTimeField.addKeyListener(new KeyTyped());
		m_managerCreationPeriodField.addKeyListener(new KeyTyped());
		m_managersLifeTimeField.addKeyListener(new KeyTyped());

		m_devCreationPeriodField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (m_devCreationPeriodField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
					m_devCreationPeriodField.setText(m_devCreationPeriod.toString());
				} else {
					fieldValueChanged(m_devCreationPeriodField.getText(), Actions.DevCreationPeriodChanged);
				}
			}
		});

		m_devLifeTimeField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (m_devLifeTimeField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
					m_devLifeTimeField.setText(m_devLifeTime.toString());
				} else {
					fieldValueChanged(m_devLifeTimeField.getText(), Actions.DevLiveTimeChanged);
				}
			}
		});

		m_managerCreationPeriodField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (m_managerCreationPeriodField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
					m_managerCreationPeriodField.setText(m_managerCreationPeriod.toString());
				} else {
					fieldValueChanged(m_managerCreationPeriodField.getText(), Actions.ManagerCreationPeriodChanged);
				}
			}
		});

		m_managersLifeTimeField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (m_managersLifeTimeField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
					m_managersLifeTimeField.setText(m_managerLifeTime.toString());
				} else {
					fieldValueChanged(m_managersLifeTimeField.getText(), Actions.ManagerLiveTimeChanged);
				}
			}
		});
		m_showTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				m_listener.addAction(ControlPanel.this, Actions.ShowTime);
			}
		});
		m_hideTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				m_listener.addAction(ControlPanel.this, Actions.HideTime);
			}
		});
		m_showSimulationInfoBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				boolean selected = m_showSimulationInfoBox.isSelected();
				if(selected) {
					m_pauseEnabled = true;
					m_listener.addAction(ControlPanel.this, Actions.ShowSimulationInfo);
				} else {
					m_pauseEnabled = false;
					m_listener.addAction(ControlPanel.this, Actions.HideSimulationInfo);
				}
			}
		});
		m_liveObjectsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				m_listener.addAction(ControlPanel.this, Actions.ShowLiveObjects);
			}
		});
		m_devCreationPossibilityBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				float possibility = m_devCreationPossibilityBox.getSelectedIndex() + 1;
				possibility *= .1;
				Listener.getInstance().addAction(ControlPanel.this, Actions.DevPossibilityChanged, new Float(possibility));
				if(System.getProperty("DEBUG").equals("1")) {
					System.out.println("\tDev Possibility: " + possibility);
				}
			}
		});
		m_managersPercentList.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent mouseEvent)
			{
				float possibility = m_managersPercentList.getSelectedIndex() + 1;
				possibility *= .1;
				Listener.getInstance().addAction(ControlPanel.this, Actions.ManagerMaxNumberChanged, new Float(possibility));

				if(System.getProperty("DEBUG").equals("1")) {
					System.out.println("\tManager Possibility: " + possibility);
				}
			}
		});
		m_devThreadPriorityBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{

			}
		});
		m_managersThreadPriorityBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{

			}
		});
	}

	private void fieldValueChanged(String time, Actions type)
	{
		Int val;
		JTextField currField;

		switch(type){
			case DevCreationPeriodChanged:
				val = m_devCreationPeriod;
				currField = m_devCreationPeriodField;
				break;
			case DevLiveTimeChanged:
				val = m_devLifeTime;
				currField = m_devLifeTimeField;
				break;
			case ManagerCreationPeriodChanged:
				val = m_managerCreationPeriod;
				currField = m_managerCreationPeriodField;
				break;
			case ManagerLiveTimeChanged:
				val = m_managerLifeTime;
				currField = m_managersLifeTimeField;
				break;
			default:
				return;
		}

		try{
			val.fromString(time);
			m_listener.addAction(this, type, val);
		} catch (java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Well, on this machine max value of current field is: "
					+ Integer.MAX_VALUE + "\nReturning to previous value: " + val.toString());
			currField.setText(val.toString());
		}

		if(System.getProperty("DEBUG").equals("1")) {
			String message = null;
			switch(type){
				case DevCreationPeriodChanged:
					message = "\tDeveloper Creation Period changed: ";
					break;
				case DevLiveTimeChanged:
					message = "\tDeveloper Life Time changed: ";
					break;
				case ManagerCreationPeriodChanged:
					message = "\tManager Creation Period changed: ";
					break;
				case ManagerLiveTimeChanged:
					message = "\tManager Life Time changed: ";
					break;
			}
			System.out.println(message + val.toString());
		}
	}

	public ControlPanel()
	{
		m_listener = Listener.getInstance();
		subscribe();
		m_stopButton.setEnabled(false);
		initVars();
		initHandlers();
	}

	@Override
	public void newAction(Actions action)
	{
		switch (action)
		{
			case Start:
				m_startButton.setEnabled(false);
				m_stopButton.setEnabled(true);
				m_devThreadButton.setEnabled(true);
				m_managersThreadButton.setEnabled(true);
				break;
			case Stop:
				m_startButton.setEnabled(true);
				m_stopButton.setEnabled(false);
				m_devThreadButton.setEnabled(false);
				m_managersThreadButton.setEnabled(false);
				break;
			case ShowTime:
				m_showTimeRadioButton.setSelected(true);
				break;
			case HideTime:
				m_hideTimeRadioButton.setSelected(true);
				break;
			case ShowSimulationInfo:
				m_pauseEnabled = true;
				m_showSimulationInfoBox.setSelected(true);
				break;
			case HideSimulationInfo:
				m_pauseEnabled = false;
				m_showSimulationInfoBox.setSelected(false);
				break;
		}
	}

	@Override
	public void newAction(Actions action, Object data)
	{

	}
}
