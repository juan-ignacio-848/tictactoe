package com.nmkip.tictactoe;

public interface Board {
    boolean taken(Square square);
    Board take(Square square);
}
