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
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading puzzle1.txt: " + e.getMessage());
        }

    }
}
