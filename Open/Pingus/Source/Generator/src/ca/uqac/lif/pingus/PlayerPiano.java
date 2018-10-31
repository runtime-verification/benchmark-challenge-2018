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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import ca.uqac.lif.pingus.PianoRoll.PianoEvent;
import ca.uqac.lif.pingus.gui.GameFrame;

/**
 * Replays a set of pre-recorded user interactions on a given level.
 * 
 * @author Sylvain Hallé
 */
public class PlayerPiano 
{
  /**
   * The level on which to play
   */
  protected transient Level m_level;

  /**
   * Creates a new instance of player piano.
   * @param lev The level on which the actions are to be replayed
   */
  public PlayerPiano(Level lev)
  {
    super();
    m_level = lev;
  }

  /**
   * Replays the contents of a piano roll for a given number of steps.
   * @param roll The piano roll containing the interactions to replay
   * @param steps The number of animation steps to replay
   * @param frame A game frame in which to show the level being
   * played back. May be {@code null} if no display is required.
   * @param sleep_ms A sleep interval between each animation step. Set it to
   * 0 when no animation is required.
   */
  public void playFor(PianoRoll roll, long steps, /*@ null @*/ GameFrame frame, long sleep_ms)
  {
    Iterator<PianoEvent> p_it = roll.iterator();
    PianoEvent next_event = null;
    int step_mod = (int) Math.max(1, m_level.m_scale / 10);
    if (p_it.hasNext())
    {
      next_event = p_it.next();
    }
    for (long step_count = 0; step_count < steps; step_count++)
    {
      if (next_event != null && step_count == next_event.m_stepCount)
      {
        // Play
        turnInto(next_event.m_pinguId, next_event.m_clazz);
        if (p_it.hasNext())
        {
          next_event = p_it.next();
        }
        else
        {
          next_event = null;
        }
      }
      m_level.step(step_count % step_mod == 0);
      if (frame != null)
      {
        frame.repaint();
        try 
        {
          Thread.sleep(sleep_ms);
        }
        catch (InterruptedException e) 
        {
          e.printStackTrace();
        }
      }
    }
  }

  protected void turnInto(int id, Class<? extends Pingu> clazz)
  {
    ClassLoader loader = Pingu.class.getClassLoader();
    Pingu p = m_level.getPingu(id);
    try 
    {
      loader.loadClass("ca.uqac.lif.pingus.Pingu");
      Constructor<? extends Pingu> c = clazz.getConstructor(Pingu.class);
      Pingu new_p = c.newInstance(p);
      m_level.turnInto(id, new_p);
    } 
    catch (NoSuchMethodException e)
    {
      throw new RuntimeException(e);
    } 
    catch (SecurityException e)
    {
      throw new RuntimeException(e);
    } 
    catch (InstantiationException e)
    {
      throw new RuntimeException(e);
    } 
    catch (IllegalAccessException e)
    {
      throw new RuntimeException(e);
    } 
    catch (IllegalArgumentException e) 
    {
      throw new RuntimeException(e);
    } 
    catch (InvocationTargetException e) 
    {
      throw new RuntimeException(e);
    } 
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }
}
