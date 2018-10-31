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

import ca.uqac.lif.pingus.characters.Basher;
import ca.uqac.lif.pingus.characters.Blocker;
import ca.uqac.lif.pingus.characters.Builder;
import ca.uqac.lif.pingus.characters.Faller;
import ca.uqac.lif.pingus.characters.HiddenPingu;
import ca.uqac.lif.pingus.characters.Walker;
import ca.uqac.lif.pingus.gui.SafeBufferedImage;
import ca.uqac.lif.pingus.util.FileHelper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * Representation of a level in the game. A level is a rectangular matrix,
 * each cell of which can either be: earth, water, metal, brick or nothing.
 * A basic level in the game also has:
 * <ul>
 * <li>a <strong>trapdoor</strong> where the Pingus enter the level</li>
 * <li>a <strong>goal</strong> where the Pingus exit the level</li>
 * </ul>
 * @author Sylvain Hallé
 */
public class Level
{
  /**
   * The set of Pingus present in that level.
   */
  protected Set<Pingu> m_pingus;

  /**
   * The currently selected Pingu.
   */
  private int m_selectedPingu = -1;

  /**
   * The number of safe Pingus.
   */
  protected int m_safePingus = 0;

  /**
   * The sprite for the trapdoor.
   */
  protected static BufferedImage s_trapdoorSprite;

  /**
   * The sprite for the goal.
   */
  protected static BufferedImage s_goalSprite;

  /**
   * The sprite for the selection rectangle.
   */
  protected static BufferedImage s_selectSprite;

  /**
   * The bitmap tiling for earth.
   */
  protected static BufferedImage s_earthTile;

  /**
   * The print stream to send the trace, if any
   */
  protected PrintStream m_traceStream = null;

  /**
   * The number of Pingus injected into the level so far
   */
  protected int m_injectedPingus = 0;

  /**
   * A recording of all the user-initiated actions when playing the level
   */
  protected PianoRoll m_pianoRoll = new PianoRoll();

  /**
   * The total number of Pingus to inject at the start of the level
   */
  protected int m_totalPingus = 10;

  /**
   * The number of ticks in the level so far
   */
  protected int m_loopCount = 0;

  /**
   * The number of steps (not ticks) so far
   */
  protected long m_stepCount = 0;

  /**
   * The number of ticks it takes to release a new Pingu
   */
  protected int m_releaseRate = 10;

  /**
   * The type of "element" present in a square of the level
   */
  public static enum Content
  {
    NOTHING, GROUND, ROCK, BRICK, WATER, STEP, METAL, DEBRIS
  }

  // A few colors
  public static final int BROWN = new Color(196, 128, 128).getRGB();
  public static final int BLACK = new Color(0, 0, 48).getRGB();
  public static final int BLUE = new Color(0, 0, 255).getRGB();
  public static final int GRAY = new Color(128, 128, 128).getRGB();
  public static final int YELLOW = new Color(255, 255, 0).getRGB();
  public static final int WHITE = new Color(255, 255, 255).getRGB();
  public static final int RED = new Color(255, 0, 0).getRGB();

  /**
   * The content of each square of the level
   */
  protected Content[][] m_content = null;

  /**
   * The number of frames per second of the level's animation
   */
  protected float m_scale = 10;

  /**
   * The level's width
   */
  protected int m_width = 0;

  /**
   * The level's height
   */
  protected int m_height = 0;

  /**
   * x-position of the trapdoor
   */
  protected int m_trapdoorX;

  /**
   * y-position of the trapdoor
   */
  protected int m_trapdoorY;

  /**
   * x-position of the goal
   */
  protected int m_goalX;

  /**
   * y-position of the goal
   */
  protected int m_goalY;

  static
  {
    try 
    {
      s_trapdoorSprite = ImageIO.read(
          Level.class.getResourceAsStream("resource/sprites/trapdoor.png"));
      s_goalSprite = ImageIO.read(
          Level.class.getResourceAsStream("resource/sprites/goal.png"));
      s_selectSprite = ImageIO.read(
          Level.class.getResourceAsStream("resource/sprites/selection.png"));
      s_earthTile = ImageIO.read(
          Level.class.getResourceAsStream("resource/sprites/earth-tile.png"));
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }
  }

  public Level()
  {
    super();
    m_pingus = new HashSet<Pingu>();
  }

  /**
   * Instantiates a new level
   * @param width The width
   * @param height The height
   */
  public Level(int width, int height)
  {
    super();
    m_pingus = new HashSet<Pingu>();
    m_width = width;
    m_height = height;
    m_content = new Content[height][width];
    fillRectWith(0, 0, width, height, Content.NOTHING);
  }

  /**
   * Fills a rectangle in the game level with the specified content
   * @param x x coordinate of the top-left corner of the rectangle
   * @param y y coordinate of the top-left corner of the rectangle
   * @param width Width of the rectangle
   * @param height Height of the rectangle
   * @param c Content to fill with
   * @return This level
   */
  public Level fillRectWith(int x, int y, int width, int height, Content c)
  {
    for (int i = Math.max(0, x); i < Math.min(m_width, x + width); i++)
    {
      for (int j = Math.max(0, y); j < Math.min(m_height, y + height); j++)
      {
        m_content[j][i] = c;
      }
    }
    return this;
  }

