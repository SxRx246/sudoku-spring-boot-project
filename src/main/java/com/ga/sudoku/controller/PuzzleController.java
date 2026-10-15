
package com.ga.sudoku.controller;

import com.ga.sudoku.dto.request.PuzzleRequest;
import com.ga.sudoku.model.Puzzle;
import com.ga.sudoku.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/puzzles")
public class PuzzleController {

    private final PuzzleService puzzleService;

    @PostMapping
    public Puzzle createPuzzle(@RequestBody PuzzleRequest request){
        return puzzleService.createPuzzle(request);
    }

    @GetMapping("/{id}")
    public Puzzle getPuzzle(@PathVariable Long id) {
        return puzzleService.getPuzzle(id);
    }

    @PostMapping("/{id}/solve")
    public String solvePuzzle(@PathVariable Long id) {
        return puzzleService.solvePuzzle(id);
    }
}
