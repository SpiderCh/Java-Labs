package Simulation;

import Utils.Int;
import Utils.Pair;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by chas on 06.01.14.
 */
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

	private final LinkedList<Human>         m_personalList;
	private final TreeSet<Integer>          m_ids;
	private final HashMap<Integer, Integer> m_curentPersonal;

	private Image m_dev_image;
	private Image m_man_image;

	private void initVars()
	{
		m_simulationTime = 0;
		m_isRunning = false;
		m_showTime = true;
		m_developerPossibility = .5f;
		m_maxPercentOfManagers = .3f;
		try {
			m_dev_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/linux_logo.png"));
			m_man_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/win_logo.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printResult(Graphics graphics)
	{
		int x = 20;
		int y = 10;
		graphics.drawString("Nothing happens", 0, 15);
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
		m_workingAreaSize = new Pair<>(new Int(), new Int());
		m_employeesCounter = new Pair<>(new Int(), new Int());
		m_periods = new Pair<>(new Int(), new Int());
		m_lifeTimes = new Pair<>(new Int(), new Int());
		m_personalList = new LinkedList<>();
		m_ids = new TreeSet<>();
		m_curentPersonal = new HashMap<>();
		initVars();
	}

	public void update(float time)
	{
		m_simulationTime = time;
//		removeOldObjects();
//		createDeveloper();
//		createManager();
	}

	public void paint(Graphics graphics)
	{
		if (m_isRunning) {
//			Iterator<Human> it = m_personal_list.iterator();
//			while (it.hasNext()) {
//				Human h = it.next();
//				h.paint(graphics, m_working_area_size);
//			}

			if (m_showTime) {
				graphics.setColor(Color.RED);
				String str = "Simulation time = " + m_simulationTime + " sec.";
				int x = 11;
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

	public void start()
	{
		m_simulationTime = 0;
		m_isRunning = true;
	}

	public void stop()
	{
		m_isRunning = false;
	}

	public void setDevCreationPeriod(int time)
	{}

	public void setManagerCreationPeriod(int time)
	{}

	public void setDevLifeTime(int time)
	{}

	public void setManagerLifeTime(int time)
	{}

	public void setDevPossibility(int time)
	{}

	public void setMaxNumOfManagers(int time)
	{}

	public void setDevThreadPriority(int priority)
	{}

	public void setManagerThreadPriority(int priority)
	{}
}