  /**
   * Returns what is at a specific square of the level
   * @param x x coordinate
   * @param y y coordinate
   * @return The content
   */
  public Content getAt(int x, int y)
  {
    if (x < 0 || x >= m_width)
    {
      return null;
    }
    if (y < 0 || y >= m_height)
    {
      return null;
    }
    return m_content[y][x];
  }

  /**
   * Returns what is at a specific square of the level
   * @param x x coordinate
   * @param y y coordinate
   * @param c The content
   * @return This level
   */
  public Level setAt(int x, int y, Content c)
  {
    if (x < 0 || x >= m_width)
    {
      return this;
    }
    if (y < 0 || y >= m_height)
    {
      return this;
    }
    m_content[y][x] = c;
    return this;
  }

  /**
   * Creates an XML rendition of the current state of the level
   * @return An XML string
   */
  public StringBuilder toXml()
  {
    StringBuilder out = new StringBuilder();
    out.append("<message>").append(FileHelper.CRLF);
    out.append("<timestamp>").append(System.currentTimeMillis()).append("</timestamp>")
    .append(FileHelper.CRLF);
    out.append("<characters>").append(FileHelper.CRLF);
    for (Pingu p : m_pingus)
    {
      out.append(p.toXml());
    }
    out.append("</characters>").append(FileHelper.CRLF);
    out.append("</message>").append(FileHelper.CRLF);
    return out;
  }

  /**
   * Steps the animation of this level
   */
  public synchronized void step(boolean tick)
  {
    if (m_stepCount == 0)
    {
      // Inject all Pingus as hidden
      for (int i = 0; i < m_totalPingus; i++)
      {
        HiddenPingu h = new HiddenPingu();
        h.setPosition(m_trapdoorX, m_trapdoorY);
        add(h);
      }
    }
    m_stepCount++;
    if (tick)
    {
      if (m_injectedPingus < m_totalPingus)
      {
        if (m_loopCount % m_releaseRate == 0)
        {
          injectPingu();
          m_injectedPingus++;
        }
        m_loopCount++;
      }
    }
    Iterator<Pingu> p_it = m_pingus.iterator();
    Set<Pingu> new_pingus = new HashSet<Pingu>();
    while (p_it.hasNext())
    {
      Pingu old_p = p_it.next();
      Pingu new_p = old_p.step(this, m_scale, tick);
      if (reachedGoal(new_p))
      {
        addSafePingu();
        new_p = null;
      }
      if (new_p != null)
      {
        new_pingus.add(new_p);
      }
    }
    m_pingus = new_pingus;
    if (m_traceStream != null)
    {
      m_traceStream.print(toXml());
    }
  }

  /**
   * Gets the first Pingu found at a given position in the level
   * @param x x-coordinate
   * @param y y-coordinate
   * @return The Pingu, null if not found
   */
  public synchronized Pingu getPinguAt(int x, int y)
  {
    Pingu out = null;
    for (Pingu p : m_pingus)
    {
      // -3 to compensate for the fact that the sprite is offset
      // by 3 px
      if (x >= p.m_x - 3 && x <= p.m_x + Pingu.s_width - 3 &&
          y <= p.m_y && y >= p.m_y - Pingu.s_height)
      {
        out = p;
        break;
      }
    }
    return out;
  }

  /**
   * Turns a Pingu into something
   * @param id The ID of the Pingu
   * @param status The new status of this Pingu
   */
  public synchronized void turnInto(int id, Pingu.Status status)
  {
    Iterator<Pingu> it_p = m_pingus.iterator();
    while (it_p.hasNext())
    {
      Pingu new_p = null;
      Pingu p_c = it_p.next();
      if (p_c.getId() != id)
      {
        continue;
      }
      switch (status)
      {
      case BASHER:
        new_p = new Basher(p_c);
        break;
      case BLOCKER:
        new_p = new Blocker(p_c);
        break;
      case BLOWER:
        new_p = p_c;
        new_p.blow();
        break;
      case BUILDER:
        new_p = new Builder(p_c);
        break;
      case DEAD:
        new_p = null;
        break;
      case FALLER:
        new_p = new Faller(p_c);
        break;
      case WALKER:
        new_p = new Walker(p_c);
        break;
      default:
        break;
      }
      it_p.remove();
      m_pingus.add(new_p);
      m_pianoRoll.add(new PianoRoll.PianoEvent(m_stepCount, id, new_p.getClass()));
      break;
    }
  }

  /**
   * Turns a Pingu into something
   * @param id The ID of the Pingu
   * @param p The Pingu to replace it with
   */
  public synchronized void turnInto(int id, Pingu p)
  {
    Iterator<Pingu> it_p = m_pingus.iterator();
    while (it_p.hasNext())
    {
      Pingu p_c = it_p.next();
      if (p_c.getId() != id)
      {
        continue;
      }
      it_p.remove();
      m_pingus.add(p);
      break;
    }
    m_pianoRoll.add(new PianoRoll.PianoEvent(m_stepCount, id, p.getClass()));
  }

