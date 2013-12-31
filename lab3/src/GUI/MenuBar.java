package GUI;

import Listener.Actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Listener.Listener;
import Listener.iObservable;

public class MenuBar extends JMenuBar implements iObservable {

    private JMenuItem m_startStopItem;
    private JCheckBoxMenuItem m_changeShowTimeItem;
    private JCheckBoxMenuItem m_changeShowInfoItem;
    private boolean m_pauseEnabled;
    private boolean m_startEnabled;

    private void initMenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        fileMenu.add(exitItem);

        JMenu simulMenu = new JMenu("Simulation");
        m_startStopItem = new JMenuItem("Start");

        m_startStopItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (m_startEnabled) {
                    if (!m_pauseEnabled) {
                        m_startStopItem.setText("Start");
                        Listener.getInstance().addAction(MenuBar.this, Actions.Stop);
                        m_startEnabled = false;
                    } else {
                        Listener.getInstance().addAction(MenuBar.this, Actions.Pause);
                    }
                } else {
                    m_startStopItem.setText("Stop");
                    Listener.getInstance().addAction(MenuBar.this, Actions.Start);
                    m_startEnabled = true;
                }
            }
        });

        simulMenu.add(m_startStopItem);
        m_changeShowTimeItem = new JCheckBoxMenuItem("Show time");
        m_changeShowTimeItem.setSelected(true);

        m_changeShowTimeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean show = m_changeShowTimeItem.isSelected();
                if (show) {
                    Listener.getInstance().addAction(MenuBar.this, Actions.ShowTime);
                } else {
                    Listener.getInstance().addAction(MenuBar.this, Actions.HideTime);
                }
            }
        });

        simulMenu.add(m_changeShowTimeItem);
        m_changeShowInfoItem = new JCheckBoxMenuItem("Show Results");
        m_changeShowInfoItem.setSelected(false);

        m_changeShowInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean show = m_changeShowInfoItem.isSelected();
                if (show) {
                    Listener.getInstance().addAction(MenuBar.this, Actions.ShowSimulationInfo);
                    m_pauseEnabled = true;
                } else {
                    Listener.getInstance().addAction(MenuBar.this, Actions.HideSimulationInfo);
                    m_pauseEnabled = false;
                }

            }
        });

        simulMenu.add(m_changeShowInfoItem);

        add(fileMenu);
        add(simulMenu);
    }

    public MenuBar() {
        initMenuBar();
        m_pauseEnabled = false;
        m_startEnabled = false;
        Listener.getInstance().addObservable(this);
    }

    @Override
    public void newAction(Actions action) {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("\t" + getClass().getName() + ": ");
            System.out.println("\t\tCalled with action: " + action);
        }
        switch (action) {
            case Start:
                m_startStopItem.setText("Stop");
                m_startEnabled = true;
                break;
            case ForceStop:
            case Stop:
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

    @Override
    public void newAction(Actions action, Object data) {
    }
}
