package GUI;

import Listener.Actions;
import Listener.iObservable;

import javax.swing.*;

public class MenuBar  extends JMenuBar implements iObservable
{
	private void initMenuBar()
	{
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		add(fileMenu);
	}

	public MenuBar()
	{
		initMenuBar();
	}

	@Override
	public void newAction(Actions action) {

	}

	@Override
	public void newAction(Actions action, Object data) {

	}
}
