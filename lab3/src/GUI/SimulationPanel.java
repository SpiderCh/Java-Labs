package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;
import Simulation.Habitat;
import java.awt.Graphics;

public class SimulationPanel extends javax.swing.JPanel implements iObservable 
{
    private final Habitat m_simulation;
    
    public SimulationPanel() {
        initComponents();
        m_simulation = new Habitat(getSize().width, getSize().height);
        Listener.getInstance().addObservable(this);
    }
    
    public void updateSize()
    {
        int width = getSize().width; 
        int height = getSize().height;
        m_simulation.updateSize(width, height);
    }

    public void update(int time)
    {
        m_simulation.update(time);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grapics)
    {
        super.paint(grapics);
        m_simulation.paint(grapics);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void newAction(Actions action) 
    {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("\t" + getClass().getName() + ": ");
            System.out.println("\t\tCalled with action: " + action);
        }
        switch(action) {
            case Start:
                m_simulation.start();
                Listener.getInstance().addAction(this, Actions.Repaint);
                break;
            case ForceStop:
            case Stop:
                m_simulation.stop();
                Listener.getInstance().addAction(this, Actions.Repaint);
                break;
            case Pause:
                Listener.getInstance().addAction(this, Actions.SimulationResults, m_simulation.getResultStr());
                break;
            case ShowLiveObjects:
                Listener.getInstance().addAction(this, Actions.LiveObjects, m_simulation.getCurrentPersonalTable());
                break;
            case ShowTime:
                m_simulation.showTime();
                break;
            case HideTime:
                m_simulation.hideTime();
                break;
        }
    }

    @Override
    public void newAction(Actions action, Object data) 
    {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("\t" + getClass().getName() + ": ");
            System.out.println("\t\tCalled with action: " + action);
        }
        Integer t = null;
        Float f = null;
        switch(action) {
            case DevCreationPeriodChanged:
                t = (Integer)data;
                m_simulation.changeDevCreationPeriod(t);
                break;
            case DevLiveTimeChanged:
                t = (Integer)data;
                m_simulation.changeDevLifeTime(t);
                break;
            case DevPossibilityChanged:
                f = (Float)data;
                m_simulation.changeDevPossibility(f);
                break;
            case ManagerCreationPeriodChanged:
                t = (Integer)data;
                m_simulation.changeManagerCreationPeriod(t);
                break;
            case ManagerLiveTimeChanged:
                t = (Integer)data;
                m_simulation.changeManagerLifeTime(t);
                break;
            case ManagerMaxNumberChanged:
                f = (Float)data;
                m_simulation.changeManagerPossibility(f);
                break;
        }
    }
}
