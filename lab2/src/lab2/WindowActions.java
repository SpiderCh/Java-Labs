package lab2;
import utils.Pair;

public interface WindowActions {
    public Pair<Integer, Integer> get_point();
    public void repaint();
    public void update(int time);
    public void start_timer();
    public void stop_timer();
    public void show_time();
}
