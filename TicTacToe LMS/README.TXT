------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Tic Tac Toe AI using least sum Squares
PURPOSE OF PROJECT: Basic Machine learning algorithm using Least sum of squares

How it works:  Evaluator evaluates the position based on its linear regression equations ex: B0 + b1x1 + b2x2... where B's are the wieghts that will be learned.  In this program the xs(inputs) are how many os or xs are on the diagonal/col/row so there are 8 possible options.  After each game it trains with the equation bi = bi + (Actual evaluation of the board - its own evaluation of the board) * (input at that possition) * .1.  When the ai loses it trains with the real evaluation: -100 and when it wins: 100 and draws are 0.  I have to fix a bug somewhere that is messing everything up.  But it kind of works.  

