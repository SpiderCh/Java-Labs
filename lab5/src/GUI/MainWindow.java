package GUI;

import Listener.Actions;
import Listener.iObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements iObservable, iWindow
{
	private JPanel m_mainPanel;

	private void setupCloseAction() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (System.getProperty("DEBUG").equals("1")) {
					System.out.println("\t" + getClass().getName() + ": ");
					System.out.println("\t\tClosing main window");
				}
			}
		});
	}

	public MainWindow()
	{
		super.setTitle("Lab 5");
		super.setSize(1024, 768);
		setupCloseAction();
		setContentPane(m_mainPanel);
		GUI.MenuBar m_menuBar = new GUI.MenuBar();
		setJMenuBar(m_menuBar);
	}

	@Override
	public void newAction(Actions action) {

	}

	@Override
	public void newAction(Actions action, Object data) {

	}

	@Override
	public void update(int time) {

	}
}
