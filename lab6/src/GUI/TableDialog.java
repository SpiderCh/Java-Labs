package GUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author CHAS
 */
public class TableDialog extends JDialog {
    private JButton         m_OKButton   = new JButton("ОК");
    private JTable          m_table      = new JTable();
    private SimulTableModel m_tableModel;

    public TableDialog(String title, HashMap<Integer, Integer> data)
    {
        super((JDialog) null, title, false);
        System.out.println("Creating");
        setSize(300, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        m_OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setLayout(new BorderLayout());

        m_tableModel = new SimulTableModel(data);
        m_table = new JTable(m_tableModel);
        JScrollPane jscrlp = new JScrollPane(m_table);

        add(jscrlp, BorderLayout.CENTER);
        add(m_OKButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class SimulTableModel extends AbstractTableModel {
        private HashMap<Integer, Integer> m_data = null;

        public SimulTableModel(HashMap<Integer, Integer> data) {
            super();
            m_data = data;
        }

        @Override
        public int getRowCount() {
            return m_data.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int row, int column) {
            Iterator<Integer> itKey = m_data.keySet().iterator();
            Object rez = null;
            int r = 0;
            while (itKey.hasNext()) {
                int id = itKey.next();

                if (r == row) {
                    if (column == 0) {
                        rez = id;
                        break;
                    }
                    rez = m_data.get(id);
                    break;
                }
                r++;
            }

            return rez;
        }

        @Override
        public String getColumnName(int c) {
            String result = "";
            switch (c) {
                case 0:
                    result = "Id";
                    break;
                case 1:
                    result = "Creation Time";
                    break;
            }
            return result;
        }
    }
}