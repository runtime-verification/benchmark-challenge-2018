/*
  Pingu Generator, simulates the execution of the Pingus video game
  Copyright (C) 2016-2018 Sylvain Hallé

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.pingus.characters;

import java.awt.image.BufferedImage;

import ca.uqac.lif.pingus.Level;
import ca.uqac.lif.pingus.Level.Content;
import ca.uqac.lif.pingus.Pingu;

/**
 * A Pingu that is scheduled to explode after five seconds.
 * 
 * @author Sylvain Hallé
 */
public class Blower extends Pingu 
{
	/**
	 * The half size (width and height) of the hole around the
	 * Pingu when it blows
	 */
	public static final int s_holeSize = 30;

	/**
	 * Counts how many ticks have elapsed since the blower was created
	 */
	protected int m_tickCount = 0;

	/**
	 * Creates a new Blower.
	 */
	public Blower()
	{
		super();
	}

	/**
   * Creates a new character by copying the state of another one.
   * @param p The other character
   */
	public Blower(Pingu p)
	{
		super(p);
	}

	@Override
	public String getStatusString() 
	{
		return "BLOWER";
	}

	@Override
	public synchronized Pingu step(Level lev, float scale, boolean tick)
	{
		// The blower lives for ten animation steps and dies
		// However, it creates a square hole around him
		if (tick && m_tickCount == 0)
		{
			float mid_x = m_x + (s_width / 2);
			float mid_y = m_y - (s_height / 2);
			for (float x = mid_x - s_holeSize; x <= mid_x + s_holeSize; x++)
			{
				for (float y = mid_y - s_holeSize; y <= mid_y + s_holeSize; y++)
				{
					if (Math.pow(x - mid_x, 2) + Math.pow(y - mid_y, 2) < Math.pow(s_holeSize / 2, 2))
					{
						lev.setAt((int) x, (int) y, Content.NOTHING);
					}
				}
			}
		}
		m_tickCount++;
		if (m_tickCount == 10)
		{
			// Die after 10 ticks
			return null;
		}
		return this;
	}

	@Override
	public void draw(BufferedImage img) 
	{
		// Show debris
		img.setRGB((int) m_x + m_tickCount, (int) m_y + m_tickCount, Level.WHITE);
		img.setRGB((int) m_x - m_tickCount, (int) m_y + m_tickCount, Level.WHITE);
		img.setRGB((int) m_x + m_tickCount, (int) m_y - m_tickCount, Level.WHITE);
		img.setRGB((int) m_x - m_tickCount, (int) m_y - m_tickCount, Level.WHITE);
	}
}
