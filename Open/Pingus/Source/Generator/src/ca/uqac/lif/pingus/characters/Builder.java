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
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.uqac.lif.pingus.Level;
import ca.uqac.lif.pingus.Level.Content;
import ca.uqac.lif.pingus.Pingu;

/**
 * A Pingu that has the ability to create stairs that reach
 * above gaps.
 * 
 * @author Sylvain Hallé
 */
public class Builder extends Pingu
{
	/**
	 * Sprites when the Pingu is facing left
	 */
	protected static final BufferedImage[] s_spritesLeft;

	/**
	 * Sprites when the Pingu is facing right
	 */
	protected static final BufferedImage[] s_spritesRight;
	
	/**
	 * The width (in pixels) of a step
	 */
	protected static final int s_stepWidth = 6;
	
	/**
	 * The numbe of steps this builder has built
	 */
	protected int m_buildCycles = 0;
	
	/**
	 * The maximum number of steps this builder can build
	 */
	protected static int s_maxSteps = 10;

	static
	{
		int num_sprites = 16;
		s_spritesRight = new BufferedImage[num_sprites];
		s_spritesLeft = new BufferedImage[num_sprites];
		try 
		{
			for (int i = 0; i < num_sprites; i++)
			{
				s_spritesRight[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/builder-%d.png", i)));
				s_spritesLeft[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/builder-flip-%d.png", i)));
			}			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new Builder.
	 */
	public Builder()
	{
		super();
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		setVelocityX(2);
		m_velY = 0;
	}

	/**
   * Creates a new character by copying the state of another one.
   * @param p The other character
   */
	public Builder(Pingu p)
	{
		super(p);
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		m_velY = 0;
		if (p.getVelocityX() >= 0)
		{
			setVelocityX(2);
		}
		else
		{
			setVelocityX(-2);
		}
	}

	@Override
	public String getStatusString()
	{
		return "BUILDER";
	}

	@Override
	public synchronized Pingu step(Level lev, float scale, boolean tick) 
	{
		if (!tick)
			return this;
		m_spriteIndex = (m_spriteIndex + 1) % m_spritesRight.length;
		if (m_spriteIndex % 16 == 9)
		{
			// Add a step
			if (getVelocityX() >= 0)
			{
				for (int i = (int) (m_x + s_width - 4); i < (m_x + s_width - 4 + s_stepWidth); i++)
				{
					lev.setAt(i, (int) m_y, Content.STEP); 
				}
			}
			else
			{
				for (int i = (int) (m_x + 4); i > (m_x + 4 - s_stepWidth); i--)
				{
					lev.setAt(i, (int) m_y, Content.STEP); 
				}
			}
		}
		boolean path_clear = true;
		if (m_spriteIndex % 16 == 0)
		{
			// Move up
			m_x += getVelocityX();
			m_y--;
			m_buildCycles++;
			if (getVelocityX() >= 0)
			{
				for (int i = 0; i < s_height; i++)
				{
					Content c = lev.getAt((int) (m_x + s_width), (int) m_y - i);
					if (c != Content.NOTHING && c != Content.STEP)
					{
						path_clear = false;
						break;
					}				
				}
			}
			else
			{
				for (int i = 0; i < s_height; i++)
				{
					Content c = lev.getAt((int) (m_x), (int) m_y);
					if (c != Content.NOTHING && c != Content.STEP)
					{
						path_clear = false;
						break;
					}				
				}
			}
		}
		if (m_buildCycles >= s_maxSteps || !path_clear)
		{
			// This builder has build enough steps OR encountered
			// an obstacle: turn into a walker
			return new Walker(this);
		}
		return this;
	}
}
