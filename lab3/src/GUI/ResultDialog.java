package GUI;
import Listener.Actions;
import Listener.Listener;
import Listener.iObservable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultDialog extends JDialog implements iObservable{
    private final JButton   m_OKButton;
    private final JButton   m_cancelButton;
    private final JTextArea m_text;

    public ResultDialog(String title)
    {
        super((JDialog) null, title, true);
        m_OKButton = new JButton("Ok");
        m_cancelButton = new JButton("Cancel");
        setSize(350, 200);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        Listener.getInstance().addObservable(this);

        JPanel bottomPanel = new JPanel();
        m_text = new JTextArea("");
        m_OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Listener.getInstance().addAction(ResultDialog.this, Actions.Stop);
                setVisible(false);
            }
        });
        bottomPanel.add(m_OKButton);
        m_cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        bottomPanel.add(m_cancelButton);
        add(bottomPanel, BorderLayout.SOUTH);

        m_text.setFont(new Font("Courier New", Font.PLAIN, 16));
        m_text.setEditable(false);
        add(m_text, BorderLayout.CENTER);
        setVisible(false);

    }

    public void setText(String message)
    {
        m_text.setText(message);
    }

    @Override
    public void newAction(Actions action) {}

    @Override
    public void newAction(Actions action, Object data) {
        switch(action) {
            case SimulationResults:
                m_text.setText((String)data);
                setVisible(true);
                break;
        }
    }
}
