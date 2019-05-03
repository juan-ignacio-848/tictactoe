package com.nmkip.tictactoe;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.nmkip.tictactoe.Square.*;

class NonEmptyBoard implements Board {

    private final Set<Square> takenSquares;

    NonEmptyBoard(Square square) {
        this.takenSquares = new HashSet<>();
        this.takenSquares.add(square);
    }

    private NonEmptyBoard(Set<Square> takenSquares) {
        this.takenSquares = takenSquares;
    }

    @Override
    public boolean taken(Square square) {
        return takenSquares.contains(square);
    }

    @Override
    public Board take(Square square) {
        HashSet<Square> nextTakenSquares = new HashSet<>(takenSquares);
        nextTakenSquares.add(square);
        return new NonEmptyBoard(nextTakenSquares);
    }

    @Override
    public boolean hasWinningCombination() {
        Stream<Stream<Square>> winningCombinations = Stream.of(
                Stream.of(TOP_LEFT, TOP_CENTER, TOP_RIGHT),
                Stream.of(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT)
        );

        return winningCombinations.anyMatch(
                aWinningCombination -> aWinningCombination.allMatch(takenSquares::contains)
        );
    }

}