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
package ca.uqac.lif.pingus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.uqac.lif.pingus.Level.Content;
import ca.uqac.lif.pingus.characters.Blocker;
import ca.uqac.lif.pingus.util.FileHelper;

/**
 * The main character in the game. Pingus are autonomous creatures that walk
 * on the game field and can be assigned special abilities.
 * @author Sylvain Hallé
 */
public abstract class Pingu
{
	/**
	 * The color green used in the sprites
	 */
	public static final int GREEN = new Color(0, 179, 0).getRGB();
	
	/**
	 * The color "skin" used in the sprites
	 */
	public static final int SKIN = new Color(255, 235, 223).getRGB();
	
	/**
	 * The color "blue" used in the sprites
	 */
	public static final int BLUE = new Color(95, 99, 255).getRGB();
	
	/**
	 * Sprites when the character is facing right
	 */
	protected BufferedImage[] m_spritesRight;
	
	/**
	 * Sprites when the character is facing left
	 */
	protected BufferedImage[] m_spritesLeft;
	
	/**
	 * Sprite representing the letter "R"
	 */
	protected static BufferedImage s_letterR = readImage("resource/sprites/letter-r.png");
	
	/**
	 * Index of the current sprite. This is used to animate the display
	 * by showing successive sprites.
	 */
	protected int m_spriteIndex = 0;
	
	/**
	 * The width (in "pixels") of a Pingu
	 */
	public static final float s_width = 6;
	
	/**
	 * The height (in "pixels") of a Pingu
	 */
	public static final float s_height = 10;
	
	protected static BufferedImage[] s_spriteTimer;
	
	/**
	 * The number of animation steps before a Pingu set to blow
	 * explodes
	 */
	public static int s_blowTimer = 50;
	
	/**
	 * The timer before this Pingu blows. A negative value means no timer
	 * is set
	 */
	protected int m_blowTimer = -1;
	
	/**
	 * The status of the Pingu
	 */
	public static enum Status {BLOCKER, BASHER, WALKER, BLOWER, FALLER, BUILDER, DEAD};
	
	/**
	 * The current status of the Pingu
	 */
	protected Status m_status = Status.WALKER;
	
	/**
	 * x-position of the Pingu. This corresponds to the bottom-left
	 * corner of the Pingu
	 */
	protected float m_x = 0;
	
	/**
	 * y-position of the Pingu. This corresponds to the bottom-left
	 * corner of the Pingu.
	 */
	protected float m_y = 0;
	
	/**
	 * x-velocity of the Pingu
	 */
	private float m_velX = 0;
	
	/**
	 * y-velocity of the Pingu
	 */
	protected float m_velY = 0;
	
	/**
	 * ID of the Pingu
	 */
	private int m_id;
	
	/**
	 * Counter for IDs
	 */
	protected static int s_idCounter = 0;
	
