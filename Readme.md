# CarlsenBot

A chess bot, the project for the Algorithm Design Course. It is a bot for the XBoard chess game.
 
 ## Piece indexation
 
 The pieces are indexed by the table. As a piece is assigned to a table, the table is responsible to keep all the pieces unique. The index/id of a piece is assigned in the order of addition to the table, white and black pieces separately.
 
 ## Check system
The check system will use a byte matrix. We can analise all pieces on a side, and for piece that attacks a cell, we increase the number ("danger value") of that cell. Every time we move a cell, decrease the danger value in it's attacked cells, then increase accordingly.
  
 © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
