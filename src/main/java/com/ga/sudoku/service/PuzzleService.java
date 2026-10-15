package com.ga.sudoku.service;

import com.ga.sudoku.exception.InformationNotFoundException;
import com.ga.sudoku.model.Puzzle;
import com.ga.sudoku.reposetory.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PuzzleService {

    private final PuzzleRepository puzzleRepository;

    public Puzzle getPuzzle(Long id){
        return puzzleRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException(
                "Puzzle with id "+ id + " not found"
        ));
    }
}
