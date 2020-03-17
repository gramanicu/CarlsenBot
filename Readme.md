# CarlsenBot

This is a project that represents the main homework for the Algorithm Design Course.
The problem statement can be found [here](https://ocw.cs.pub.ro/courses/pa/proiect).


## Table of Contents

- [Chess Bot](#chess-bot)
	-[Table of Contents](#table-of-contents)
	-[Project structure](#project-structure)
	-[Packages](#packages)
		-[communication](#communication)
		-[main](#main)
		-[pieces](#pieces)
		-[player](#player)
		-[position](#position)
		-[table](#table)
	-[Game overview](#game-overview)

## Project structure

``` bash
 -. src
	|-- Readme.md
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
	|	|--CheckSystem.java
	|	|--Table.java
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

 © 2020 Grama Nicolae, Ioniță Radu, Mosessohn Vlad, 322CA
