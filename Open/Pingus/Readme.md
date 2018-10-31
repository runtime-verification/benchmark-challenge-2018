An instrumented simulator for a Lemmings-like computer game
===========================================================

This program simulates the execution of a simplified version of a computer
game called
[Lemmings](https://en.wikipedia.org/wiki/Lemmings_%28video_game%29); it is
based on an open-source clone called
[Pingus](http://pingus.seul.org/welcome.html). In this game, between 10 and
100 autonomous, penguin-like characters (the Pingus) progressively enter a
level from a trapdoor and start walking across the area. A Pingu keeps walking
in the same direction until it either reaches a wall (in which case it turns
around) or falls into a gap (and dies, if it falls from too high).

The goal of the game is to have a minimum percentage of the incoming Pingus
safely reach the exit door. To this end, the player can give special abilities
to certain Pingus, allowing them to modify the landscape in order to create a
walkable path to the goal. For example, some Pingus can become *Bashers* and
dig into the ground; others can become *Builders* and construct a staircase to
reach over a gap. Other abilities modify the behaviour of other Pingus: hence
the *Blocker* stands in the way and makes any Pingu that reaches it turn
around as if it encountered a wall. Each level starts with a fixed number of
times each ability can be used; in some cases the puzzle is not so much to
find a path to the goal, but rather to achieve it using the limited number of
abilities made available to the player.

Monitoring Pingus
-----------------

Previous research has shown how the execution of this game can be monitored,
and bugs in the implementation of the game's mechanics be detected by means
of *formal specifications*.

When running, the game updates the playing field about 60 times per second;
the game's main loop can be instrumented so that it produces an XML snapshot
of its state similar to the one shown below:

```xml
<characters>
  <character>
    <id>0</id>
    <status>FALLER</status>
    <position>
      <x>1121</x>
      <y>393</y>
    </position>
    <velocity>
      <x>0</x>
      <y>3.6</y>
    </velocity>
  </character>
  ...
</characters>
```

Each such snapshot contains the ID, status, position and velocity of every
Pingu on the game field. The same Pingu is given the same ID across all events
in the same execution.

The program in this repository simulates the execution of the video game, and
produces such a trace of XML events. It also has the ability to display the
execution graphically, complete with animated sprites taken from the original
Lemmings game. However, contrary to the regular game, it has a number of
original features aimed at using it as a runtime monitoring benchmark:

- New levels can be created by means of special image maps
- It can record, and then replay the interactions made by the user on a game
- The number of Pingus in a level can be modified
- Some characters can be turned into "buggy" ones, which do not behave exactly
  like they should. This is useful for creating execution traces that
  violate a specification.

Compiling and Running
---------------------

To compile the generator, make sure you have the following:

- The Java Development Kit (JDK) to compile. The generator complies
  with Java version 6; it is probably safe to use any later version.
- [Ant](http://ant.apache.org) to automate the compilation and build process

From the project's root folder, compile the sources by simply typing:

    ant

This will produce a file called `pingu-generator.jar` in the folder. This file
is runnable and stand-alone.

Running in Normal Mode
----------------------

The "normal" mode of the generator works like a simplified and interactive
version of the game. Simply start it by typing:

    java -jar pingu-generator.jar

By default, the program starts in a default level (called "level-0"), shows
the level's map in a new window, and introduces 10 Pingus into the level. You
can see the characters being animated in the window, as below:

![Screenshot](Screenshot.png)

You can interact with the characters; clicking on one of them "selects" it.
Once it is selected, you can use the keyboard to give it a special ability:

- `b`: blocker
- `d`: builder
- `h`: basher
- `w`: walker
- `x`: blower

Use `Esc` to quit or close the window.

When hovering the mouse over a Pingu, the standard output displays its
current state.

However, the point of the program is not to play with it, but rather to
generate execution traces. You can do so by using the `--trace` option,
followed by a file name:

    java -jar pingu-generator.jar --trace out.xml

The program still starts in normal (i.e. interactive) mode, but writes to
`out.xml` a trace of XML events giving the status of the game at each cycle of
the main loop (as we said, about 60 times per second).

Generating Faulty Traces
------------------------

As we said, a way of generating traces with errors is to give some characters
some "buggy" abilities; these characters are called the *rebels*. This can be
done in the same way as normal abilities, but using different keys:

- `W`: creates a *Rebel Walker* (who ignores blockers and walks past them)
- `I`: creates an *Invincible Jumper* (who always survives to a fall,
  regardless of its height)
- `B`: creates a *Slacker Blocker* (who blocks other Pingus for 5 s, and then
  resumes walking)
- `H`: creates a *Basher Blocker* (a Basher that becomes a Blocker (instead of
  a Walker) when it is done bashing
- `F`: creates a *Fragile Faller* (who always dies (by blowing) when it hits
  the ground)

These buggy abilities can be used to generate traces that violate some formal
specification (see below).

Replay Mode
-----------

Replaying the same user interaction on variants of the game (with or without
bugs, or with a different number of Pingus on the game field) is next to
impossible with the original game. The simulator provides the capability to
simulate user interactions by reading from a "script" called the *piano roll*.

Outputting to a piano roll is done with the `--roll` command line option; for
example, the following call will write the roll to a file called `file.txt`
when the program is closed:

    java -jar pingu-generator.jar --roll file.txt

A line in the roll looks like this:

    1135,0,ca.uqac.lif.pingus.characters.Blocker

It is made of three elements:

- The number of animation steps corresponding to the "time" of the event
- The ID of the character on which to assign a new ability
- The name of the ability to give the character (must correspond
  to a class name in the package `ca.uqac.lif.pingus.characters`

This simple format means that such a file can also be written directly by the
user (if you know what you are doing).

Given such a piano roll, one can replay a sequence of interactions using the
`--replay` argument:

    java -jar pingu-generator.jar --replay file.txt

By default, the interaction is replayed in the GUI; the resulting execution
can be written to an XML file using the `--trace` option, as above.

Headless Execution and Image Sequence
-------------------------------------

The execution can be started in "headless" mode, meaning that no user
interface will be shown, using the `--headless` command line option. If
`--headless` is present, arguments `--replay` and `--trace` *must* also be
present.

The execution can also be exported as a sequence of PNG images. This can be
useful to investigate the execution of the level on a frame-by-frame basis.
The `--images` flag can be used to this end. It will output a sequence of
images called `image-XXX.png` in the current directory.

References
----------

Monitoring on the Pingus video game has been described in further detail
in the following paper:

> S. Varvaressos, K. Lavoie, S. Gaboury, S. Hallé. (2017). Automated Bug
> Finding in Video Games: A Case Study for Runtime Monitoring. Computers in
> Entertainment 15(1): 1-28. ACM. DOI: 10.1145/2700529

<!-- :maxLineLen=78:mode=markdown: -->