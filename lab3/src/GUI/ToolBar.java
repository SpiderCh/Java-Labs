package GUI;

import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;

public class ToolBar extends javax.swing.JPanel implements iObservable {

    private boolean m_timeEnabled;
    private boolean m_pause;

    public ToolBar() {
        initComponents();
        Listener.getInstance().addObservable(this);
        m_timeEnabled = true;
        m_pause = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_toolBar = new javax.swing.JToolBar();
        m_startButton = new javax.swing.JButton();
        m_stopButton = new javax.swing.JButton();
        m_timeButton = new javax.swing.JButton();

        m_toolBar.setFloatable(false);
        m_toolBar.setRollover(true);

        m_startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/play.png"))); // NOI18N
        m_startButton.setFocusable(false);
        m_startButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        m_startButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        m_startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_startButtonActionPerformed(evt);
            }
        });
        m_toolBar.add(m_startButton);

        m_stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/stop.png"))); // NOI18N
        m_stopButton.setEnabled(false);
        m_stopButton.setFocusable(false);
        m_stopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        m_stopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        m_stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_stopButtonActionPerformed(evt);
            }
        });
        m_toolBar.add(m_stopButton);

        m_timeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/time.png"))); // NOI18N
        m_timeButton.setFocusable(false);
        m_timeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        m_timeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        m_timeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_timeButtonActionPerformed(evt);
            }
        });
        m_toolBar.add(m_timeButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void m_startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_startButtonActionPerformed
        m_startButton.setEnabled(false);
        m_stopButton.setEnabled(true);
        Listener.getInstance().addAction(this, Actions.Start);
    }//GEN-LAST:event_m_startButtonActionPerformed

    private void m_stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_stopButtonActionPerformed
        if (m_pause) {
            Listener.getInstance().addAction(this, Actions.Pause);
        } else {
            m_startButton.setEnabled(true);
            m_stopButton.setEnabled(false);
            Listener.getInstance().addAction(this, Actions.Stop);
        }
    }//GEN-LAST:event_m_stopButtonActionPerformed

    private void m_timeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_timeButtonActionPerformed
        if (m_timeEnabled) {
            Listener.getInstance().addAction(this, Actions.HideTime);
            m_timeEnabled = false;
        } else {
            Listener.getInstance().addAction(this, Actions.ShowTime);
            m_timeEnabled = true;
        }
    }//GEN-LAST:event_m_timeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton m_startButton;
    private javax.swing.JButton m_stopButton;
    private javax.swing.JButton m_timeButton;
    private javax.swing.JToolBar m_toolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void newAction(Actions action) {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("\t" + getClass().getName() + ": ");
            System.out.println("\t\tCalled with action: " + action);
        }
        switch (action) {
            case Start:
                m_startButton.setEnabled(false);
                m_stopButton.setEnabled(true);
                break;
            case ForceStop:
            case Stop:
                m_startButton.setEnabled(true);
                m_stopButton.setEnabled(false);
                break;
            case ShowTime:
                m_timeEnabled = true;
                break;
            case HideTime:
                m_timeEnabled = false;
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
    }
}
