package Simulation;

import Utils.Int;
import Utils.Pair;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class Habitat
{
	private final Pair<Int, Int> m_workingAreaSize;
	private float          m_simulationTime;
	private boolean        m_isRunning;
	private boolean        m_showTime;

	private final Pair<Int, Int> m_employeesCounter;
	private final Pair<Int, Int> m_periods;
	private final Pair<Int, Int> m_lifeTimes;
	private float          m_developerPossibility;
	private float          m_maxPercentOfManagers;

	private final LinkedList<Human>         m_personalList    = new LinkedList<>();
	private final TreeSet<Integer>          m_ids             = new TreeSet<>();
	private final HashMap<Integer, Integer> m_curentPersonal  = new HashMap<>();

	private Image m_dev_image;
	private Image m_man_image;

    private DeveloperAI m_devAIThread = new DeveloperAI(m_personalList, 10.0f, 10);
    private ManagerAI   m_manAIThread = new ManagerAI(m_personalList, 0.3f, 20);

	private void initVars()
	{
		m_simulationTime = 0;
		m_isRunning = false;
		m_showTime = true;
		m_developerPossibility = .5f;
		m_maxPercentOfManagers = .6f;
		try {
			m_dev_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/linux_logo.png"));
			m_man_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/win_logo.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

        m_devAIThread.setState(1);
        m_devAIThread.setState(2);

        m_manAIThread.setState(1);
        m_manAIThread.setState(2);
	}

	private void printResult(Graphics graphics)
	{
		int x = 20;
		int y = 10;
		int count = m_employeesCounter.first.get() + m_employeesCounter.second.get();
		graphics.setColor(Color.BLACK);
		graphics.drawString("Results:", x + 5, y + 5);
		graphics.drawString("Number of created objects: " + count, x + 5, y + 20);
		graphics.drawString("Number of Developers: " + m_employeesCounter.first.toString(), x + 5, y + 35);
		graphics.drawString("Number of Managers: " + m_employeesCounter.second.toString(), x + 5, y + 50);
		graphics.drawString("Elapsed time: " + m_simulationTime, x + 5, y + 65);
	}

	public Habitat()
	{
		m_workingAreaSize = new Pair<>(new Int(818), new Int(680));
		m_employeesCounter = new Pair<>(new Int(), new Int());
		m_periods = new Pair<>(new Int(5), new Int(9));
		m_lifeTimes = new Pair<>(new Int(10), new Int(11));
		initVars();
	}

	public void update(float time)
	{
		m_simulationTime = time;
        synchronized (m_personalList) {
            removeOldObjects();
            createDeveloper();
            createManager();
        }
	}


    public void paint(Graphics graphics)
    {
        if (m_isRunning) {
            Iterator<Human> it = m_personalList.iterator();
            while (it.hasNext()) {
                Human h = it.next();
                h.paint(graphics, m_workingAreaSize);
            }

            if (m_showTime) {
                graphics.setColor(Color.RED);
                String str = "Simulation time = " + m_simulationTime + " sec.";
                int x = 10;
                int y = 15;
                graphics.drawString(str, x, y);
            }
        } else {
            printResult(graphics);
        }
    }

	public void showTime(boolean state)
	{
		m_showTime = state;
	}

    public void showTime()
    {
        m_showTime = true;
    }

    public void hideTime()
    {
        m_showTime = false;
    }

	public void start()
	{
		m_simulationTime = 0;
		m_isRunning = true;
        m_simulationTime = 0;
        m_employeesCounter.first.set(0);
        m_employeesCounter.second.set(0);
	}

	public void stop()
	{
		m_isRunning = false;
		m_personalList.clear();
		m_ids.clear();
		m_curentPersonal.clear();
        m_devAIThread.setState(2);
        m_manAIThread.setState(2);
	}

	public void setDevCreationPeriod(int time)
	{
        m_periods.first.set(time);
    }

	public void setManagerCreationPeriod(int time)
	{
        m_periods.second.set(time);
    }

	public void setDevLifeTime(int time)
	{
        m_lifeTimes.first.set(time);
        synchronized (m_personalList) {
            Iterator<Human> it = m_personalList.iterator();
            while (it.hasNext()) {
                Human emp = it.next();
                if (emp.getType() == PersonalType.Developer) {
                    emp.changeLifeTime(time);
                }
            }
        }
    }

	public void setManagerLifeTime(int time)
	{
        m_lifeTimes.second.set(time);
        synchronized (m_personalList) {
            Iterator<Human> it = m_personalList.iterator();
            while (it.hasNext()) {
                Human emp = it.next();
                if (emp.getType() == PersonalType.Manager) {
                    emp.changeLifeTime(time);
                }
            }
        }
    }

	public void setDevPossibility(int possibility)
	{
        m_developerPossibility = possibility;
    }

	public void setMaxNumOfManagers(int maxNum)
	{
        m_maxPercentOfManagers = maxNum;
    }

	public void setDevThreadPriority(int priority)
	{
        m_devAIThread.setPriority(priority);
    }

	public void setManagerThreadPriority(int priority)
	{
        m_devAIThread.setPriority(priority);
    }

    public void stopDevThread()
    {
        m_devAIThread.setState(2);
    }

    public void resumeDevThread()
    {
        m_devAIThread.setState(3);
    }

    public void stopManThread()
    {
        m_manAIThread.setState(2);
    }

    public void resumeManThread()
    {
        m_manAIThread.setState(3);
    }

    private void removeOldObjects()
    {
            Iterator<Human> it = m_personalList.iterator();
            while (it.hasNext()) {
                Human emp = it.next();
                if (m_simulationTime - emp.getCreationTime() >= emp.getLifeTime()) {
                    m_ids.remove(emp.getId());
                    m_curentPersonal.remove(emp.getId());
                    it.remove();
                }
            }
    }

    private void createDeveloper()
    {
        int dev_update = (int) m_simulationTime % m_periods.first.get();
        int pos_x = 0;
        int pos_y = 0;
        int id = m_employeesCounter.first.get() + m_employeesCounter.second.get();
        if(dev_update == 0) {
            if(Math.random() <= m_developerPossibility) {
                    do {
                        pos_x = (int) (Math.random() * m_workingAreaSize.first.get());
                    } while (pos_x == 0);
                    do {
                        pos_y = (int) (Math.random() * m_workingAreaSize.second.get());
                    } while (pos_y == 0);

                    Human new_person = new Developer(id, pos_x, pos_y, (int) m_simulationTime);
                    new_person.setImage(m_dev_image);
                    new_person.changeLifeTime(m_lifeTimes.first.get());
                    m_personalList.add(new_person);
                    m_employeesCounter.first.set(m_employeesCounter.first.get() + 1);
                    m_ids.add(id);
                    m_curentPersonal.put(id, (int) m_simulationTime);
                }
            }
    }

    private void createManager()
    {
        int man_update = (int) m_simulationTime % m_periods.second.get();
        int pos_x = 0;
        int pos_y = 0;
        int id = m_employeesCounter.first.get() + m_employeesCounter.second.get();
        if(man_update == 0) {
            boolean man_percent = false;
            if(m_employeesCounter.first.get() > 0) {
                man_percent = (m_employeesCounter.second.get()/(float)m_employeesCounter.first.get() < m_maxPercentOfManagers);
            }
            if(man_percent) {
                    do {
                        pos_x = (int) (Math.random() * m_workingAreaSize.first.get());
                    } while (pos_x == 0);
                    do {
                        pos_y = (int) (Math.random() * m_workingAreaSize.second.get());
                    } while (pos_y == 0);

                    Human new_person = new Manager(id, pos_x, pos_y, (int) m_simulationTime);
                    new_person.setImage(m_man_image);
                    new_person.changeLifeTime(m_lifeTimes.second.get());
                    m_personalList.add(new_person);
                    m_employeesCounter.second.set(m_employeesCounter.second.get() + 1);
                    m_ids.add(id);
                    m_curentPersonal.put(id, (int) m_simulationTime);
                }
            }
    }

    public String getResultStr()
    {
        int count = m_employeesCounter.first.get() + m_employeesCounter.second.get();
        String result = "Number of created objects: " + count +
                "\nNumber of developers: " + m_employeesCounter.first +
                "\nNumber of managers: " + m_employeesCounter.second +
                "\nSimulation time: " + m_simulationTime;
        return result;
    }

    public HashMap<Integer, Integer> getCurrentPersonalTable()
    {
        return new HashMap<>(m_curentPersonal);
    }
}
