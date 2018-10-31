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
package ca.uqac.lif.pingus.rebels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ca.uqac.lif.pingus.Pingu;
import ca.uqac.lif.pingus.characters.Faller;
import ca.uqac.lif.pingus.characters.Walker;
/**
 * Faller that never dies when it hits the ground
 */
public class InvincibleFaller extends Faller 
{
  /**
   * Creates a new character by copying the state of another
   * one.
   * @param p The other character
   */
	public InvincibleFaller(Pingu p)
	{
		super(p);
	}
	
	@Override
	public synchronized void draw(BufferedImage img)
	{
		super.draw(img);
		Graphics g = img.getGraphics();
		g.drawImage(s_letterR, (int) m_x, (int) (m_y - s_height - 7), null);
	}

	@Override
	protected Walker getWalker()
	{
		return new InvincibleFallerWalker(this);
	}

	@Override
	protected boolean fallsTooFast()
	{
		return false;
	}

}
