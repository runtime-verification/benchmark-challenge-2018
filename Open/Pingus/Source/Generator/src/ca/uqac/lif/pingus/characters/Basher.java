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
 * A Pingu that has the ability to remove earth from walls and
 * dig tunnels.
 * 
 * @author Sylvain Hallé
 */
public class Basher extends Pingu
{
	protected static final BufferedImage[] s_spritesLeft;
	protected static final BufferedImage[] s_spritesRight;
	
	protected int m_bashCycle = 0;
	
	protected int m_pxWithoutBashing = 0;

	static
	{
		int num_sprites = 8;
		s_spritesRight = new BufferedImage[num_sprites];
		s_spritesLeft = new BufferedImage[num_sprites];
		try 
		{
			for (int i = 0; i < num_sprites; i++)
			{
				s_spritesRight[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/basher-%d.png", i)));
				s_spritesLeft[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/basher-flip-%d.png", i)));
			}			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new Basher.
	 */
	public Basher()
	{
		super();
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
	}
	
	/**
   * Creates a new character by copying the state of another one.
   * @param p The other character
   */
	public Basher(Pingu p)
	{
		super(p);
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		m_velY = 0;
		if (p.getVelocityX() >= 0)
		{
			setVelocityX(1);
		}
		else
		{
			setVelocityX(-1);
		}
	}

	@Override
	public String getStatusString() 
	{
		return "BASHER";
	}

	@Override
	public synchronized Pingu step(Level lev, float scale, boolean tick) 
	{
		if (!tick)
			return this; // No update between ticks
		m_spriteIndex = (m_spriteIndex + 1) % m_spritesRight.length;
		if (m_spriteIndex % 4 != 0)
			return this;
		if (m_blowTimer == 0)
		{
			// Explode
			return new Blower(this);
		}
		if (m_blowTimer > 0)
		{
			m_blowTimer--;
		}
		// Check if the Pingu is still over ground for all its width
		if (!hasGround(lev, 1))
		{
			if (!hasGround(lev, 2))
			{
				// Turn this walker into a faller
				return new Faller(this);
			}
			else
			{
				// Drop of just 1 pixel: go down
				m_y++;
			}
		}
		// Check if blocked by blocker
		if (collidesWithBlocker(lev))
		{
			// Yes: turn into walker
			return getWalker();
		}
		// Bash one pixel
		if (getVelocityX() > 0)
		{
			for (int i = 0; i < s_height; i++)
			{
				if (lev.getAt((int) (m_x + s_width), (int) (m_y - i)) == Content.GROUND)
				{
					lev.setAt((int) (m_x + s_width), (int) m_y - i, Content.NOTHING);
				}
			}
		}
		else
		{
			for (int i = 0; i < s_height; i++)
			{
				if (lev.getAt((int) (m_x), (int) (m_y - i)) == Content.GROUND)
				{
					lev.setAt((int) (m_x), (int) m_y - i, Content.NOTHING);
				}
			}
		}
		m_x += getVelocityX(); // Move
		// Check if there is still something to bash
		boolean still_bash = false;
		if (getVelocityX() > 0)
		{
			for (int i = 0; i < s_height; i++)
			{
				if (lev.getAt((int) (m_x + s_width), (int) (m_y - i)) == Content.GROUND)
				{
					still_bash = true;
				}
				if (lev.getAt((int) (m_x + s_width), (int) (m_y - i)) != Content.GROUND && 
						lev.getAt((int) (m_x + s_width), (int) (m_y - i)) != Content.NOTHING)
				{
					// Hit something we can't bash: become walker
					return new Walker(this);
				}
				lev.setAt((int) (m_x + s_width), (int) m_y - i, Content.NOTHING);
			}
		}
		else
		{
			for (int i = 0; i < s_height; i++)
			{
				if (lev.getAt((int) (m_x), (int) (m_y - i)) == Content.GROUND)
				{
					still_bash = true;
				}
				if (lev.getAt((int) (m_x), (int) (m_y - i)) != Content.GROUND && 
						lev.getAt((int) (m_x), (int) (m_y - i)) != Content.NOTHING)
				{
					// Hit something we can't bash: become walker
					return new Walker(this);
				}
				lev.setAt((int) (m_x), (int) m_y - i, Content.NOTHING);
			}
		}
		if (!still_bash)
		{
			m_pxWithoutBashing++;
			if (m_pxWithoutBashing < 5)
			{
				still_bash = true;
			}
			else
			{
				m_pxWithoutBashing = 0;
			}
		}
		else
		{
			// Reset bash count when hitting some ground
			m_pxWithoutBashing = 0;
		}
		if (still_bash)
		{
			return this;
		}
		return getWalker();
	}
	
	/**
	 * Gets an instance of walker based on the current Pingu.
	 * This is used to turn the current Basher into a Walker at
	 * the next cycle of the game loop.
	 * @return A walker created from the Basher
	 */
	public Pingu getWalker()
	{
		return new Walker(this);
	}
}
