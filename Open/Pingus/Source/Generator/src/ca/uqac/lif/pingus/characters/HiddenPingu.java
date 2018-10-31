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

import ca.uqac.lif.pingus.Level;
import ca.uqac.lif.pingus.Pingu;

/**
 * A Pingu that is not shown on the game map. This generally corresponds
 * to Pingus that are already "dead".
 * 
 * @author Sylvain Hallé
 */
public class HiddenPingu extends Pingu
{
  /**
   * Creates a new hidden Pingu
   */
	public HiddenPingu()
	{
		super();
		setVelocityX(Walker.s_vel);
		setVelocityY(0);
	}

	@Override
	public String getStatusString()
	{
		return "HIDDEN";
	}

	@Override
	public Pingu step(Level lev, float scale, boolean tick)
	{
		return this;
	}
	
	@Override
	public void draw(BufferedImage img)
	{
		// Do nothing
		return;
	}
}