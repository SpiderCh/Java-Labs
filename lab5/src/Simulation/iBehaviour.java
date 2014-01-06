package Simulation;

import Utils.Pair;
import java.awt.*;

public interface iBehaviour {
	public void paint(Graphics graphics, Pair<Integer, Integer> border);
	public void update(int time);
}