/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import Utils.Pair;
import java.awt.*;

public interface iBehaviour {
    public void paint(Graphics graphics, Pair<Integer, Integer> border);
    public void update(int time);
}
