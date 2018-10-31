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

import java.util.LinkedList;
import java.util.Scanner;

import ca.uqac.lif.pingus.util.FileHelper;

/**
 * A sequence of events representing the recorded interaction of a
 * user with the game.
 * 
 * @author Sylvain Hallé
 */
public class PianoRoll extends LinkedList<PianoRoll.PianoEvent> 
{
  /**
   * Dummy UID
   */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new empty piano roll
   */
  public PianoRoll()
  {
    super();
  }

  /**
   * Creates a new piano roll from a scanner
   * @param scan The scanner
   */
  public PianoRoll(Scanner scan)
  {
    while (scan.hasNextLine())
    {
      String line = scan.nextLine();
      line = line.trim();
      if (line.startsWith("#") || line.isEmpty())
        continue;
      add(new PianoEvent(line));
    }
    scan.close();
  }

  /**
   * Representation of an event in a piano roll. An event is:
   * <ul>
   * <li>the assignment of a specific ability (blocker, etc.)</li>
   * <li>to a specific character (represented by its ID)</li>
   * <li>at a specific moment (expressed in number of frames from
   * the start of the simulation)</li>
   * </ul> 
   */
  public static class PianoEvent
  {
    /**
     * The class loader used to generate instances of
     * characters
     */
    protected static final ClassLoader s_loader = Pingu.class.getClassLoader();

    /**
     * The number of animation steps corresponding to the "time"
     * of the event
     */
    public long m_stepCount;

    /**
     * The ID of the character on which to assign a new ability
     */
    public int m_pinguId;

    /**
     * The class corresponding to the ability to give to the
     * character
     */
    public Class<? extends Pingu> m_clazz;

    /**
     * Creates a new piano event
     * @param s The number of animation steps corresponding to the "time"
     * of the event
     * @param i The ID of the character on which to assign a new ability
     * @param c The class corresponding to the ability to give to the
     * character
     */
    public PianoEvent(long s, int i, Class<? extends Pingu> c)
    {
      super();
      m_stepCount = s;
      m_pinguId = i;
      m_clazz = c;
    }

    /**
     * Creates a new piano event out of a character string.
     * The string must be a comma-separated list of three
     * elements:
     * <ol>
     * <li>The number of animation steps corresponding to the "time"
     * of the event</li>
     * <li>The ID of the character on which to assign a new ability</li>
     * <li>The name of the ability to give the character (must correspond
     * to a class name in the package
     * {@link ca.uqac.lif.pingus.characters})</li>
     * </ol>
     * @param s The string to create the event from
     */
    @SuppressWarnings("unchecked")
    public PianoEvent(String s)
    {
      String[] parts = s.split(",");
      m_stepCount = Long.parseLong(parts[0]);
      m_pinguId = Integer.parseInt(parts[1]);
      try 
      {
        m_clazz = (Class<? extends Pingu>) s_loader.loadClass(parts[2]);
      } 
      catch (ClassNotFoundException e) 
      {
        throw new RuntimeException(e);
      }
    }

    @Override
    public String toString()
    {
      return m_stepCount + "," + m_pinguId + "," + m_clazz.getName();
    }
  }

  @Override
  public String toString()
  {
    StringBuilder out = new StringBuilder();
    for (PianoEvent e : this)
    {
      out.append(e).append(FileHelper.CRLF);
    }
    return out.toString();
  }
}
