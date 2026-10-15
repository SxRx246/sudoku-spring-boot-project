package com.ga.sudoku.reposetory;

import com.ga.sudoku.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuzzleRepository extends JpaRepository<Game, Long> {
}
