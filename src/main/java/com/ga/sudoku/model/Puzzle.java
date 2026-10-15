package com.ga.sudoku.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "puzzles")
public class Puzzle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }

    @Enumerated(EnumType.STRING)
    private Level level;
    private String initialBoard;
    private String solution;
    private String hint;
}
