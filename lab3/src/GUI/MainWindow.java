package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Timer;

public class MainWindow extends javax.swing.JFrame implements iObservable, iWindow {

    private boolean m_showTime;
    private boolean m_pause;
    private final ResultDialog m_resultDialog;
    private Timer m_timer;

    private void setupCloseAction() {
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

    private void setupKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key_code = e.getKeyCode();
                switch (key_code) {
                    case KeyEvent.VK_R:
                    case KeyEvent.VK_B:
                        Listener.getInstance().addAction(null, Actions.Start);
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_E:
                        if (m_pause) {
                            Listener.getInstance().addAction(null, Actions.Pause);
                        } else {
                            Listener.getInstance().addAction(null, Actions.Stop);
                        }
                        break;
                    case KeyEvent.VK_T:
                        if (m_showTime) {
                            Listener.getInstance().addAction(MainWindow.this, Actions.HideTime);
                            m_showTime = false;
                        } else {
                            Listener.getInstance().addAction(MainWindow.this, Actions.ShowTime);
                            m_showTime = true;
                        }
                        break;
                }
            }
        });
    }

    private void initVars() {
        Listener.getInstance().addObservable(this);
        m_showTime = true;
        m_pause = false;
    }

    public MainWindow() {
        super("Simulation Test Programm");
        initComponents();
        setupCloseAction();
        setupKeyListener();
        initVars();
        m_simulationPanel.updateSize();
        m_resultDialog = new ResultDialog("Simulation Results");
        setFocusable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_simulationPanel = new GUI.SimulationPanel();
        m_controlPanel = new GUI.ControlPanel();
        m_toolBar = new GUI.ToolBar();
        m_menuBar = new GUI.MenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("m_mainWindow"); // NOI18N
        setResizable(false);

        javax.swing.GroupLayout m_simulationPanelLayout = new javax.swing.GroupLayout(m_simulationPanel);
        m_simulationPanel.setLayout(m_simulationPanelLayout);
        m_simulationPanelLayout.setHorizontalGroup(
            m_simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        m_simulationPanelLayout.setVerticalGroup(
            m_simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setJMenuBar(m_menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(m_toolBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_simulationPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(m_controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(m_toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_simulationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Simulation Program");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.ControlPanel m_controlPanel;
    private GUI.MenuBar m_menuBar;
    private GUI.SimulationPanel m_simulationPanel;
    private GUI.ToolBar m_toolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void newAction(Actions action) {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("\t" + getClass().getName() + ": ");
            System.out.println("\t\tCalled with action: " + action);
        }
        switch (action) {
            case Repaint:
                repaint();
                break;
            case ForceStop:
            case Stop:
                m_timer.cancel();
                m_timer = null;
                break;
            case Start:
                m_timer = new Timer();
                m_timer.schedule(new Updater(this), 0, 1000);
                break;
            case ShowTime:
                m_showTime = true;
                break;
            case HideTime:
                m_showTime = false;
                break;
            case ShowSimulationInfo:
                m_pause = true;
                break;
            case HideSimulationInfo:
                m_pause = false;
                break;
        }
    }

    @Override
    public void newAction(Actions action, Object data) {
        switch (action) {
            case LiveObjects:
                HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) data;
                new TableDialog("Live Objects", map);
                break;
        }
    }

    @Override
    public void update(int time) {
        m_simulationPanel.update(time);
        repaint();
    }
}
