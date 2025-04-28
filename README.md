# Java Hangman Game

This is a simple Java hangman game which I created while learning Java programming. The program is a command-line implementation of Hangman where the player guesses letters to reveal the hidden word.

## Table of Contents

- [Overview](#overview)
- [Video Demo](#video-demo)
- [Features](#features)
- [File Structure](#file-structure)
- [Requirements](#requirements)
- [How To Use](#how-to-use)

## Overview

The application allows the player to guess letters to reveal a hidden word. Throughout the game, the correct and incorrect guesses are tracked, and a visual of the hangman is displayed as incorrect guesses are made. The game ends when the word is fully guessed or when the hangman is fully drawn.

## Video Demo
![Demo Video](./demo/JavaHangmanDemo.gif)

## Features

- ✅ Randomly selects a word from a predefined list `Word List.txt`
- ✅ Tracks and displays correct/incorrect guesses
- ✅ Displays a hangman figure as incorrect guesses are made
- ✅ Allows the player to play again after the game ends
- ✅ User-friendly console-based interface

## File Structure:
- **Main.java**: Entry point of the program. Manages the flow of the game, word selection, and user interaction.
- **Hangman.java**: Contains the game logic (tracking guesses, updating word state, and displaying the hangman figure).
- **Word List.txt**: A text file containing a list of words for the game (one per line).

## Requirements
- Java 23 or higher
- A text file (`Word List.txt`) with words for the game (one per line).

> _Note: This project was developed using IntelliJ IDEA and Java 23. Additional configuration may be required for other IDEs/editors._

## How to Use:

1. **Run the application:**
- Compile and run `Main.java` to start the hangman game.
  
2. **Game Start:**
- The game will start automatically and randomly pick a word from `Word List.txt`.
- You'll be prompted to guess one letter at a time.
  
3. **Make a Guess:**
- Enter one letter to guess from the word.
- A display will inform you whether your guess is correct or incorrect.
- The hangman figure will update every time an incorrect guess is made.
  
4. **Win or Lose:**
- If you guess all the letters in the word, you win!
- If the hangman figure is fully drawn, you lose (6 incorrect guesses). 

5. **Play Again:**
- After the game, you'll be asked if you want to play again. Type 'yes' to play again or 'no' to quit the game.
