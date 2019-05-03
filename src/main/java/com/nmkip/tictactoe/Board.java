package com.nmkip.tictactoe;

public interface Board {
    Board take(Square square, Player player);
    boolean taken(Square square);
    boolean allTaken();
    boolean hasWinningCombination(Player player);
}
