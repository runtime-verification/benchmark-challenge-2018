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
import java.io.InputStream;

import javax.imageio.ImageIO;

import ca.uqac.lif.pingus.gui.SafeBufferedImage;

/**
 * A game level whose map is created from a specially-design
 * bitmap image.
 * @author Sylvain Hallé
 */
public class BitmapLevel extends Level 
{
  /**
   * A static reference to the color white
   */
	public static final int WHITE = new Color(255, 255, 255).getRGB();
	
	/**
	 * A static reference to the color white
	 */
	public static final int GREEN = new Color(0, 255, 0).getRGB();
	
	/**
	 * The image whose pixel content determines the actual content of
	 * the level
	 */
	protected BufferedImage m_textureMap;
	
	/**
	 * Creates a new bitmap level from an image
	 * @param map The image used
	 */
	public BitmapLevel(BufferedImage map)
	{
		this(map, null);
	}
	
	/**
   * Creates a new bitmap level from an image
   * @param map The image used
   * @param texture An optional texture map
   */
	public BitmapLevel(BufferedImage map, BufferedImage texture)
	{
		super(map.getWidth(), map.getHeight());
		fillFromImage(map);
		m_textureMap = texture;		
	}

	/**
	 * Creates a new level by referring to the name of a built-in level
	 * @param level_name The name of the level. This should refer to the
	 * base name of two bitmap files found in the <code>resource/levels</code>
	 * internal folder:
	 * <ul>
	 * <li><tt>XXX-map.png</tt> contains the map file</li>
	 * <li><tt>XXX-texture.png</tt> contains the texture file</li>
	 * </ul>
	 */
	public BitmapLevel(String level_name) 
	{
		super();
		try 
		{
			BufferedImage img = ImageIO.read(BitmapLevel.class.getResourceAsStream("resource/levels/" + level_name + "-map.png"));
			m_width = img.getWidth();
			m_height = img.getHeight();
			m_content = new Content[m_height][m_width];
			fillRectWith(0, 0, m_width, m_height, Content.NOTHING);
			fillFromImage(img);
			InputStream texture_stream = BitmapLevel.class.getResourceAsStream("resource/levels/" + level_name + "-texture.png");
			if (texture_stream != null)
			{
				m_textureMap = ImageIO.read(texture_stream);
			}
			else
			{
				m_textureMap = null;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Fills the content of the level from the pixels of an image
	 * @param img The image used to fill the level
	 */
	protected void fillFromImage(BufferedImage img)
	{
		for (int i = 0; i < img.getWidth(); i++)
		{
			for (int j = 0; j < img.getHeight(); j++)
			{
				int color = img.getRGB(i, j);
				if (color == GREEN)
				{
					setAt(i, j, Level.Content.GROUND);
				}
				if (color == GRAY)
				{
					setAt(i, j, Level.Content.METAL);
				}
				if (color == RED)
				{
					setAt(i, j, Level.Content.BRICK);
				}
				if (color == WHITE)
				{
					setTrapdoorAt(i, j);
				}
				if (color == YELLOW)
				{
					setGoalAt(i, j);
				}
			}
		}
	}
	
	@Override
	public synchronized BufferedImage draw()
	{
		if (m_textureMap == null)
		{
			// If no texture, use default
			return super.draw();
		}
		// Draw background
		BufferedImage img = new SafeBufferedImage(m_width, m_height, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < m_width; i++)
		{
			for (int j = 0; j < m_height; j++)
			{
				if (m_content[j][i] != Content.NOTHING)
				{
					if (m_content[j][i] == Content.STEP)
					{
						img.setRGB(i, j, YELLOW);
					}
					else
					{
						img.setRGB(i, j, m_textureMap.getRGB(i, j));
					}
				}
				else
				{
					img.setRGB(i, j, BLACK);
				}
			}
		}
		// Draw trapdoor
		Graphics g = img.getGraphics();
		g.drawImage(s_trapdoorSprite, m_trapdoorX - 20, m_trapdoorY - 17, null);
		// Draw goal
		g.drawImage(s_goalSprite, m_goalX - 18, m_goalY - 24, null);
		// Draw Pingus
		for (Pingu p : m_pingus)
		{
			p.draw(img);
			if (p.getId() == getSelectedPingu())
			{
				g.drawImage(s_selectSprite, (int) (p.m_x - 8), (int) (p.m_y - 15), null);
			}
		}
		return img;
	}
}