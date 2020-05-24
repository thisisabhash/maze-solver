# Maze Solver

## Input
A maze will be given in a text file as a matrix in which the start position is indicated by “S”, 
the goal position is indicated by “G”, walls are indicated by “%”, and empty positions where the 
robot can move are indicated by “ “. The outer border of the maze, i.e., the entire first row, 
last row, first column and last column will always contain “%” characters. A robot is allowed to 
move only horizontally or vertically, not diagonally.


## Execution
`javac *.java`

`java FindPath inputfile searchmethod`

where searchmethod can be 'bfs' or 'astar'
and inputfile can be 'testfile/input*.txt' from the included testfiles

## Output
After a solution is found, following is printed out on separate lines:
1. the maze with a “.” in each square that is part of the solution path
2. the length of the solution path
3. the number of nodes expanded (i.e., the number of nodes removed from Frontier, including the
goal node)
4. the maximum depth searched
5. the maximum size of Frontier at any point during the search.

If the goal position is not reachable from the start position, the standard output should contain the 
line “No Solution” and nothing else.
