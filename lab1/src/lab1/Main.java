package lab1;

import utils.Pair;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

class WindowListener extends WindowAdapter
{
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

public class Main
{
    public static void main(String[] args) {
        URL loc = Main.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(loc.getFile());

        Frame frame = new JFrame("Апплет двойного назначения");

        MainWindow window = new MainWindow();
        window.set_app_type(AppType.APPLICATION);
	    window.set_parameters(args);
        window.init();
	    Pair<Integer, Integer> size = window.get_window_size();

	    frame.add(window);
        frame.setSize(size.first, size.second);
        frame.setVisible(true);
        frame.addWindowListener(new WindowListener());
    }
}
