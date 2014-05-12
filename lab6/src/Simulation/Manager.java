package Simulation;

import java.io.Serializable;

public class Manager extends Human implements Serializable {
    protected float m_xCenter = 0;
    protected float m_yCenter = 0;

	public Manager(String path)
	{
		super();
        m_type = PersonalType.Manager;
	}

	public Manager(int id, String path)
	{
		super(id);
        m_type = PersonalType.Manager;
	}

	public Manager(int x, int y)
	{
		super(x, y);
        m_xCenter = x;
        m_yCenter = y;
        m_type = PersonalType.Manager;
	}

	public Manager(int x, int y, String Path)
	{
		super(x, y);
        m_xCenter = x;
        m_yCenter = y;
        m_type = PersonalType.Manager;
	}

	public Manager(int id, int x, int y, String Path)
	{
		super(id, x, y);
        m_xCenter = x;
        m_yCenter = y;
        m_type = PersonalType.Manager;
	}

	public Manager(int id, int x, int y, int current_time) {
		super(id, x, y, current_time);
        m_xCenter = x;
        m_yCenter = y;
		m_type = PersonalType.Manager;
	}

	@Override
	public void update(int time)
	{}
}