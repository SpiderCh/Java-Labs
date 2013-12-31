package GUI;

import java.util.TimerTask;

public class Updater extends TimerTask {
    private final iWindow m_window;
    private boolean m_first_run;
    private long    m_start_time;


    public Updater(iWindow window)
    {
        System.out.println("Created timer");
        m_window = window;
        m_first_run = true;
        m_start_time = 0;
    }

    @Override
    public void run()
    {
        System.out.println(getClass().getName());
        if(m_first_run){
            m_start_time = System.currentTimeMillis();
            m_first_run = false;
        }
        long currentTime = System.currentTimeMillis();
        int elapsed = (int)((currentTime - m_start_time) / 1000.0);
        m_window.update(elapsed);
    }
}
