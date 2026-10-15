
package com.ga.sudoku.controller;

import com.ga.sudoku.model.Puzzle;
import com.ga.sudoku.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PuzzleController {

    private final PuzzleService puzzleService;

    @GetMapping("/puzzles/{id}")
    public Puzzle getPuzzle(@PathVariable Long id) {
        return puzzleService.getPuzzle(id);
    }

    @PostMapping("/puzzles/{id}/solve")
    public String solvePuzzle(@PathVariable Long id) {
        return puzzleService.solvePuzzle(id);
    }
}
