package Simulation;

import java.util.Iterator;
import java.util.LinkedList;

public class ManagerAI extends BaseAI {
    private float m_radius = 0;

    public ManagerAI(LinkedList<Human> listEmployees, float speed, float radius) {
        m_listEmployees = listEmployees;

        m_speed  = speed;
        m_radius = radius;
    }

    @Override
    protected void computeAI(float dt) {
        float time = (System.currentTimeMillis() - m_startTime) / 1000.0f;

        Iterator<Human> it = m_listEmployees.iterator();
        while (it.hasNext()) {
            Human emp = it.next();
            if (emp instanceof Manager) {
                Manager man = (Manager)emp;

                man.m_posX = (int) (man.m_xCenter + m_radius * Math.cos(m_speed * time));
                man.m_posY = (int) (man.m_yCenter + m_radius * Math.sin(m_speed * time));
            }

        }
    }
}