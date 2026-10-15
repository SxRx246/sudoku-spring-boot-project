# Sudoku Solver

## Overview
A Java and Spring Boot application that solves 9 × 9 Sudoku puzzles through REST API endpoints. Users can upload a Sudoku text file using Postman, receive the solved grid, and save the solution as a text file.

## Technologies
- Java
- Spring Boot
- Spring Web
- Spring Security
- Postman

## REST API Endpoints

**Base URL:** `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/sudoku/solve` | Uploads a Sudoku `.txt` file, solves the puzzle, saves the solution, and returns the solved grid. |
| GET | `/sudoku/solution` | Returns the server-side location of the generated solution file. |

## How to Use with Postman

### 1. Upload and Solve a Puzzle

1. Select `POST` and enter `http://localhost:8080/sudoku/solve`.
2. Go to **Body → form-data**.
3. Enter `file` as the key and select **File** as its type.
4. Upload your Sudoku `.txt` file.
5. Click **Send** to receive the solved grid.

### 2. Get the Solution File Location

1. Select `GET` and enter `http://localhost:8080/sudoku/solution`.
2. Click **Send** after successfully solving a puzzle.
3. The response displays the solution file's location on the server.

**Note:** The GET endpoint currently returns the file location as text; it does not download the file itself.

## Input File Format

The file must contain nine rows with nine numbers per row. Use `1–9` for existing values and `0` for empty cells.

Example:

```text id="8c3xpe"
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

## Output

Solutions are saved in `src/main/resources/puzzle.solution/` using the original filename followed by `.solution.txt`.

Example: `puzzle1.txt` → `puzzle1.solution.txt`.

Alternatively, `Main.java` can solve a predefined puzzle directly without using the API.
