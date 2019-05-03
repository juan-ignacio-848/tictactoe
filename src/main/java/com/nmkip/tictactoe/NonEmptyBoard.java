package com.nmkip.tictactoe;

class NonEmptyBoard implements Board {

    private Square lastSquare;

    NonEmptyBoard(Square square) {
        this.lastSquare = square;
    }

    @Override
    public boolean taken(Square square) {
        return square == lastSquare;
    }

    @Override
    public Board take(Square square) {
        return new NonEmptyBoard(square);
    }
}