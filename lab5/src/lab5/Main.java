package lab5;

import javax.swing.*;

import GUI.MainWindow;
import Listener.Listener;

public class Main
{
	public static void main(String[] args) {
		if (System.getProperty("DEBUG").equals("1")) {
			System.out.println("Running in debug mode.");
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException |
				InstantiationException |
				IllegalAccessException |
				UnsupportedLookAndFeelException ex) {
			if (System.getProperty("DEBUG").equals("1")) {
				System.out.println("Cannot setup system look and feel");
			}
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Listener.getInstance();
				new MainWindow().setVisible(true);
			}
		});
	}
}
