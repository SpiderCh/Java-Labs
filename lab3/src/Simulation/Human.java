/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import Utils.Pair;
import java.awt.*;

public abstract class Human implements iBehaviour {
    protected Pair<Integer, Integer> m_position;
    protected int m_id;
    protected String m_image_path;
    protected Image m_image;
    protected Pair<Integer, Integer> m_vital_signs;
    protected PersonalType m_type;

    public Human()
    {
        m_position = new Pair<>(0, 0);
        m_id = -1;
        m_image_path = null;
        m_image = null;
        m_vital_signs = new Pair<>(-1, 10);
    }

    public Human(int id)
    {
        m_position = new Pair<>(0, 0);
        m_id = id;
        m_image_path = null;
        m_image = null;
        m_vital_signs = new Pair<>(0, 10);
    }

    public Human(int x, int y)
    {
        m_position = new Pair<>(x, y);
        m_id = -1;
        m_image_path = null;
        m_image = null;
        m_vital_signs = new Pair<>(0, 10);
    }

    public Human(int id, int x, int y)
    {
        m_position = new Pair<>(x, y);
        m_id = id;
        m_image_path = null;
        m_image = null;
        m_vital_signs = new Pair<>(0, 10);
    }
    
    public Human(int id, int x, int y, int current_time)
    {
        m_position = new Pair<>(x, y);
        m_id = id;
        m_image_path = null;
        m_image = null;
        m_vital_signs = new Pair<>(current_time, 10);
    }

    public void updatePosition(int x, int y)
    {
        m_position.first  = x;
        m_position.second = y;
    }

    public void setImage(Image image)
    {
        m_image = image;
    }

    @Override
    public void paint(Graphics graphics, Pair<Integer, Integer> border)
    {
        int width = m_image.getWidth(null);
        int height = m_image.getHeight(null);
        if(width == -1 || height == -1){
            return;
        }
        int offscreen_width = border.first - (m_position.first + width);
        int offscreen_height = border.second - (m_position.second + height);
        
        graphics.drawImage(m_image, m_position.first, m_position.second, null);
        if(offscreen_width < 0 || offscreen_height < 0) {
            if(offscreen_width < 0) {
                offscreen_width = 0 - (width + offscreen_width);
            } else {offscreen_width = m_position.first;}
            if(offscreen_height < 0) {
                offscreen_height = 0 - (height + offscreen_height);
            } else {offscreen_height = m_position.second;}
            
            graphics.drawImage(m_image, offscreen_width, offscreen_height, null);
        }
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tImage:\n\t\tWidth: " + width + "\n\t\tHeight: " + height);
            System.out.println("\t\tScreen Width Position: " + m_position.first + 
                    "\n\t\tScreen Height Position: " + m_position.second);
            System.out.println("\t\tOffscreen Width: " + offscreen_width + 
                    "\n\t\tOffscreen Height: " + offscreen_height);
        }
    }

    @Override
    public abstract void update(int time);
    
    public int getId()
    {
        return m_id;
    }
    
    public int getCreationTime()
    {
        return m_vital_signs.first;
    }
    
    public int getLifeTime()
    {
        return m_vital_signs.second;
    }
    
    public void changeLifeTime(int new_time)
    {
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tChanged life time to: " + new_time);
        }
        m_vital_signs.second = new_time;
    }
    
    public PersonalType getType()
    {
        return m_type;
    }
}
