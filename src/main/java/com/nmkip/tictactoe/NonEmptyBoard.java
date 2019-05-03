package com.nmkip.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nmkip.tictactoe.Square.*;

class NonEmptyBoard implements Board {

    private static final int NUMBER_OF_DISTINCT_SQUARES = 9;

    private final Map<Square, Player> takenSquares;

    NonEmptyBoard(Square square, Player player) {
        this.takenSquares = new HashMap<>();
        this.takenSquares.put(square, player);
    }

    private NonEmptyBoard(Map<Square, Player> takenSquares) {
        this.takenSquares = takenSquares;
    }

    @Override
    public boolean taken(Square square) {
        return takenSquares.keySet().contains(square);
    }

    @Override
    public Board take(Square square, Player player) {
        Map<Square, Player> nextTakenSquares = new HashMap<>(takenSquares);
        nextTakenSquares.put(square, player);
        return new NonEmptyBoard(nextTakenSquares);
    }

    @Override
    public boolean hasWinningCombination(Player player) {
        return winningCombinations().anyMatch(
                aWinningCombination -> aWinningCombination.allMatch(takenSquaresBy(player))
        );
    }

    private Predicate<Square> takenSquaresBy(Player player) {
        return squaresOf(player)::contains;
    }

    private Set<Square> squaresOf(Player player) {
        return takenSquares.entrySet()
                           .stream()
                           .filter(entry -> entry.getValue() == player)
                           .map(Map.Entry::getKey)
                           .collect(Collectors.toSet());
    }

    @Override
    public boolean allTaken() {
        return takenSquares.size() == NUMBER_OF_DISTINCT_SQUARES;
    }

    private Stream<Stream<Square>> winningCombinations() {
        return Stream.of(
                Stream.of(TOP_LEFT, TOP_CENTER, TOP_RIGHT),
                Stream.of(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT),
                Stream.of(BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT),
                Stream.of(TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER),
                Stream.of(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT),
                Stream.of(TOP_RIGHT, MIDDLE_CENTER, BOTTOM_LEFT)
        );
    }

}