# Sudoku-Solver

A project that solves a medium-level Sudoku

## Description

This program is able to read a .txt file containing a Sudoku with a specific format and solve it. For the program to successfully recognize the text as a Sudoku, the following are required:  
1. Every unknown square is encoded as the digit 0
2. Every known square is encoded as the digit that appears on the square
3. The order of the digits that appear in the text file is the same as the order of the digits that appear on the Sudoku
3. Each pair of adjacent squares on the Sudoku are separated by a whitespace when encoded into the text file
4. Every new row in the Sudoku is encoded as a new row in the text file

The following is an example of a recognizable Sudoku:  
0 9 0 0 0 0 0 0 1  
3 2 0 0 0 0 8 0 0  
7 0 0 0 0 0 0 9 0  
0 0 0 0 0 0 0 0 0  
0 8 0 3 0 0 0 7 0  
2 0 0 0 5 1 6 0 0  
0 0 0 0 8 0 2 6 0  
8 0 0 7 3 0 0 0 9  
0 0 0 0 6 9 1 0 3  

Note that this project is unable to solve all Sudoku. Sudoku that require the most complex strategies are unable to be solved by this program.

## Installation

This project is created using Eclipse. You can run this project on Eclipse or any other Java IDE. For downloading Eclipse, visit https://www.eclipse.org/downloads/. For importing this project onto Eclipse, you can visit https://stackoverflow.com/questions/6760115/importing-a-github-project-into-eclipse for instructions written by others. 

## Usage

Once you have imported the project onto Eclipse or other IDEs, find a Sudoku you would like to solve and encode it in a .txt file according to the rules above. Then, click the "run" button and see if the Sudoku is successfully solved!

## Contributing

Pull requests are welcome. Adding more features and improving the functionality and efficiency of current features are all encouraged. For major changes, please open an issue first to see what you would like to change.

## License

MIT
