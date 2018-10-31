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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.uqac.lif.pingus.Level;
import ca.uqac.lif.pingus.Pingu;
import ca.uqac.lif.pingus.rebels.BasherBlocker;
import ca.uqac.lif.pingus.rebels.FragileFaller;
import ca.uqac.lif.pingus.rebels.InvincibleFallerWalker;
import ca.uqac.lif.pingus.rebels.RebelWalker;
import ca.uqac.lif.pingus.rebels.SlackerBlocker;

/**
 * Graphical rendition of a level as a Java widget that can be
 * placed inside a window. This object is also responsible for handling
 * keyboard events and mouse clicks.
 * @author Sylvain Hallé
 */
public class LevelPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The level this panel draws
	 */
	protected Level m_level;

	/**
	 * The ID of the last Pingu hovered
	 */
	protected int m_lastId = -1;

	/**
	 * The parent frame
	 */
	protected JFrame m_parent;

	public LevelPanel(JFrame parent, Level lev)
	{
		super();
		m_parent = parent;
		setFocusable(true);
		setPreferredSize(new Dimension(640, 480));
		m_level = lev;
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setSize(640, 480);
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		BufferedImage img = m_level.draw();
		img = ImageScaler.scale(img, BufferedImage.TYPE_INT_ARGB, 640, 480, 2, 2);
		g.drawImage(img, 0, 0, null); // see javadoc for more info on the parameters
	}

	@Override
	public void mouseMoved(MouseEvent event) 
	{
		Pingu p = m_level.getPinguAt(event.getX() / 2, event.getY() / 2);
		if (p != null)
		{
			if (p.getId() != m_lastId)
			{
				System.out.println(p.toXml());
				m_lastId = p.getId();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent event) 
	{
		//checkForHover(event);
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		char c = arg0.getKeyChar();
		switch (c)
		{
		case 27: // Esc
			m_level.close();
			m_parent.dispatchEvent(new WindowEvent(m_parent, WindowEvent.WINDOW_CLOSING));
			break;
		default:
			// Do nothing
			break;		
		}
		if (m_level.getSelectedPingu() < 0)
			return;
		switch (c)
		{
		case 'd':
			m_level.turnInto(m_level.getSelectedPingu(), Pingu.Status.BUILDER);
			m_level.unselect();
			break;
		case 'b':
			m_level.turnInto(m_level.getSelectedPingu(), Pingu.Status.BLOCKER);
			m_level.unselect();
			break;
		case 'h':
			m_level.turnInto(m_level.getSelectedPingu(), Pingu.Status.BASHER);
			m_level.unselect();
			break;
		case 'w':
			m_level.turnInto(m_level.getSelectedPingu(), Pingu.Status.WALKER);
			m_level.unselect();
			break;
		case 'x':
			m_level.turnInto(m_level.getSelectedPingu(), Pingu.Status.BLOWER);
			m_level.unselect();
			break;
		case 'W':
			m_level.turnInto(m_level.getSelectedPingu(), new RebelWalker(m_level.getPingu(m_level.getSelectedPingu())));
			m_level.unselect();
			break;
		case 'I':
			m_level.turnInto(m_level.getSelectedPingu(), new InvincibleFallerWalker(m_level.getPingu(m_level.getSelectedPingu())));
			m_level.unselect();
			break;
		case 'B':
			m_level.turnInto(m_level.getSelectedPingu(), new SlackerBlocker(m_level.getPingu(m_level.getSelectedPingu())));
			m_level.unselect();
			break;
		case 'H':
			m_level.turnInto(m_level.getSelectedPingu(), new BasherBlocker(m_level.getPingu(m_level.getSelectedPingu())));
			m_level.unselect();
			break;
    case 'F':
      m_level.turnInto(m_level.getSelectedPingu(), new FragileFaller(m_level.getPingu(m_level.getSelectedPingu())));
      m_level.unselect();
      break;
		default:
			// Do nothing
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		Pingu p = m_level.getPinguAt(event.getX() / 2, event.getY() / 2);
		if (p == null)
		{
			m_level.unselect();
			return;
		}
		m_level.select(p.getId());
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		// Nothing to do
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// Nothing to do
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// Nothing to do
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// Nothing to do
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// Nothing to do
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// Nothing to do
	}			
}
