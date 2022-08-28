package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private char gameBoard[][] = new char[3][3];
    private Scanner scanner = new Scanner(System.in);
    private int numberOfXs;
    private int numberOfOs;

    private boolean xTurn = true;
    private boolean oTurn = false;

    public void run() {
        emptyGameBoard();

        print();

        while (gameNotFinished() && !impossible()) {
            updateGameBoard();
            print();
        }

        if (draw())
            System.out.println("Draw");

        if (oWins())
            System.out.println("O wins");

        if (xWins())
            System.out.println("X wins");
    }

    private void emptyGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++){
                gameBoard[i][j] = '_';
            }
        }
    }

    private void print() {
        System.out.println("---------");
        System.out.println("| " + gameBoard[0][0] + " " + gameBoard[0][1] + " " + gameBoard[0][2] + " |");
        System.out.println("| " + gameBoard[1][0] + " " + gameBoard[1][1] + " " + gameBoard[1][2] + " |");
        System.out.println("| " + gameBoard[2][0] + " " + gameBoard[2][1] + " " + gameBoard[2][2] + " |");
        System.out.println("---------");
    }

    private boolean diagonalWinning(int sumToWin) {
        int leftSum = 0;
        int rightSum = 0;
        int i = 0;
        int j = gameBoard.length - 1;

        for (char[] chars : gameBoard) {
            leftSum += chars[i];
            rightSum += chars[j];
            i++;
            j--;
        }
        return leftSum == sumToWin || rightSum == sumToWin;
    }

    private  boolean columnWinning(int sumToWin) {
        for (var column : gameBoard) {
            int columnSum = 0;

            for (var cell : column) {
                columnSum += cell;
            }

            if (columnSum == sumToWin)
                return true;
        }
        return false;
    }

    private  boolean rowWinning(int sumToWin) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] + gameBoard[1][i] + gameBoard[2][i] == sumToWin) {
                return true;
            }
        }
        return false;
    }

    private  boolean xWins() {
        int sumToWin = 264;
        return rowWinning(sumToWin) || columnWinning(sumToWin) || diagonalWinning(sumToWin);
    }

    private  boolean oWins() {
        int sumToWin = 237;
        return rowWinning(sumToWin) || columnWinning(sumToWin) || diagonalWinning(sumToWin);
    }

    private boolean hasEmptyCells() {
        for (var row : gameBoard) {
            for (var cell : row) {
                if (cell == '_')
                    return true;
            }
        }
        return false;
    }

    private boolean draw() {
        return !oWins() && !xWins() &&
                !hasEmptyCells();
    }

    private boolean impossible() {
        return Math.abs(numberOfXs - numberOfOs) > 1|| columnWinning(264) && columnWinning(237);
    }

    private boolean gameNotFinished() {
        return hasEmptyCells() && !xWins() && !oWins();
    }

    private void updateGameBoard() {
        System.out.println("Enter coordinates ");
        int xCorrdinate = scanner.nextInt();
        int yCorrdinate = scanner.nextInt();
        if (xCorrdinate < 1 || xCorrdinate > 3 || yCorrdinate < 1 || yCorrdinate > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
        } else if (gameBoard[xCorrdinate - 1][yCorrdinate - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
        } else {
            if (xTurn) {
                gameBoard[xCorrdinate - 1][yCorrdinate - 1] = 'X';
                xTurn = false;
                oTurn = true;
                numberOfXs++;
            } else if (oTurn) {
                gameBoard[xCorrdinate - 1][yCorrdinate - 1] = 'O';
                xTurn = true;
                oTurn = false;
                numberOfOs++;
            }
        }
    }
}