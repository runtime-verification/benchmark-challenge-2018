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
 * A Pingu that is currently in a free fall.
 * 
 * @author Sylvain Hallé
 */
public class Faller extends Pingu 
{
	/**
	 * The rate at which the y velocity increases at each animation step
	 */
	public static float s_acceleration = 0.15f;
	
	/**
	 * The terminal velocity; when a Pingu hits the ground at this velocity,
	 * it dies
	 */
	public static final float s_terminalVelocity = 2;

	/**
	 * Sprites when the Pingu is facing left
	 */
	protected static final BufferedImage[] s_spritesLeft;
	
	/**
	 * Sprites when the Pingu is facing right
	 */
	protected static final BufferedImage[] s_spritesRight;

	static
	{
		int num_sprites = 4;
		s_spritesRight = new BufferedImage[num_sprites];
		s_spritesLeft = new BufferedImage[num_sprites];
		try 
		{
			for (int i = 0; i < num_sprites; i++)
			{
				s_spritesRight[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/faller-%d.png", i)));
				s_spritesLeft[i] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/faller-%d.png", i)));
			}			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new faller
	 */
	public Faller()
	{
		super();
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		setVelocityX(Walker.s_vel);
	}

	/**
   * Creates a new character by copying the state of another one.
   * @param p The other character
   */
	public Faller(Pingu p)
	{
		super(p);
		m_spritesLeft = s_spritesLeft;
		m_spritesRight = s_spritesRight;
		m_x += getVelocityX();
		m_y--; // Deliberately drop by 1 pixel right away; avoids
		// being stuck in a staircase
	}

	@Override
	public String getStatusString() 
	{
		return "FALLER";
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
		// Increment falling velocity
		if (m_velY < s_terminalVelocity)
		{
			m_velY = Math.min(s_terminalVelocity, m_velY + s_acceleration);
		}
		// Check if the Pingu is still over "nothing" for all its width and
		// all the displacement downwards
		float i = m_y;
		for (; i < m_y + m_velY; i++)
		{
			for (int j = 0; j < s_width; j++)
			{
				if (lev.getAt((int) (m_x + j), (int) i) != Content.NOTHING)
				{
					if (fallsTooFast())
					{
						// Hits the ground too fast: dies
						return null;
					}
					// OK: turn back into a walker, put on the ground
					m_y = (int) i;
					return getWalker();
				}
			}			
		}
		// Move Pingu
		m_y += m_velY;
		return this;
	}
	
  /**
   * Gets an instance of walker based on the current Pingu.
   * This is used to turn the current Faller into a Walker at
   * the next cycle of the game loop.
   * @return A walker created from the Faller
   */
	protected Pingu getWalker()
	{
		return new Walker(this);
	}
	
	/**
	 * Determines if the character falls too fast. This is the case
	 * if its vertical speed exceed a fixed value called the
	 * <em>terminal velocity</em>.
	 * @return {@code true} if the character falls to fast, {@code false}
	 * otherwise
	 */
	protected boolean fallsTooFast()
	{
		return Math.abs(m_velY) >= s_terminalVelocity;
	}

}
