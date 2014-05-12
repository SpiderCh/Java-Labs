package Simulation;

import Utils.Int;
import Utils.Pair;
import java.awt.*;
import java.io.Serializable;

public abstract class Human implements iBehaviour, Serializable {
    protected int                     m_posX;
    protected int                     m_posY;
	protected int                       m_id;
	protected String                    m_image_path;
	protected Image                     m_image;
    protected int                       m_creationTime;
    protected int                       m_lifeTime;
	protected PersonalType              m_type;

	public Human()
	{
		m_id = -1;
		m_image_path = null;
		m_image = null;
        m_creationTime = -1;
        m_lifeTime = 10;
	}

	public Human(int id)
	{
        m_posX = 0;
        m_posY = 0;
		m_id = id;
		m_image_path = null;
		m_image = null;
        m_creationTime = -1;
        m_lifeTime = 10;
	}

	public Human(int x, int y)
	{
        m_posX = x;
        m_posY = y;
		m_id = -1;
		m_image_path = null;
		m_image = null;
        m_creationTime = -1;
        m_lifeTime = 10;
	}

	public Human(int id, int x, int y)
	{
        m_posX = x;
        m_posY = y;
		m_id = id;
		m_image_path = null;
		m_image = null;
        m_creationTime = -1;
        m_lifeTime = 10;
	}

	public Human(int id, int x, int y, int current_time)
	{
        m_posX = x;
        m_posY = y;
		m_id = id;
		m_image_path = null;
		m_image = null;
        m_creationTime = current_time;
        m_lifeTime = 10;
	}

	public void updatePosition(int x, int y)
	{
        m_posX = x;
        m_posY = y;
	}

	public void setImage(Image image)
	{
		m_image = image;
	}

	@Override
	public void paint(Graphics graphics, Pair<Int, Int> border)
	{
		int width = m_image.getWidth(null);
		int height = m_image.getHeight(null);
		if(width == -1 || height == -1){
			return;
		}
		int offscreen_width = border.first.get() - (m_posX + width);
		int offscreen_height = border.second.get() - (m_posY + height);

		graphics.drawImage(m_image, m_posX, m_posY, null);
		if(offscreen_width < 0 || offscreen_height < 0) {
			if(offscreen_width < 0) {
				offscreen_width = 0 - (width + offscreen_width);
			} else {offscreen_width = m_posX;}
			if(offscreen_height < 0) {
				offscreen_height = 0 - (height + offscreen_height);
			} else {offscreen_height = m_posY;}

			graphics.drawImage(m_image, offscreen_width, offscreen_height, null);
		}
		if(System.getProperty("DEBUG").equals("1")) {
			System.out.println("\tImage:\n\t\tWidth: " + width + "\n\t\tHeight: " + height);
			System.out.println("\t\tScreen Width Position: " + m_posX +
					"\n\t\tScreen Height Position: " + m_posY);
			System.out.println("\t\tOffscreen Width: " + offscreen_width +
					"\n\t\tOffscreen Height: " + offscreen_height);
		}
	}

	@Override
	public abstract void update(int time);

	public int getId()
	{
		return m_id;
	}

	public int getCreationTime()
	{
		return m_creationTime;
	}

	public int getLifeTime()
	{
		return m_lifeTime;
	}

	public void changeLifeTime(int newTime)
	{
		if(System.getProperty("DEBUG").equals("1")) {
			System.out.println("\tChanged life time to: " + newTime);
		}
		m_lifeTime = newTime;
	}

	public PersonalType getType()
	{
		return m_type;
	}
}
