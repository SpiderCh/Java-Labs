package Simulation;

import Utils.Int;
import Utils.Pair;
import java.awt.*;

public interface iBehaviour {
	public void paint(Graphics graphics, Pair<Int, Int> border);
	public void update(int time);
}