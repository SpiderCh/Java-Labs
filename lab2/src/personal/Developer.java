package personal;

import java.awt.*;

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

    @Override
    public void update(int time)
    {}
}
