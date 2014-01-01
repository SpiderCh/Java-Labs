package Utils;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_CN;

/*
 ** Т.к. Integer на каждый чих создаёт новый объект
 *  решено создать такой простой класс.
 *  В результате не происходит копирования каждый раз
 *  и появилась возможность использовать указательна тип инт.
 *  Int a = new Int();
 *  Int b = a;
 *  b.set(7);
 *  a.equals(b) == 7;
 *  Integer бы создал новый объект и записал туда число 7.
 */
public class Int
{
	private int m_value;

	public Int()
	{
		m_value = 0;
	}

	public Int(int val)
	{
		m_value = val;
	}

	public void set(int value)
	{
		m_value = value;
	}

	public void set(Int value)
	{
		if(value == null) {return;}
		m_value = value.m_value;
	}

	public int get()
	{
		return m_value;
	}

	public String toString()
	{
		return String.valueOf(m_value);
	}

	public void fromString(String str) throws java.lang.NumberFormatException
	{
		m_value = Integer.valueOf(str);
	}

	public boolean equals(Int val)
	{
		if(val == null) {
			return false;
		}
		return m_value == val.m_value;
	}

	public boolean equals(int val)
	{
		return m_value == val;
	}
}
