package Simulation;

public class Manager extends Human {
    protected float m_xCenter = 0;
    protected float m_yCenter = 0;

	public Manager(String path)
	{
		super();
	}

	public Manager(int id, String path)
	{
		super(id);
	}

	public Manager(int x, int y)
	{
		super(x, y);
	}

	public Manager(int x, int y, String Path)
	{
		super(x, y);
	}

	public Manager(int id, int x, int y, String Path)
	{
		super(id, x, y);
	}

	public Manager(int id, int x, int y, int current_time) {
		super(id, x, y, current_time);
		m_type = PersonalType.Manager;
	}

	@Override
	public void update(int time)
	{}
}