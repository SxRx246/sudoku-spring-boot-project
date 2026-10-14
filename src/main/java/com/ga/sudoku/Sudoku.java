package com.ga.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Sudoku {
    public static void main(String[] args) {

        InputStream puzzle1Stream = Sudoku.class.getResourceAsStream("/puzzle/puzzle1.txt");

        if (puzzle1Stream == null) {
            System.out.println("puzzle1.txt not found");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(puzzle1Stream))) {

            System.out.println("\t\t\tPuzzle 1");

            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");

                for (int column = 0; column < numbers.length; column++) {
                    if (column > 0) {
                        System.out.print("  ");
                    }

                    if (column == 3 || column == 6) {
                        System.out.print("|  ");
                    }

                    System.out.print(numbers[column]);
                }

                System.out.println();
                row++;

                if (row % 3 == 0 && row < 9) {
                    System.out.println("----------------------------------");
                }
            }
        } catch (
                IOException e) {
            System.out.println("Error reading puzzle1.txt: " + e.getMessage());
        }

    }
}
