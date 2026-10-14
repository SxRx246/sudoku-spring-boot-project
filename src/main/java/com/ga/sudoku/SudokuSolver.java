package com.ga.sudoku;

public class SudokuSolver {
    SudokuCell[][] board = Sudoku.getBoard();

    public boolean existsInRow(int num, int row) {
        for (int column = 0; column < 9; column++) {
            if (board[row][column].getValue() == num) {
                return true;
            }
        }
        return false;
    }

    public boolean existsInColumn(int num, int column) {
        for (int row = 0; row < 9; row++) {
            if (board[row][column].getValue() == num) {
                return true;
            }
        }
        return false;
    }

    public boolean existsInBox(int num, int row, int column) {

        int startRow = (row / 3) * 3;
        int startColumn = (column / 3) * 3;

        for (int currentRow = startRow; currentRow < startRow + 3; currentRow++) {
            for (int currentColumn = startColumn; currentColumn < startColumn + 3; currentColumn++) {

                if (board[currentRow][currentColumn].getValue() == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValid(int num, int row, int column) {
            if (!existsInRow(num, row) && !existsInColumn(num, column) && !existsInBox(num, row, column)) {
                return true;
            } else {
                return false;
            }
    }

    public boolean solveSudoku() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column].isEmpty()) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(num, row, column)) {
                            board[row][column].setValue(num);
                            if (solveSudoku()) {
                                return true;
                            }
                            board[row][column].setValue(0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
