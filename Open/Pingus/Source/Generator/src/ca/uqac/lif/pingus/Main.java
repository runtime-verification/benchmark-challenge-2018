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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import ca.uqac.lif.pingus.gui.GameFrame;
import ca.uqac.lif.pingus.gui.ImageScaler;
import ca.uqac.lif.pingus.util.CliParser;
import ca.uqac.lif.pingus.util.CliParser.Argument;
import ca.uqac.lif.pingus.util.CliParser.ArgumentMap;

/**
 * The main class of the Pingu generator.
 * @author Sylvain Hallé
 */
public class Main
{
  /**
   * Whether to output a sequence of bitmaps when the game runs
   */
  static boolean s_toImages = false;

  /**
   * Whether to output an XML trace when the game runs
   */
  static String s_traceFilename = null;

  /**
   * The name of the level to run
   */
  static String s_levelName = "level-0";

  /**
   * The frame rate at which the game runs
   */
  static float s_rate = 60;

  /**
   * The number of Pingus in the level
   */
  static int s_numPingus = 10;
  
  /**
   * Whether to show the game's window
   */
  static boolean s_withGui = true;

  /**
   *  Return code indicating that no error has been produced
   */
  private static final transient int ERR_OK = 0;

  /**
   *  Return code indicating that the command line arguments are invalid
   */
  private static final transient int ERR_ARGS = 1;

  /**
   * The program's main loop
   * @param args Command-line arguments
   * @throws IOException Thrown when a file cannot be read or written
   * @throws InterruptedException Thrown when the main thread is interrupted
   */
  public static void main(String[] args) throws IOException, InterruptedException 
  {
    // Parse command line parameters
    String piano_roll_filename = null;
    CliParser parser = setupCli();
    ArgumentMap params = parser.parse(args);
    if (params == null)
    {
      System.err.println("Invalid command line parameters");
      showUsage(parser);
      System.exit(ERR_ARGS);
    }
    if (params.hasOption("roll"))
    {
      piano_roll_filename = params.getOptionValue("roll");
    }
    if (params.hasOption("rate"))
    {
      s_rate = Integer.parseInt(params.getOptionValue("rate"));
    }
    if (params.hasOption("pingus"))
    {
      s_numPingus = Integer.parseInt(params.getOptionValue("pingus"));
    }
    if (params.hasOption("images"))
    {
      s_toImages = true;
    }
    if (params.hasOption("headless"))
    {
      s_withGui = false;
    }
    if (params.hasOption("level"))
    {
      s_levelName = params.getOptionValue("level");
    }
    if (s_traceFilename == null && !s_withGui)
    {
      System.err.println("Option --headless cannot be used without --trace");
      System.exit(ERR_ARGS);
    }
    Level lev = new BitmapLevel(s_levelName);
    lev.setRate(s_rate);
    lev.setPinguCount(s_numPingus);
    Runtime.getRuntime().addShutdownHook(new MainShutdownHook(lev, piano_roll_filename));
    if (params.hasOption("trace"))
    {
      s_traceFilename = params.getOptionValue("trace");
      lev.setStream(new PrintStream(new FileOutputStream(s_traceFilename)));
    }
    if (params.hasOption("replay"))
    {
      // Replay mode
      String piano_filename = params.getOptionValue("replay");
      PianoRoll roll = new PianoRoll(new Scanner(new File(piano_filename)));
      GameFrame frame = null;
      PlayerPiano player = new PlayerPiano(lev);
      if (s_withGui)
      {
        // Replay in GUI
        frame = new GameFrame(lev);   
      }
      player.playFor(roll, (long) (1500 * s_rate/10), frame, (long) (500 / s_rate));
    }
    else
    {
      // Interactive mode
      GameFrame frame = new GameFrame(lev);		
      int tick_mod = Math.round(s_rate / 10);
      long sleep_time = Math.round(1000 / s_rate);
      for (int loop_count = 0; loop_count < 5000; loop_count++)
      {
        long start = System.currentTimeMillis();
        if (s_toImages)
        {
          BufferedImage im = lev.draw();
          im = ImageScaler.scale(im, BufferedImage.TYPE_INT_ARGB, 640, 480, 2, 2);
          File outputfile = new File(String.format("image-%03d.png", loop_count));
          ImageIO.write(im, "png", outputfile);
          System.out.print(loop_count + " ");
        }
        boolean tick = (loop_count % tick_mod == 0);
        lev.step(tick);
        frame.repaint();
        long end = System.currentTimeMillis();
        // Adjust sleep time for actual elapsed time
        Thread.sleep(Math.max(0, sleep_time - (end - start)));
      }
    }
    System.exit(ERR_OK);
  }

  /**
   * Sets up the command line arguments for the parser
   * @return The parser
   */
  protected static CliParser setupCli()
  {
    CliParser parser = new CliParser();
    parser.addArgument(new Argument().withLongName("rate").withArgument("x")
        .withDescription("Sets animation rate to x fps"));
    parser.addArgument(new Argument().withLongName("images")
        .withDescription("Output execution as sequence of images"));
    parser.addArgument(new Argument().withLongName("headless")
        .withDescription("Don't show game window"));
    parser.addArgument(new Argument().withLongName("level").withArgument("x")
        .withDescription("Use built-in level named x"));
    parser.addArgument(new Argument().withLongName("trace").withArgument("file")
        .withDescription("Write execution trace to file"));
    parser.addArgument(new Argument().withLongName("roll").withArgument("file")
        .withDescription("Write piano roll to file"));
    parser.addArgument(new Argument().withLongName("replay").withArgument("file")
        .withDescription("Replay interactions from file"));
    parser.addArgument(new Argument().withLongName("pingus").withArgument("x")
        .withDescription("Sets number of Pingus to x"));
    return parser;
  }

  /**
   * Print command-line usage to standard error
   */
  protected static void showUsage(CliParser parser)
  {
    parser.printHelp("Usage: java -jar pingu-generator.jar [options]", System.err);
  }

  /**
   * Handles the end of the program
   */
  protected static class MainShutdownHook extends Thread
  {
    /**
     * The level played by the simulator
     */
    protected Level m_level;

    /**
     * The name of the output piano roll
     */
    protected String m_pianoRollFilename = null;

    /**
     * Creates the main shutdown hook
     * @param lev The level played by the simulator
     * @param piano_roll_filename The name of the output piano roll
     */
    public MainShutdownHook(Level lev, String piano_roll_filename)
    {
      super();
      m_level = lev;
      m_pianoRollFilename = piano_roll_filename;
    }

    @Override
    public void run()
    {
      // If piano roll selected, write it to file before ending
      if (m_pianoRollFilename != null)
      {
        try
        {
          PrintStream ps = new PrintStream(new File(m_pianoRollFilename));
          ps.print(m_level.m_pianoRoll.toString());
          ps.close();
        }
        catch (FileNotFoundException e)
        {
          // Not supposed to happen
        }
      }
    }
  }
}