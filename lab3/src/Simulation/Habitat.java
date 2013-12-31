/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import Utils.Pair;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class Habitat 
{
    /*
     * m_working_area_size:
     * First  --- width
     * Second --- height
     */
    private Pair<Integer, Integer> m_working_area_size;
    private boolean m_show_time;
    private boolean m_is_running;
    private int m_simulation_time;
    /*
     * m_employees_countr && m_periods:
     * First  --- Developer
     * Second --- Manager
     */
    private Pair<Integer, Integer> m_employees_counter;
    private Pair<Integer, Integer> m_periods;
    private Pair<Integer, Integer> m_life_times;
    private float m_developer_possibility;
    private float m_max_percent_of_managers;
    
    private LinkedList<Human> m_personal_list;
    private TreeSet<Integer> m_ids;
    private HashMap<Integer, Integer> m_curent_personal;

    private Image m_dev_image;
    private Image m_man_image;
    
    public Habitat()
    {
        initComponents();
    }

    public Habitat(int width, int height)
    {
        initComponents();
        
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tWindow Size:\n\t\tWidth: " + width + 
                    "\n\t\tHeight: " + height);
        }
        
        m_working_area_size.first = width;
        m_working_area_size.second = height;
    }
    
    private void initComponents()
    {
        m_working_area_size = new Pair<>(0, 0);
        m_employees_counter = new Pair<>(0, 0);
        m_life_times = new Pair<>(10, 10);
        m_periods = new Pair<>(5, 10);
        m_show_time = true;
        m_is_running = false;
        m_simulation_time = 0;
        
        m_personal_list = new LinkedList<>();
        m_ids = new TreeSet<>();
        m_curent_personal = new HashMap<>();

        m_developer_possibility = .8f;
        m_max_percent_of_managers = .5f;

        String dev_image_path = "linux_logo.jpg";
        String man_image_path = "win_logo.jpg";
        try {
            m_dev_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/linux_logo.png"));
            m_man_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/win_logo.png"));
//            m_dev_image = Toolkit.getDefaultToolkit().getImage(dev_image_path);
//            m_man_image = Toolkit.getDefaultToolkit().getImage(man_image_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start()
    {
        m_is_running = true;
        m_simulation_time = 0;
        m_employees_counter.first = 0;
        m_employees_counter.second = 0;
    }

    public void setSize(Pair<Integer, Integer> size)
    {
        m_working_area_size.first = size.first;
        m_working_area_size.second = size.second;
    }
    
    public void updateSize(int width, int height)
    {   
        if(System.getProperty("DEBUG").equals("1")) {
            System.out.println("\tWindow Size:\n\t\tWidth: " + width + 
                    "\n\t\tHeight: " + height);
        }
        
        m_working_area_size.first = width;
        m_working_area_size.second = height;
    }

    public void stop()
    {
        m_is_running = false;
        m_personal_list.clear();
        m_ids.clear();
        m_curent_personal.clear();
    }

    public void paint(Graphics graphics)
    {
        System.out.println(getClass().getName() + "::paint():");
        if (m_is_running) {
            Iterator<Human> it = m_personal_list.iterator();
            while (it.hasNext()) {
                Human h = it.next();
                h.paint(graphics, m_working_area_size);
            }

            if (m_show_time) {
                graphics.setColor(Color.RED);
                String str = "Simulation time = " + m_simulation_time + " sec.";
                int x = 10;
                int y = 15;
                graphics.drawString(str, x, y);
            }
        } else {
            printResult(graphics);
        }
    }

    public void update(int time)
    { 
        m_simulation_time = time;
        removeOldObjects();
        createDeveloper();
        createManager();
    }
    
    private void removeOldObjects()
    {
        Iterator<Human> it = m_personal_list.iterator();
        while(it.hasNext()){
            Human emp = it.next();
            if(m_simulation_time - emp.getCreationTime() >= emp.getLifeTime()){
                m_ids.remove(emp.getId());
                m_curent_personal.remove(emp.getId());
                it.remove();
            }
        }
    }
    
    private void createDeveloper()
    {
        int dev_update = m_simulation_time % m_periods.first;
        int pos_x = 0;
        int pos_y = 0;
        int id = m_employees_counter.first + m_employees_counter.second;
        if(dev_update == 0) {
            if(Math.random() <= m_developer_possibility){
                do {
                    pos_x = (int)(Math.random() * m_working_area_size.first);
                } while(pos_x == 0);
                do {
                    pos_y = (int)(Math.random() * m_working_area_size.second);
                } while(pos_y == 0);

                Human new_person = new Developer(id, pos_x, pos_y, m_simulation_time);
                new_person.setImage(m_dev_image);
                new_person.changeLifeTime(m_life_times.first);
                m_personal_list.add(new_person);
                ++m_employees_counter.first;
                m_ids.add(id);
                m_curent_personal.put(id, m_simulation_time);
            }
        }
    }
    
    private void createManager()
    {
        int man_update = m_simulation_time % m_periods.second; 
        int pos_x = 0;
        int pos_y = 0;
        int id = m_employees_counter.first + m_employees_counter.second;
        if(man_update == 0) {
            boolean man_percent = false;
            if(m_employees_counter.first > 0) {
                man_percent = (m_employees_counter.second/(float)m_employees_counter.first < m_max_percent_of_managers);
            }
            if(man_percent){
                do {
                    pos_x = (int)(Math.random() * m_working_area_size.first);
                } while(pos_x == 0);
                do {
                    pos_y = (int)(Math.random() * m_working_area_size.second);
                } while(pos_y == 0);

                Human new_person = new Manager(id, pos_x, pos_y, m_simulation_time);
                new_person.setImage(m_man_image);
                new_person.changeLifeTime(m_life_times.second);
                m_personal_list.add(new_person);
                ++m_employees_counter.second;
                m_ids.add(id);
                m_curent_personal.put(id, m_simulation_time);
            }
        }
    }

    public void printResult(Graphics graphics)
    {
        int x = 10;
        int y = 10;
        int count = m_employees_counter.first + m_employees_counter.second;
        graphics.setColor(Color.BLACK);
        graphics.drawString("Results:", x + 5, y + 5);
        graphics.drawString("Number of created objects: " + count, x + 5, y + 20);
        graphics.drawString("Number of Developers: " + m_employees_counter.first, x + 5, y + 35);
        graphics.drawString("Number of Managers: " + m_employees_counter.second, x + 5, y + 50);
        graphics.drawString("Elapsed time: " + m_simulation_time, x + 5, y + 65);
    }

    public void switchShowTime()
    {
        m_show_time = !m_show_time;
    }
    
    public void showTime()
    {
        m_show_time = true;
    }
    
    public void hideTime()
    {
        m_show_time = false;
    }
    
    public void changeDevCreationPeriod(int time)
    {
        m_periods.first = time;
    }
    
    public void changeManagerCreationPeriod(int time)
    {
        m_periods.second = time;
    }
    
     public void changeDevPossibility(float possibility)
    {
        m_developer_possibility = possibility;
    }
    
    public void changeManagerPossibility(float possibility)
    {
        m_max_percent_of_managers = possibility;
    }
    
    public void changeDevLifeTime(int time)
    {
         m_life_times.first = time;
         Iterator<Human> it = m_personal_list.iterator();
         while(it.hasNext()){
             Human emp = it.next();
             if(emp.getType() == PersonalType.Developer){
                 emp.changeLifeTime(time);
             }
         }
    }
    
    public void changeManagerLifeTime(int time)
    {
        m_life_times.second = time;
        Iterator<Human> it = m_personal_list.iterator();
         while(it.hasNext()){
             Human emp = it.next();
             if(emp.getType() == PersonalType.Manager){
                 emp.changeLifeTime(time);
             }
         }
    }
    
    public String getResultStr()
    {
        int count = m_employees_counter.first + m_employees_counter.second;
        String result = "Number of created objects: " + count +
                "\nNumber of developers: " + m_employees_counter.first +
                "\nNumber of managers: " + m_employees_counter.second +
                "\nSimulation time: " + m_simulation_time;
        return result;
    }
    
    public HashMap<Integer, Integer> getCurrentPersonalTable()
    {
         return new HashMap<>(m_curent_personal);
    }
}
