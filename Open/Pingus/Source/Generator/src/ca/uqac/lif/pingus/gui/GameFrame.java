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
package ca.uqac.lif.pingus.gui;

import javax.swing.JFrame;

import ca.uqac.lif.pingus.Level;

/**
 * The Swing frame in which the game is placed.
 * 
 * @author Sylvain Hallé
 */
public class GameFrame extends JFrame 
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new game frame showing a particular level
	 * @param lev The level to show
	 */
	public GameFrame(Level lev)
	{
		super("Pingu Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		LevelPanel lp = new LevelPanel(this, lev);
		add(lp);
		addKeyListener(lp);
		pack();
	}
}
