package GUI;

import Listener.Listener;
import Listener.Actions;
import Listener.iObservable;
import javax.swing.JOptionPane;

public class ControlPanel extends javax.swing.JPanel implements iObservable 
{
    private boolean m_pause;
    private Integer m_devCreationPeriod;
    private Integer m_devLiveTime;
    private Integer m_managerCreationPeriod;
    private Integer m_managerLiveTime;

    private void initVars()
    {
        m_pause = false;
        m_devCreationPeriod = new Integer(5);
        m_devLiveTime = new Integer(10);
        m_managerCreationPeriod = new Integer(8);
        m_managerLiveTime = new Integer(10);
        Listener.getInstance().addObservable(this);
    }
    
    public ControlPanel() {
        initComponents();
        initVars();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        m_devCreationPeriodField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        m_managerCreationPeriodField = new javax.swing.JTextField();
        m_showTimeRadioButton = new javax.swing.JRadioButton();
        m_hideTimeRadioButton = new javax.swing.JRadioButton();
        m_showInfoCheckbox = new javax.swing.JCheckBox();
        m_startButton = new javax.swing.JButton();
        m_stopButton = new javax.swing.JButton();
        m_showLiveObjectsButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        m_devCCreationBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        m_maxNumberManagersList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        m_devLifeTimeField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        m_mangerLiveTimeField = new javax.swing.JTextField();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dev Creation Period:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        m_devCreationPeriodField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        m_devCreationPeriodField.setText("5");
        m_devCreationPeriodField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_devCreationPeriodFieldActionPerformed(evt);
            }
        });
        m_devCreationPeriodField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_devCreationPeriodFieldKeyTyped(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Manager Creation Period:");

        m_managerCreationPeriodField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        m_managerCreationPeriodField.setText("8");
        m_managerCreationPeriodField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_managerCreationPeriodFieldActionPerformed(evt);
            }
        });
        m_managerCreationPeriodField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_managerCreationPeriodFieldKeyTyped(evt);
            }
        });

        buttonGroup2.add(m_showTimeRadioButton);
        m_showTimeRadioButton.setSelected(true);
        m_showTimeRadioButton.setText("Show Time");
        m_showTimeRadioButton.setFocusable(false);
        m_showTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_showTimeRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup2.add(m_hideTimeRadioButton);
        m_hideTimeRadioButton.setText("Hide Time");
        m_hideTimeRadioButton.setFocusable(false);
        m_hideTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_hideTimeRadioButtonActionPerformed(evt);
            }
        });

        m_showInfoCheckbox.setText("Show Results");
        m_showInfoCheckbox.setFocusable(false);
        m_showInfoCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_showInfoCheckboxActionPerformed(evt);
            }
        });

        m_startButton.setText("Start");
        m_startButton.setFocusable(false);
        m_startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_startButtonActionPerformed(evt);
            }
        });

        m_stopButton.setText("Stop");
        m_stopButton.setEnabled(false);
        m_stopButton.setFocusable(false);
        m_stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_stopButtonActionPerformed(evt);
            }
        });

        m_showLiveObjectsButton.setText("Show Live Objects");
        m_showLiveObjectsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        m_showLiveObjectsButton.setFocusable(false);
        m_showLiveObjectsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_showLiveObjectsButtonActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dev Creation Possibility");

        m_devCCreationBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10 %", "20 %", "30 %", "40 %", "50 %", "60 %", "70 %", "80 %", "90 %", "100 %" }));
        m_devCCreationBox.setSelectedIndex(4);
        m_devCCreationBox.setFocusable(false);
        m_devCCreationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_devCCreationBoxActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Number of Managers per Devs:");

        m_maxNumberManagersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "10 %", "20 %", "30 %", "40 %", "50 %", "60 %", "70 %", "80 %", "90 %", "100 %" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        m_maxNumberManagersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        m_maxNumberManagersList.setFocusable(false);
        m_maxNumberManagersList.setSelectedIndex(6);
        m_maxNumberManagersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_maxNumberManagersListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(m_maxNumberManagersList);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Dev Life Time:");

        m_devLifeTimeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        m_devLifeTimeField.setText("10");
        m_devLifeTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_devLifeTimeFieldActionPerformed(evt);
            }
        });
        m_devLifeTimeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_devLifeTimeFieldKeyTyped(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Manager Life Time:");

        m_mangerLiveTimeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        m_mangerLiveTimeField.setText("10");
        m_mangerLiveTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_mangerLiveTimeFieldActionPerformed(evt);
            }
        });
        m_mangerLiveTimeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_mangerLiveTimeFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_devCreationPeriodField)
            .addComponent(m_managerCreationPeriodField)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(m_devLifeTimeField)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(m_startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(m_stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(m_hideTimeRadioButton)
                        .addComponent(m_showInfoCheckbox)
                        .addComponent(m_showLiveObjectsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m_devCCreationBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(m_showTimeRadioButton))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(m_mangerLiveTimeField)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_devCreationPeriodField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_managerCreationPeriodField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_devLifeTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_mangerLiveTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(m_showTimeRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_hideTimeRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_showInfoCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_startButton)
                    .addComponent(m_stopButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_showLiveObjectsButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_devCCreationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void m_devCreationPeriodFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_devCreationPeriodFieldActionPerformed
        if (m_devLifeTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
            m_devLifeTimeField.setText(m_devCreationPeriod.toString());
        } else {
            devCreationPeriodChanged();
        }
    }//GEN-LAST:event_m_devCreationPeriodFieldActionPerformed

    private void m_showLiveObjectsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_showLiveObjectsButtonActionPerformed
        Listener.getInstance().addAction(this, Actions.ShowLiveObjects);
    }//GEN-LAST:event_m_showLiveObjectsButtonActionPerformed

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

    private void m_showInfoCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_showInfoCheckboxActionPerformed
        boolean selected = m_showInfoCheckbox.isSelected();
        if (selected) {
            Listener.getInstance().addAction(this, Actions.ShowSimulationInfo);
            m_pause = true;
        } else {
            Listener.getInstance().addAction(this, Actions.HideSimulationInfo);
            m_pause = false;
        }
    }//GEN-LAST:event_m_showInfoCheckboxActionPerformed

    private void m_showTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_showTimeRadioButtonActionPerformed
        Listener.getInstance().addAction(this, Actions.ShowTime);
    }//GEN-LAST:event_m_showTimeRadioButtonActionPerformed

    private void m_hideTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_hideTimeRadioButtonActionPerformed
        Listener.getInstance().addAction(this, Actions.HideTime);
    }//GEN-LAST:event_m_hideTimeRadioButtonActionPerformed

    private void m_devCreationPeriodFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_devCreationPeriodFieldKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_m_devCreationPeriodFieldKeyTyped

    private void m_managerCreationPeriodFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_managerCreationPeriodFieldKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_m_managerCreationPeriodFieldKeyTyped

    private void m_devLifeTimeFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_devLifeTimeFieldKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_m_devLifeTimeFieldKeyTyped

    private void m_mangerLiveTimeFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_mangerLiveTimeFieldKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_m_mangerLiveTimeFieldKeyTyped

    private void m_managerCreationPeriodFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_managerCreationPeriodFieldActionPerformed
        if (m_managerCreationPeriodField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
            m_managerCreationPeriodField.setText(m_managerCreationPeriod.toString());
        } else {
            managerCreationPeriodChanged();
        }
    }//GEN-LAST:event_m_managerCreationPeriodFieldActionPerformed

    private void m_devLifeTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_devLifeTimeFieldActionPerformed
        if (m_devLifeTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
            m_devLifeTimeField.setText(m_devLiveTime.toString());
        } else {
            devLiveTimeChanged();
        }
    }//GEN-LAST:event_m_devLifeTimeFieldActionPerformed

    private void m_mangerLiveTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_mangerLiveTimeFieldActionPerformed
        if (m_mangerLiveTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You forgot enter the value.\nLet me do this for you.");
            m_mangerLiveTimeField.setText(m_managerLiveTime.toString());
        } else {
            managerLiveTimeChanged();
        }
    }//GEN-LAST:event_m_mangerLiveTimeFieldActionPerformed

    private void m_devCCreationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_devCCreationBoxActionPerformed
        // TODO add your handling code here:
        float possibility = m_devCCreationBox.getSelectedIndex() + 1;
        possibility *= .1;
        Listener.getInstance().addAction(this, Actions.DevPossibilityChanged, new Float(possibility));
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tDev Possibility: " + possibility);
        }
    }//GEN-LAST:event_m_devCCreationBoxActionPerformed

    private void m_maxNumberManagersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_maxNumberManagersListMouseClicked
        float possibility = m_maxNumberManagersList.getSelectedIndex() + 1;
       possibility *= .1;
       Listener.getInstance().addAction(this, Actions.ManagerMaxNumberChanged, new Float(possibility));
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tManager Possibility: " + possibility);
        }
    }//GEN-LAST:event_m_maxNumberManagersListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox m_devCCreationBox;
    private javax.swing.JTextField m_devCreationPeriodField;
    private javax.swing.JTextField m_devLifeTimeField;
    private javax.swing.JRadioButton m_hideTimeRadioButton;
    private javax.swing.JTextField m_managerCreationPeriodField;
    private javax.swing.JTextField m_mangerLiveTimeField;
    private javax.swing.JList m_maxNumberManagersList;
    private javax.swing.JCheckBox m_showInfoCheckbox;
    private javax.swing.JButton m_showLiveObjectsButton;
    private javax.swing.JRadioButton m_showTimeRadioButton;
    private javax.swing.JButton m_startButton;
    private javax.swing.JButton m_stopButton;
    // End of variables declaration//GEN-END:variables

    private void devCreationPeriodChanged()
    {
        String time = m_devCreationPeriodField.getText();
        try {
            m_devCreationPeriod = Integer.parseInt(time);
            Listener.getInstance().addAction(this, Actions.DevCreationPeriodChanged, m_devCreationPeriod);
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Well, on this machine max value of current field is: " 
                    + Integer.MAX_VALUE + "\nReturning to previous value: " + 
                    m_devCreationPeriod.toString());
            m_devCreationPeriodField.setText(m_devCreationPeriod.toString());
        }
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tDev Creation Period: " + m_devCreationPeriod.toString());
        }
    }
    
    private void managerCreationPeriodChanged()
    {
        String time = m_managerCreationPeriodField.getText();
        try {
            m_managerCreationPeriod = Integer.parseInt(time);
            Listener.getInstance().addAction(this, Actions.ManagerCreationPeriodChanged, m_managerCreationPeriod);
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Well, on this machine max value of current field is: " 
                    + Integer.MAX_VALUE + "\nReturning to previous value: " + 
                    m_managerCreationPeriod.toString());
            m_managerCreationPeriodField.setText(m_managerCreationPeriod.toString());
        }
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tManager Creation Period: " + m_managerCreationPeriod.toString());
        }
    }
    
    private void devLiveTimeChanged()
    {
        String time = m_devLifeTimeField.getText();
        try {
            m_devLiveTime = Integer.parseInt(time);
            Listener.getInstance().addAction(this, Actions.DevLiveTimeChanged, m_devLiveTime);
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Well, on this machine max value of current field is: " 
                    + Integer.MAX_VALUE + "\nReturning to previous value: " + 
                    m_devLiveTime.toString());
            m_devLifeTimeField.setText(m_devLiveTime.toString());
        }
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tDev Life Time Changed: " + m_devLiveTime.toString());
        }
    }
    
    private void managerLiveTimeChanged()
    {
        String time = m_mangerLiveTimeField.getText();
        try {
            m_managerLiveTime = Integer.parseInt(time);
            Listener.getInstance().addAction(this, Actions.ManagerLiveTimeChanged, m_managerLiveTime);
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Well, on this machine max value of current field is: " 
                    + Integer.MAX_VALUE + "\nReturning to previous value: " + 
                    m_managerLiveTime.toString());
            m_mangerLiveTimeField.setText(m_managerLiveTime.toString());
        }
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tManager Life Time Changed: " + m_managerLiveTime.toString());
        }
    }
    
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
            case ShowSimulationInfo:
                m_showInfoCheckbox.setSelected(true);
                m_pause = true;
                break;
            case HideSimulationInfo:
                m_showInfoCheckbox.setSelected(false);
                m_pause = false;
                break;
            case ShowTime:
                m_showTimeRadioButton.setSelected(true);
                break;
            case HideTime:
                m_hideTimeRadioButton.setSelected(true);
                break;
        }
    }

    @Override
    public void newAction(Actions action, Object data) {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("\t" + getClass().getName() + ": ");
            System.out.println("\t\tCalled with action: " + action);
        }
    }
}
