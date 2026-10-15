
package com.ga.sudoku.dto.request;

import com.ga.sudoku.model.Puzzle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PuzzleRequest {

    private Puzzle.Level level;
    private String initialBoard;
    private String solution;
    private String hint;
}
