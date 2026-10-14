package com.ga.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Sudoku {

    public static InputStream getPuzzle(String filePath){
        InputStream puzzle1Stream = Sudoku.class.getResourceAsStream(filePath);

        if (puzzle1Stream == null) {
            System.out.println("puzzle file not found");
            return null;
        }
        else {
            return puzzle1Stream;
        }
    }

    public static void displayPuzzle(InputStream puzzle1Stream){
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

    public static void main(String[] args) {
        String puzzle1FilePath = "/puzzle/puzzle1.txt";
        InputStream puzzle1 = getPuzzle(puzzle1FilePath);
        if(puzzle1 != null){
            displayPuzzle(puzzle1);
        }

    }
}