  /**
   * Gets the Pingu with specified ID
   * @param id The ID
   * @return The Pingu, null if not found
   */
  public synchronized Pingu getPingu(int id)
  {
    for (Pingu p : m_pingus)
    {
      if (p.getId() == id)
      {
        return p;
      }
    }
    return null;
  }

  /**
   * Selects a Pingu
   * @param id The ID of the Pingu
   * @return This level
   */
  public Level select(int id)
  {
    setSelectedPingu(id);
    return this;
  }

  /**
   * Unselects a Pingu
   * @return This level
   */
  public Level unselect()
  {
    setSelectedPingu(-1);
    return this;
  }

  /**
   * Sets the number of frames per second
   * @param r The rate
   * @return This level
   */
  public Level setRate(float r)
  {
    m_scale = r;
    Walker.s_vel = 10f / r;
    Faller.s_acceleration = 10f / r * 0.15f;//(float) Math.sqrt(10f / r) * 0.3f;
    return this;
  }

  /**
   * Gets the set of Pingus
   * @return The set of Pingus
   */
  public Set<Pingu> getPingus()
  {
    return m_pingus;
  }

  /**
   * Creates a drawing out of the current state of the level
   * @return The image
   */
  public synchronized BufferedImage draw()
  {
    // Draw background
    BufferedImage img = new SafeBufferedImage(m_width, m_height, BufferedImage.TYPE_INT_ARGB);
    for (int i = 0; i < m_width; i++)
    {
      for (int j = 0; j < m_height; j++)
      {
        switch(m_content[j][i])
        {
        case GROUND:
          img.setRGB(i, j, s_earthTile.getRGB(i % 124, j % 58));
          //img.setRGB(i, j, BROWN);
          break;
        case METAL:
          img.setRGB(i, j, BLUE);
          break;
        case NOTHING:
          img.setRGB(i, j, BLACK);
          break;
        case ROCK:
          img.setRGB(i, j, GRAY);
          break;
        case WATER:
          img.setRGB(i, j, BLUE);
          break;
        case STEP:
          img.setRGB(i, j, YELLOW);
          break;
        case DEBRIS:
          img.setRGB(i, j, WHITE);
          break;
        case BRICK:
          img.setRGB(i, j, RED);
          break;
        default:
          img.setRGB(i, j, BLACK);
          break;
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

  /**
   * Adds a Pingu to this level
   * @param p The Pingu
   * @return This level
   */
  public Level add(Pingu p)
  {
    m_pingus.add(p);
    return this;
  }

  /**
   * Sets the position of the trapdoor
   * @param x The x position
   * @param y The y position
   * @return This level
   */
  public Level setTrapdoorAt(int x, int y)
  {
    m_trapdoorX = x;
    m_trapdoorY = y;
    return this;
  }

  /**
   * Sets the position of the goal
   * @param x The x position
   * @param y The y position
   * @return This level
   */
  public Level setGoalAt(int x, int y)
  {
    m_goalX = x;
    m_goalY = y;
    return this;
  }

  /**
   * Increments the count of safe Pingus
   * @return This level
   */
  public Level addSafePingu()
  {
    m_safePingus++;
    return this;
  }

  /**
   * Checks if a Pingu has reached the goal
   * @param p The Pingu
   * @return true if within the goal area
   */
  public boolean reachedGoal(Pingu p)
  {
    if (p == null)
    {
      return false;
    }
    return Math.abs(p.m_x - m_goalX) < 10
        && Math.abs(p.m_y - m_goalY) < 10;
  }

  /**
   * Turns the next hidden Pingu into a faller
   * @return This level
   */
  public synchronized Level injectPingu()
  {
    Pingu p = getPingu(m_injectedPingus);
    turnInto(m_injectedPingus, new Faller(p));
    return this;
  }

  /**
   * Prepares the level for closing
   */
  public void close()
  {
    if (m_traceStream != null)
    {
      m_traceStream.println("</trace>");
      m_traceStream.close();
    }

    System.out.println(m_pianoRoll);
  }

  /**
   * Sets a stream to print an output trace
   * @param ps The print stream
   */
  public void setStream(PrintStream ps)
  {
    m_traceStream = ps;
    //ps.println("<trace>");
  }

  /**
   * Sets the total number of Pingus in this level
   * @param count The number
   * @return This level
   */
  public Level setPinguCount(int count)
  {
    m_totalPingus = count;
    return this;
  }

  /**
   * Gets the ID of the Pingu that is currently selected
   * @return The ID of the selected Pingu, -1 if Pingu is selected
   */
  public int getSelectedPingu()
  {
    return m_selectedPingu;
  }

  /**
   * Sets the ID of the currently selected Pingu
   * @param selected_pingu The ID of the Pingu
   */
  public void setSelectedPingu(int selected_pingu)
  {
    this.m_selectedPingu = selected_pingu;
  }
}