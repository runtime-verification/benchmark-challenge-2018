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
 * A Pingu with no specific ability. It simply walks in one direction,
 * and turns around when it encounters an obstacle.
 * 
 * @author Sylvain Hallé
 */
public class Walker extends Pingu 
{
	/**
	 * The default walking velocity for all Pingus
	 */
	public static float s_vel = 1;

	/**
	 * This Pingu's walking velocity
	 */
	protected float m_vel = s_vel;
	
	/**
	 * Array of sprites when the Pingu is facing left
	 */
	protected static final BufferedImage[] s_spritesLeft;
	
	/**
	 * Array of sprites when the Pingu is facing right
	 */
	protected static final BufferedImage[] s_spritesRight;

	/*
	 * Iniitalizes the array of sprites 
	 */
	static
	{
		int num_sprites = 8;
		s_spritesRight = new BufferedImage[num_sprites];
		s_spritesLeft = new BufferedImage[num_sprites];
		try 
		{
			for (int i = 0; i < num_sprites; i++)
			{
				s_spritesRight[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/walker-%d.png", i)));
				s_spritesLeft[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/walker-flip-%d.png", i)));
			}			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new walker.
	 */
	public Walker()
	{
		super();
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		m_velY = 0;
		setVelocityX(s_vel);
	}

	/**
	 * Creates a new character by copying the state of another one.
	 * @param p The other character
	 */
	public Walker(Pingu p)
	{
		super(p);
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		if (!(p instanceof Walker))
		{
			// Inherit walking direction from other Pingu
			m_velY = 0;
			if (p.getVelocityX() > 0)
			{
				setVelocityX(s_vel);
			}
			else
			{
				setVelocityX(-s_vel);
			}
		}
	}

	@Override
	public String getStatusString() 
	{
		return "WALKER";
	}

	@Override
	public synchronized Pingu step(Level lev, float scale, boolean tick)
	{
		if (tick)
			m_spriteIndex = (m_spriteIndex + 1) % m_spritesRight.length;
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
				if (!hasGround(lev, 3))
				{
					// Turn this walker into a faller
					return getFaller();
				}
				else
				{
					// Drop of just 2 pixels: go down
					m_y += 2;
				}
			}
			else
			{
				// Drop of just 1 pixel: go down
				m_y++;
			}
		}
		// Check if walker collides with blocker
		boolean path_clear = !collidesWithBlocker(lev);
		// Check if the next pixel in the direction of walking
		// is empty for all the height of the Pingu
		int climb = 0;
		if (getVelocityX() >= 0)
		{
			for (int j = (int) m_x; j < m_x + getVelocityX(); j++)
			{
				if (lev.getAt((int) (j + s_width), (int) m_y - climb) != Content.NOTHING)
				{
					climb++;
					if (lev.getAt((int) (j + s_width), (int) m_y - climb) != Content.NOTHING)
					{
						climb++;
					}
				}
				for (int i = 0; i < s_height; i++)
				{
					Content c = lev.getAt((int) (j + s_width), (int) m_y - climb - i);
					if (c != Content.NOTHING && c != Content.STEP)
					{
						path_clear = false;
						climb = 0;
						break;
					}				
				}				
			}		
		}
		else
		{
			for (int j = (int) m_x; j > m_x + getVelocityX(); j--)
			{
				if (lev.getAt((int) (j), (int) m_y - climb) != Content.NOTHING)
				{
					climb++;
					if (lev.getAt((int) (j), (int) m_y - climb) != Content.NOTHING)
					{
						climb++;
					}
				}
				for (int i = 0; i < s_height; i++)
				{
					Content c = lev.getAt((int) (j), (int) m_y - climb - i);
					if (c != Content.NOTHING && c != Content.STEP)
					{
						path_clear = false;
						climb = 0;
						break;
					}				
				}		
			}					
		}
		if (!path_clear) // Path is blocked: Pingu turns around
		{
			setVelocityX(-getVelocityX());
		}
		m_x += getVelocityX();
		m_y -= climb;
		return this;
	}
	
  /**
   * Gets an instance of Faller based on the current Pingu.
   * This is used to turn the current Walker into a Faller at
   * the next cycle of the game loop.
   * @return A Fallercreated from the Walker
   */
	protected Faller getFaller()
	{
		return new Faller(this);
	}
}
