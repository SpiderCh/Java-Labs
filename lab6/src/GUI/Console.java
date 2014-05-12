package GUI;

import Listener.Listener;
import Listener.Message;
import Listener.iObservable;
import Signal.Signal;
import Signal.SignalType;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.*;
import java.util.List;

public class Console extends JDialog implements iObservable, Runnable
{
    private JPanel m_window;
    private JTextArea m_console;
    private ArrayList<String> m_commands;
    private long m_lines;
    private boolean m_isRunning;
    private boolean m_isGoingThread     = false;
    private PipedReader m_pipedRider    = null;
    private PipedWriter m_pipedWriter   = null;

    private void initEnvironment()
    {
        m_console.setCaretColor(Color.green);
        m_console.setFont(new Font("Terminus", Font.CENTER_BASELINE, 12));
        m_console.setText("#> ");
        m_console.setCaretPosition(3);

        m_console.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                int    keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_ENTER:
                        processInput();
                        break;
                    case KeyEvent.VK_UP:

                        break;
                    case KeyEvent.VK_DOWN:

                        break;
                }
            }
        });
    }

    private void initVars()
    {
        m_isRunning = false;
        m_lines = 0;
        m_commands = new ArrayList<>();
        m_pipedWriter = new PipedWriter();
    }

    private void subscribe()
    {
        Listener.getInstance().subscribe(this, SignalType.SIGNAL);
        Listener.getInstance().subscribe(this, SignalType.SYSTEM);
    }

    public Console()
    {
        super((JDialog) null, "Console");
        initEnvironment();
        initVars();
        subscribe();
        setContentPane(m_window);
        setModal(false);
        setVisible(false);
        setSize(640, 400);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    public PipedWriter getPipedWriter()
    {
        return m_pipedWriter;
    }

    public void setPipedReader(PipedWriter pipedWriter)
    {
        try {
            m_pipedRider    = new PipedReader(pipedWriter);
            m_isGoingThread = true;
            new Thread(this).start();
        } catch(IOException e) { }
    }

    @Override
    public void signal(Message mess)
    {
        switch(mess.m_action) {
            case ShowConsole:
                setVisible(true);
                break;
            case Start:
                m_isRunning = true;
                break;
            case Stop:
            case ForceStop:
                m_isRunning = false;
                break;
        }
    }

    @Override
    public void run()
    {
        while(m_isGoingThread) {
            try {
                int command = m_pipedRider.read();
                int count   = m_pipedRider.read();

                switch (command) {
                    case 0:
                        m_console.setCaretPosition(m_console.getText().length() - 3);
                        m_console.append("\nFired " + count + " workers\n#> ");
                        m_console.setCaretPosition(m_console.getText().length());
                        break;
                    case 1:
                        m_console.setCaretPosition(m_console.getText().length() - 3);
                        m_console.append("\nHired " + count + " workers\n#> ");
                        m_console.setCaretPosition(m_console.getText().length());
                        break;
                }
            } catch(IOException e) { }
        }
    }

    private void processInput()
    {
        m_lines = m_console.getLineCount() - 2;
        int cPosition = m_console.getCaretPosition();
        String command      = "";
        int offset_start    = -1;
        int offset_end      = -1;
        try {
            offset_start = m_console.getLineStartOffset((int) m_lines);
            offset_end = m_console.getLineEndOffset((int) m_lines) - 1; //Removing \n position
            command = m_console.getText(offset_start + 3, offset_end - (offset_start + 3));
        } catch (BadLocationException e) {System.out.println("Bad Location.");}
        if(command.equals("")) {
            m_console.setCaretPosition(cPosition);
            m_console.append("#> ");
            return;
        }
        m_console.setCaretPosition(cPosition);
        m_commands.add(command);
        String tokens[] = command.split(" ");
        switch(tokens[0]) {
            case "help":
                m_console.append("There is no hope.\n");
                break;
            case "clear":
                m_console.setText("#> ");
                return;
            case "start":
                if(m_isRunning) {
                    m_console.append("Simulation already running.\n");
                } else {
                    m_isRunning = true;
                    Listener.getInstance().signal(this, new Message(SignalType.SIGNAL, Signal.Start, null));
                    m_console.append("Running simulation.\n");
                }
                break;
            case "stop":
                if(m_isRunning) {
                    m_isRunning = false;
                    Listener.getInstance().signal(this, new Message(SignalType.SIGNAL, Signal.Stop, null));
                    m_console.append("Stopping simulation.\n");
                } else {
                    m_console.append("Simulation already stopped.\n");
                }
                break;
            case "fire":
                if(tokens.length == 2 && tokens[1].equals("managers")) {
                    try {
                        m_pipedWriter.write(0);
                        m_pipedWriter.write(0);
                    } catch(IOException e) { }
                } else {
                    m_console.append("Unknown command.\n");
                }
                break;
            case "hire":
                if(tokens.length == 3 && tokens[2].equals("managers")) {
                    int num = Integer.parseInt(tokens[1]);
                    try {
                        m_pipedWriter.write(1);
                        m_pipedWriter.write(num);
                    } catch(IOException e) { }
                } else {
                    m_console.append("Unknown command.\n");
                }
                break;
            default:
                m_console.append("Unknown command\n");
        }
        m_console.append("#> ");
    }
}
