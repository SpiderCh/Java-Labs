package GUI;

import Listener.Listener;
import Listener.Message;
import Signal.SignalType;
import Signal.Signal;
import Listener.iObservable;
import Utils.Updater;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuBar;
import Listener.iListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.Timer;

public class MainWindow extends JFrame implements iObservable, iWindow, Runnable
{
	private JPanel          m_mainPanel;
	private SimulationPanel m_simulationPanel;
	private ToolBar         m_toolBarPanel;
	private ControlPanel    m_controlPanel;
	private Timer           m_timer;
	private iListener       m_listener;
    private ResultDialog    m_resultDialog;
    private Console         m_console;
    private PipedReader     m_pipedRider  = null;
    private PipedWriter     m_pipedWriter = null;
    private boolean         m_isGoingThread = false;

	private void setupCloseAction()
	{
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				Listener.getInstance().stopQueue();
				try {
					Listener.getInstance().join();
				} catch (InterruptedException e1) {
					/*
					 * Add some kind of usefull output
					 */
				}
				if (System.getProperty("DEBUG").equals("1")) {
					System.out.println("\t" + getClass().getName() + ": ");
					System.out.println("\t\tClosing main window");
				}
			}
		});
	}

	private void initListener()
	{
		m_listener = Listener.getInstance();
		m_listener.subscribe(this, SignalType.SIGNAL);
        m_listener.subscribe(this, SignalType.DATA);
		m_listener.subscribe(this, SignalType.SYSTEM);
	}

	private void initWindow()
	{
		super.setTitle("Lab 6");
		super.setSize(1024, 768);
		super.setResizable(false);
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(new BorderLayout());
		m_simulationPanel = new SimulationPanel();
		this.add(m_simulationPanel, BorderLayout.CENTER);
		this.add(m_toolBarPanel.getContentPane(), BorderLayout.NORTH);
		this.add(m_controlPanel.getContentPane(), BorderLayout.EAST);
		GUI.MenuBar m_menuBar = new GUI.MenuBar();
		setJMenuBar(m_menuBar);
		setupCloseAction();
        setFocusable(true);
	}

    private void initVars()
    {
        m_resultDialog = new ResultDialog("Simulation Results");
        m_console = new Console();
        m_pipedWriter = new PipedWriter();
        m_console.setPipedReader(m_pipedWriter);
        try {
            m_pipedRider    = new PipedReader(m_console.getPipedWriter());
            m_isGoingThread = true;
            new Thread(this).start();
        } catch(IOException e) { }
    }

    private void saveProperties()
    {
        try {
            FileOutputStream fileOutput = new FileOutputStream("config");
            Properties properties = new Properties();
            properties = m_controlPanel.saveProperties(properties);
            properties.store(fileOutput, "config");
            fileOutput.close();
        }
        catch (Exception e) { }
    }

    private void loadProperties()
    {
        try {
            FileInputStream fileInput  = new FileInputStream("config");
            Properties      properties = new Properties();

            properties.load(fileInput);
            fileInput.close();

            m_controlPanel.loadProperties(properties);
        }
        catch (Exception e) { }
    }

	public MainWindow()
	{
		initListener();
		initWindow();
        initVars();
	}

	@Override
	public void update(int time)
	{
        m_simulationPanel.update(time);
        repaint();
	}

	@Override
	public void signal(Message mess)
	{
		switch (mess.m_action) {
			case Start:
				m_timer = new Timer();
				m_timer.schedule(new Updater(this), 0, 1000);
                repaint();
				break;
			case ForceStop:
			case Stop:
				m_timer.cancel();
				m_timer = null;
                repaint();
				break;
            case LiveObjects:
                HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) mess.m_data;
                new TableDialog("Live Objects", map);
                break;
		}
	}

    @Override
    public void run() {
        while(m_isGoingThread) {
            try {
                int command = m_pipedRider.read();
                int count   = m_pipedRider.read();

                switch (command) {
                    case 0:
                        int countManF = m_simulationPanel.fireAllManagers();

                        m_pipedWriter.write(0);
                        m_pipedWriter.write(countManF);
                        break;
                    case 1:
                        int countManH = m_simulationPanel.hireManagers(count);

                        m_pipedWriter.write(1);
                        m_pipedWriter.write(countManH);
                        break;
                }
            } catch(IOException e) { }
        }
    }
}
