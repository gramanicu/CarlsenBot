# CarlsenBot

This is a project that represents the main homework/project for the Algorithm Design Course.
The problem statement can be found [here](https://ocw.cs.pub.ro/courses/pa/proiect).


## Project structure

``` bash
 -. src/com.carlsenbot
	|-- communication
	|	|--Command.java
	|	|--Engine.java
	|-- main
	|	|--GameManager.java
	|	|--GameUtils.java
	|	|--Main.java
	|-- pieces
	|	|--Bishop.java
	|	|--King.java
	|	|--Knight.java
	|	|--Pawn.java
	|	|--Piece.java
	|	|--PieceColor.java
	|	|--Queen.java
	|	|--Rook.java
	|-- player
	|	|--AI.java
	|	|--Player.java
	|-- position
	|	|--Position.java
	|-- table
		|--CheckSystem.java
		|--Table.java
```

## Packages

### communication

This package contains the communication logic.

- Command - wrapper for the commands received from xboard
- Engine - communication manager. Handles incoming commands, stores the xboard settings and sends commands to the xboard.

### main

This package contains the game logic.

- GameManager - the game manager. Handles things like turn (who can move), and it's a "hub" that connects the different parts of the project
- GameUtils - contains helper methods (ex. pieces starting position)
- Main - main function. Initialises the game manager.

### pieces

This package contains the logic behind every piece.

- Bishop, King, Knight, Pawn, Queen, Rook - classes that implement functionality specific to the piece
- Piece - a template class, that contains the base logic for pieces
- PieceColor - a enum that contains the two possible color for the pieces: black & white.

### player

This package contains the brain of the game.

- AI - class that implements the ai - the brain of the bot
- Player - it is a wrapper used by the AI, will store information about both players in the future

### position

- Position - used to represent positions on the board. Different methods, to be able to use 3 systems to represent a point: char+number, x+y, Position. Parses strings into x-y positions.

### table

- Table - here the chess board is stored, with functionalities added for pieces
- CheckSystem - the check system is a class that verifies when the king is in check (and what cells are attacked). At the moment, it is a simple sistem, just made to work.

## Game overview

Currently, when the application is first run, it will initialise the board, then wait for xboard commands. The moment it receives the "xboard" command, it will reply with a set of settings. After each turn change, the "ai" will try to compute a new move. Currently, the AI uses RNG, and it may send invalid movements, as it cannot detect "check" (only if it is already in check, when it resigns). And, because xboard doesn't reply with "invalid move", it doesn't know, to be able to select another move or resign. It must be closed manually. Because the application activates the "usermove" option, every move command it receives must have this prefix "usermove _move_".

Every piece is implemented, without some advanced movements (castling, en passant, check by discovery detection).

There are many unit tests (JUnit 5), designed to check how if everything does what it is expected.

## Installation and Run

In the makefile are multiple rules written, designed to make the installation, "compilation" and run processes easier. It requires java 13 to "compile" and run.

make install - installs java 13 and xboard
make build - creates the jarfile that contains the bot
make run - compiles and runs the bot in terminal
make xboard - compiles and runs the bot, using xboard
make pack - creates an archive containing the essential files

It was tested on two ubuntu machines (18 and 19), using the makefile rules, and on a windows 10 machine, using a .jar file build using intellij and winboard 4.8.

## Other information

Because gitlab was down for a while, we decided use github to host the repository. This is why the commit usernames are different:

- gramanicu - nicolae.grama
- WansE1z - radu.ionita0702
- vladmosessohn - vlad.mosessohn

 © 2020 Grama Nicolae, Ioniță Radu, Mosessohn Vlad, 322CA
