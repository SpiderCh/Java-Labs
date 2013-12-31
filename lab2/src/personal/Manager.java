package personal;

public class Manager extends Human {

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

    @Override
    public void update(int time)
    {}
}
