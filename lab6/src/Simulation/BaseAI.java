package Simulation;

import java.util.LinkedList;

public abstract class BaseAI extends Thread {
    protected boolean m_isGoing    = false;
    protected boolean m_isSleeping = false;

    // список работников
    protected LinkedList<Human> m_listEmployees;

    protected float m_speed     = 0;
    protected long  m_startTime = 0;
    protected long  m_oldTime   = 0;

    public synchronized void setState(int state) {
        switch (state) {
            case 0 : // остановка потока
                m_isGoing = false;
                break;
            case 1 : // старт потока
                m_isGoing    = true;
                m_isSleeping = false;
                m_startTime  = System.currentTimeMillis();
                m_oldTime    = m_startTime;
                super.start();
                break;
            case 2 : // приостановка потока
                m_isSleeping = true;
                break;
            case 3 : // возобновление потока
                m_isSleeping = false;
                m_startTime  = System.currentTimeMillis();
                m_oldTime    = m_startTime;
                synchronized (m_listEmployees) {
                    m_listEmployees.notifyAll();
                }
                break;
        }
    }

    @Override
    public void run() {
        while (m_isGoing) {

            synchronized (m_listEmployees) {

                if (m_isSleeping) {
                    try {
                        m_listEmployees.wait();
                    } catch(InterruptedException e) { System.out.println("Cannot suspend thread");}
                } else {
                    long currentTime = System.currentTimeMillis();
                    while(currentTime == m_oldTime) {
                        currentTime = System.currentTimeMillis();
                    }
                    float dt = (currentTime - m_oldTime);
                    m_oldTime = currentTime;
                    computeAI(dt);
                }
            }
        }
    }

    protected void computeAI(float dt) { }
}
