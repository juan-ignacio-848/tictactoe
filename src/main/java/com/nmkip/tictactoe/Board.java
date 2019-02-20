package com.nmkip.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.nmkip.tictactoe.Square.*;
import static java.util.stream.Collectors.toSet;

class Board {

    private final Map<Square, Player> takenSquaresByPlayer;

    Board() {
        takenSquaresByPlayer = new HashMap<>();
    }

    private Board(Map<Square, Player> squares) {
        this.takenSquaresByPlayer = squares;
    }

    boolean alreadyTaken(Square square) {
        return takenSquaresByPlayer.keySet().contains(square);
    }

    Board take(Square square, Player player) {
        Map<Square, Player> squaresByPlayer = new HashMap<>(takenSquaresByPlayer);
        squaresByPlayer.put(square, player);
        return new Board(squaresByPlayer);
    }

    boolean isFull() {
        return takenSquaresByPlayer.size() == 9;
    }

    boolean hasWinningCombination(Player player) {
        Stream<Stream<Square>> winningCombinations = Stream.of(
                Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
                Stream.of(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),
                Stream.of(CENTRE_LEFT, CENTRE_MIDDLE, CENTRE_RIGHT),
                Stream.of(TOP_LEFT, CENTRE_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_RIGHT, CENTRE_MIDDLE, BOTTOM_LEFT),
                Stream.of(TOP_LEFT, CENTRE_LEFT, BOTTOM_LEFT),
                Stream.of(TOP_MIDDLE, CENTRE_MIDDLE, BOTTOM_MIDDLE),
                Stream.of(TOP_RIGHT, CENTRE_RIGHT, BOTTOM_RIGHT)
        );

        return winningCombinations.anyMatch(
                winningCombination -> winningCombination.allMatch(squaresTakenBy(player)::contains));
    }

    private Set<Square> squaresTakenBy(Player player) {
        return takenSquaresByPlayer.entrySet().stream()
                                   .filter(entry -> entry.getValue() == player)
                                   .map(entry -> entry.getKey())
                                   .collect(toSet());
    }
}
