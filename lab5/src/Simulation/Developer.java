package Simulation;

public class Developer extends Human {

	public Developer(String path)
	{
		super();
	}

	public Developer(int id, String path)
	{
		super(id);
	}

	public Developer(int x, int y)
	{
		super(x, y);
	}

	public Developer(int x, int y, String Path)
	{
		super(x, y);
	}

	public Developer(int id, int x, int y, String Path)
	{
		super(id, x, y);
	}

	public Developer(int id, int x, int y, int current_time)
	{
		super(id, x, y, current_time);
		m_type = PersonalType.Developer;
	}

	@Override
	public void update(int time)
	{}
}