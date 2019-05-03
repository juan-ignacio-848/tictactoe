package com.nmkip.tictactoe;

public class EmptyBoard implements Board {

    @Override
    public boolean taken(Square square) {
        return false;
    }

    @Override
    public Board take(Square square, Player player) {
        return new NonEmptyBoard(square, player);
    }

    @Override
    public boolean hasWinningCombination(Player player) {
        return false;
    }

    @Override
    public boolean allTaken() {
        return false;
    }
}
