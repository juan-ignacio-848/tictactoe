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
        Stream<Square> winningCombination = Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT);

        return winningCombination.allMatch(takenSquares::contains);
    }

    Board take(Square square) {
        Set<Square> squares = new HashSet<>(takenSquares);
        squares.add(square);
        return new Board(squares);
    }
}
