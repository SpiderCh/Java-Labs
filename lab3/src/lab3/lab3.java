package lab3;

import GUI.MainWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Listener.Listener;

public class lab3 
{
    public static void main(String[] args) {
        if (System.getProperty("DEBUG").equals("1")) {
            System.out.println("Running in debug mode.");
        }
        try {
            if (System.getProperty("OS").equals("Linux")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (ClassNotFoundException | 
                InstantiationException | 
                IllegalAccessException | 
                UnsupportedLookAndFeelException ex) {
            if (System.getProperty("DEBUG").equals("1")) {
                System.out.println("Cannot setup system look and feel");
            }
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Listener.getInstance();
                new MainWindow().setVisible(true);
            }
        });
    }
    
}