package com.nmkip.tictactoe;

public class EmptyBoard implements Board {

    @Override
    public boolean taken(Square square) {
        return false;
    }

    @Override
    public Board take(Square square) {
        return new NonEmptyBoard(square);
    }

    @Override
    public boolean hasWinningCombination() {
        return false;
    }
}
