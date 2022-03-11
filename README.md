# 2048

This project is an own implementation of the infamous 2048 game.

## Motivation

I've been thinking about a project, which lets me practice threading in Java. 
Then it crossed my mind that in 2048 every row, and every column is independent. 
Meaning, when the user slides the cells in a direction, let's say to the right, every row can be updated in parallel, which is a perfect opportunity for multithreading.
Furthermore, 2048 has a simple concept, I knew the rules by myself, and after thinking a bit I had ideas about new features I haven't seen elsewhere.

## Functionality

Right now, the program has a console user interface. 
The player can move the cells in four directions; up, down, right, left. 
The program computes the score for the player.
The user can exit from the game anytime.
The user can start new game anytime.

## Plans

### Functionality

+ Diagonal movement
+ Use base different from 2
+ Undo movement
+ Replay game
+ Create menu
+ User defined map size
+ Continue game
+ Save game
+ Load game
+ User settings
+ User defined key mapping
+ Store scores
+ Show scores
+ Multiplayer ideas
  + Player1: up/down, Player2: left/right
  + Player1: 2 based number, Player2: other number based number

### Platforms

Based on the core functionalities I would like to add support for multiple platforms. 
This is also mostly about practicing, and learning. 

#### Console

I want to reorganize the project to have a core functionality totally independent of any platform details, even of console implementation. 
But I want to keep the console implementation, and organize it into a new module.

#### Desktop GUI

I want to create a JavaFX application based on the core functionalities.

#### Web application

I want to create a web application around the core functionalities, and deploy it in GitHub.

#### Android application

I've never created a mobile app, but I'm excited about it, it seems like a perfect opportunity to try it.