	// Preload the sprites for the pre-blowup digits
	static
	{
		s_spriteTimer = new BufferedImage[5];
		for (int i = 1; i <= 5; i++)
		{
			try 
			{
				s_spriteTimer[i - 1] = ImageIO.read(Pingu.class.getResourceAsStream(String.format("resource/sprites/digit-%d.png", i)));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Creates a new "empty" Pingu
	 */
	public Pingu()
	{
		super();
		setId(s_idCounter++);
	}
	
	/**
	 * Creates a Pingu by copying the contents of another Pingu
	 * @param p The pingu
	 */
	public Pingu(Pingu p)
	{
		super();
		setId(p.getId());
		m_x = p.m_x;
		m_y = p.m_y;
		setVelocityX(p.getVelocityX());
		m_velY = p.m_velY;
		m_blowTimer = p.m_blowTimer;
	}
	
	/**
	 * Resets the counter for giving Pingus IDs
	 */
	public static void resetIdCount()
	{
		s_idCounter = 0;
	}
	
	/**
	 * XML-ises the state of this Pingu
	 * @return An XML structure
	 */
	public StringBuilder toXml()
	{
		StringBuilder out = new StringBuilder();
		out.append("<character>").append(FileHelper.CRLF);
		out.append("  <id>").append(getId()).append("</id>").append(FileHelper.CRLF);
		out.append("  <status>").append(getStatusString()).append("</status>").append(FileHelper.CRLF);
		out.append("  <position>").append(FileHelper.CRLF);
		out.append("    <x>").append(m_x).append("</x>").append(FileHelper.CRLF);
		out.append("    <y>").append(m_y).append("</y>").append(FileHelper.CRLF);
		out.append("  </position>").append(FileHelper.CRLF);
		out.append("  <velocity>").append(FileHelper.CRLF);
		out.append("    <x>").append(getVelocityX()).append("</x>").append(FileHelper.CRLF);
		out.append("    <y>").append(m_velY).append("</y>").append(FileHelper.CRLF);
		out.append("  </velocity>").append(FileHelper.CRLF);
		out.append("</character>").append(FileHelper.CRLF);
		return out;
	}
	
	/**
	 * Sets the horizontal velocity of this Pingu
	 * @param v The velocity
	 * @return This Pingu
	 */
	public Pingu setVelocityX(float v)
	{
		m_velX = v;
		return this;
	}
	
	/**
	 * Sets the vertical velocity of this Pingu
	 * @param v The velocity
	 * @return This Pingu
	 */
	public Pingu setVelocityY(float v)
	{
		m_velY = v;
		return this;
	}

	
	@Override
	public int hashCode()
	{
		return getId();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof Pingu))
		{
			return false;
		}
		Pingu p = (Pingu) o;
		return p.getId() == getId();
	}
	
	/**
	 * Checks if the Pingu collides (i.e. intersects) with another one
	 * @param p The other Pingu
	 * @return true if they collide, false otherwise
	 */
	public boolean collidesWith(Pingu p)
	{
		return Math.abs(m_x - p.m_x) < s_width &&
				Math.abs(m_y - p.m_y) < s_height;
	}
	
	/**
	 * Sets this Pingu as a blower
	 * @return This Pingu
	 */
	public Pingu blow()
	{
		m_blowTimer = s_blowTimer;
		return this;
	}
	
	/**
	 * Sets the position of the Pingu
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return This Pingu
	 */
	public Pingu setPosition(float x, float y)
	{
		m_x = x;
		m_y = y;
		return this;
	}
	
	/**
	 * Gets the status of this Pingu in a string
	 * @return The status
	 */
	public abstract String getStatusString();	
	
	/**
	 * Animates the Pingu for the next animation step
	 * @param lev The level
	 * @param scale Number of frames per second
	 * @param tick Indicates when it is 1/10 of a second
	 * @return If the Pingu changes into another, the new Pingu
	 */
	public abstract Pingu step(final Level lev, float scale, boolean tick);

	/**
	 * Draws the Pingu on the level
	 * @param img The image
	 */
	public synchronized void draw(BufferedImage img)
	{
		Graphics g = img.getGraphics();
		if (getVelocityX() >= 0)
		{
		  // Pingu going right, use sprite facing right
			g.drawImage(m_spritesRight[m_spriteIndex], (int) m_x - 3, (int) (m_y - s_height + 1), null);
		}
		else
		{
		  // Pingu going left, use sprite facing left
			g.drawImage(m_spritesLeft[m_spriteIndex], (int) m_x - 3, (int) (m_y - s_height + 1), null);
		}
		if (m_blowTimer > 0)
		{
		  // Pingu scheduled to explode, put sprite with timer value above
			int timer_index = (m_blowTimer - 1) / 10;
			g.drawImage(s_spriteTimer[timer_index], (int) (m_x), (int) (m_y - s_height - 7), null);
		}
	}
	
	/**
	 * Checks if this Pingu collides with some blocker
	 * @param lev The level
	 * @return true if Pingu collides wit blocker
	 */
	protected boolean collidesWithBlocker(Level lev)
	{
		boolean path_clear = false;
		for (Pingu p : lev.getPingus())
		{
			if (p.equals(this))
				continue;
			if (p.collidesWith(this) && p instanceof Blocker)
			{
				path_clear = true;
				break;
			}
		}
		return path_clear;
	}
	
	/**
	 * Check if the Pingu is still over ground for all its width
	 * @param lev The level
	 * @param depth The depth at which to check
	 * @return false if Pingu will fall
	 */
	protected boolean hasGround(Level lev, int depth)
	{
		for (int j = 0; j < s_width; j++)
		{
			if (lev.getAt((int) (m_x + j), (int) m_y + depth) != Content.NOTHING)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Reads an image from a resource
	 * @param name The filename
	 * @return The image
	 */
	public static BufferedImage readImage(String name)
	{
		BufferedImage img = null;
		try 
		{
			img = ImageIO.read(Pingu.class.getResourceAsStream(name));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * Gets the ID of the Pingu
	 * @return The ID
	 */
	public int getId()
	{
		return m_id;
	}

	/**
	 * Sets the ID of the Pingu
	 * @param id The id
	 */
	public void setId(int id) 
	{
		m_id = id;
	}

	/**
	 * Gets the horizontal velocity of the Pingu
	 * @return The horizontal velocity (in pixels per cycle of the game loop)
	 */
	public float getVelocityX() 
	{
		return m_velX;
	}
}