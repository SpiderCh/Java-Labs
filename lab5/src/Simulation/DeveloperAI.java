package Simulation;

import java.util.Iterator;
import java.util.LinkedList;

public class DeveloperAI extends BaseAI {
    private float   m_changePeriod    = 0;
    private float   m_accumulatedTime = 0;

    private int     m_width  = 818;
    private int     m_height = 680;

    public DeveloperAI(LinkedList<Human> listEmployees, float speed, float period) {
        m_listEmployees = listEmployees;

        m_speed        = speed;
        m_changePeriod = period;
    }

    @Override
    protected void computeAI(float dt) {
        if(dt == 0) {System.exit(789);}
        m_accumulatedTime += dt;
        if (m_accumulatedTime > m_changePeriod) {
            setNewDirections();
            m_accumulatedTime = 0;
        }

        Iterator<Human> it = m_listEmployees.iterator();
        while (it.hasNext()) {
            Human emp = it.next();
            if (emp instanceof Developer) {
                Developer dev = (Developer)emp;

                // проверка на выход за границы
                if (dev.m_posX <= 0 || dev.m_posX >= m_width) {
                    dev.m_dx = -dev.m_dx;
                }

                if (dev.m_posY <= 0 || dev.m_posY >= m_height) {
                    dev.m_dy = -dev.m_dy;
                }

                // задание новых позиций
                dev.m_posX += dev.m_dx * m_speed * dt;
                dev.m_posY += dev.m_dy * m_speed * dt;
            }
        }
    }

    private void setNewDirections() {
        Iterator<Human> it = m_listEmployees.iterator();
        while (it.hasNext()) {
            Human emp = it.next();
            if (emp instanceof Developer) {
                Developer dev    = (Developer)emp;

                float dx = (float)Math.random() * 10 + 1;
                if (Math.random() < 0.5) {
                    dx = -dx;
                }

                float dy = (float)Math.random() * 10 + 1;
                if (Math.random() < 0.5) {
                    dy = -dy;
                }

                float     length = (float)Math.sqrt(dx * dx + dy * dy);

                dev.m_dx = dx / length;
                dev.m_dy = dy / length;
            }
        }
    }
}