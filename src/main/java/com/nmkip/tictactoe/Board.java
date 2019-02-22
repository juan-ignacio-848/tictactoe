package com.nmkip.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nmkip.tictactoe.Square.*;

class Board {

    final Map<Square, Player> takenSquaresByPlayer;

    Board() {
        takenSquaresByPlayer = new HashMap<>();
    }

    private Board(Map<Square, Player> takenSquaresByPlayer) {
        this.takenSquaresByPlayer = takenSquaresByPlayer;
    }

    boolean alreadyTaken(Square square) {
        return takenSquaresByPlayer.keySet().contains(square);
    }

    Board take(Square square, Player player) {
        Map<Square, Player> squares = new HashMap<>(takenSquaresByPlayer);
        squares.put(square, player);
        return new Board(squares);
    }

    boolean hasDrawCombination() {
        return takenSquaresByPlayer.size() == 9;
    }

    boolean hasWinningCombination(Player player) {
        return winningCombinations().anyMatch(winningCombination -> winningCombination.allMatch(
                takenSquaresBy(player)::contains
        ));
    }

    private Stream<Stream<Square>> winningCombinations() {
        return Stream.of(
                Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
                Stream.of(CENTER_LEFT, CENTER_MIDDLE, CENTER_RIGHT),
                Stream.of(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT),
                Stream.of(TOP_MIDDLE, CENTER_MIDDLE, BOTTOM_MIDDLE),
                Stream.of(TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTER_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_RIGHT, CENTER_MIDDLE, BOTTOM_LEFT)
        );
    }

    private Set<Square> takenSquaresBy(Player player) {
        return takenSquaresByPlayer.entrySet()
                                   .stream()
                                   .filter(entry -> entry.getValue() == player)
                                   .map(Map.Entry::getKey)
                                   .collect(Collectors.toSet());
    }
}
