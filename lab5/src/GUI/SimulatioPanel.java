package GUI;

import Listener.Listener;
import Listener.iListener;
import Listener.iObservable;
import Listener.Actions;

import javax.swing.*;
import java.awt.event.ComponentAdapter;

public class SimulatioPanel implements iObservable
{
	private JPanel m_simulationPanel;
	private iListener m_listener;

	public SimulatioPanel()
	{
		m_listener = Listener.getInstance();
	}

	@Override
	public void newAction(Actions action) {

	}

	@Override
	public void newAction(Actions action, Object data) {

	}
}
