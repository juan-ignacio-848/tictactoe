package com.nmkip.tictactoe;

import java.util.HashSet;
import java.util.Set;

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

    Board take(Square square) {
        Set<Square> squares = new HashSet<>(takenSquares);
        squares.add(square);
        return new Board(squares);
    }
}
