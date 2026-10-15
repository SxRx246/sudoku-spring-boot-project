package com.ga.sudoku.reposetory;
import com.ga.sudoku.model.Puzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuzzleRepository extends JpaRepository<Puzzle, Long> {
    Puzzle findByLevel(Puzzle.Level level);
}
