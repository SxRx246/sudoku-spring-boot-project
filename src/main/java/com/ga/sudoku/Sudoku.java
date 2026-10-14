package com.ga.sudoku;

public class Sudoku {
    public static void main(String[] args) {
        var file = Sudoku.class.getResource("/puzzle/puzzle1.txt");

        if (file != null) {
            System.out.println("puzzle1.txt is found");
        }
        else {
            System.out.println("not found");
        }
    }
}
