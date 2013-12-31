package lab2;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int width = 800;
        int height = 640;
        JFrame frame = new JFrame("Simulation program");
        MainWindow application = new MainWindow();
        frame.add(application);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        application.set_size(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
