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

- Command - it is a wrapper for commands that are making connections with xboard.
- Engine - communication manager

### main

This package contains the game logic.

- GameManager - game manager
- GameUtils - a class where the pieces are initialised
- Main - contains the main function

### pieces

This package contains the logic behind every piece.

- Bishop, King, Knight, Pawn, Queen, Rook - classes that implement functionality specific to the piece
- Piece - a template class, that contains the base logic for pieces
- PieceColor - a class that stores the colours.

### player

This package contains the brain of the game.

- AI - class that stores the "brain"
- Player - it is a wrapper used by the AI

### position

- Position - position manager

### table

- Table - here the chess board is stored, with functionalities added for pieces
- CheckSystem - class that will store the check functionality

## Game overview

Currenlty, when the application is first run, it will initialise the board, then wait for xboard commands. The moment it receives the "xboard" command, it will reply with a set of settings. After each turn change, the "ai" will try to compute a new move. Currently, the AI uses RNG, and it may send invalid movements, as it cannot detect "check" (only if it is already in check, when it resigns). And, because xboard doesn't reply with "invalid move", it doesn't know, to be able to select another move or resign. It must be closed manually. Because the application activates the "usermove" option, every move command it receives must have this prefix "usermove _move_".

Every piece is implemented, without some advanced movements (castling, en passant, check by discovery detection).

There are many unit tests (JUnit 5), designed to check how if everything does what it is expected.

## Installation and Run

In the makefile are multiple rules written, designed to make the installation, "compilation" and run processes easier. It requires java 13 to "compile" and run.

make install - installs java 13 and xboard
make build - creates the jarfile that contains the bot
make run - compiles and runs the bot in terminal
make xboard - compiles and runs the bot, using xboard
make pack - creates an archive containing the essential files

## Other information

Because gitlab was down for a while, we decided use github to host the repository. This is why the commit usernames are different:

- gramanicu - nicolae.grama
- WansE1z - radu.ionita0702
- vladmosessohn - vlad.mosessohn

 © 2020 Grama Nicolae, Ioniță Radu, Mosessohn Vlad, 322CA
