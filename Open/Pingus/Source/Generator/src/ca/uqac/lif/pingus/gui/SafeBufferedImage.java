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

import java.awt.image.BufferedImage;

public class SafeBufferedImage extends BufferedImage 
{
	public SafeBufferedImage(int width, int height, int imageType) 
	{
		super(width, height, imageType);
	}
	
	@Override
	public void setRGB(int x, int y, int rgb)
	{
		if (x < 0 || x >= getWidth())
			return;
		if (y < 0 || y >= getHeight())
			return;
		super.setRGB(x, y, rgb);
	}
}
