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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.uqac.lif.pingus.Level;
import ca.uqac.lif.pingus.Pingu;

/**
 * A Pingu that has the ability to block other Pingus from walking
 * past.
 * 
 * @author Sylvain Hallé
 */
public class Blocker extends Pingu
{
	protected static final BufferedImage[] s_walkingSprites;
	
	static
	{
		s_walkingSprites = new BufferedImage[1];
		try {
			s_walkingSprites[0] = ImageIO.read(Pingu.class.getResourceAsStream("resource/sprites/blocker-0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new Blocker.
	 */
	public Blocker()
	{
		super();
		setVelocityX(0);
		m_velY = 0;
	}
	
	/**
   * Creates a new character by copying the state of another one.
   * @param p The other character
   */
	public Blocker(Pingu p)
	{
		super(p);
		setVelocityX(0);
		m_velY = 0;
	}

	@Override
	public String getStatusString()
	{
		return "BLOCKER";
	}

	@Override
	public synchronized Pingu step(Level lev, float scale, boolean tick) 
	{
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
					return new Faller(this);	
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
		return this;
	}
	
	
	@Override
	public void draw(BufferedImage img)
	{
		Graphics g = img.getGraphics();
		g.drawImage(s_walkingSprites[0], (int) m_x, (int) (m_y - s_height + 1), null);
	}
}
