package com.nmkip.tictactoe;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.nmkip.tictactoe.Square.*;

class Board {

    final Set<Square> takenSquares;

    Board() {
        takenSquares = new HashSet<>();
    }

    private Board(Set<Square> square) {
        this.takenSquares = square;
    }

    boolean alreadyTaken(Square square) {
        return takenSquares.contains(square);
    }

    boolean hasDrawCombination() {
        return takenSquares.size() == 9;
    }

    boolean hasWinningCombination() {
        Stream<Stream<Square>> winningCombinations = Stream.of(
                Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
                Stream.of(CENTER_LEFT, CENTER_MIDDLE, CENTER_RIGHT),
                Stream.of(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT),
                Stream.of(TOP_MIDDLE, CENTER_MIDDLE, BOTTOM_MIDDLE),
                Stream.of(TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTER_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_RIGHT, CENTER_MIDDLE, BOTTOM_LEFT)
        );

        return winningCombinations.anyMatch(winningCombination -> winningCombination.allMatch(takenSquares::contains));
    }

    Board take(Square square) {
        Set<Square> squares = new HashSet<>(takenSquares);
        squares.add(square);
        return new Board(squares);
    }
}
