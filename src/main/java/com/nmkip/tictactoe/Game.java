package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Status.GAME_ON;

class Game {

    private final Status status;
    private final Player lastPlayer;
    private final Board board;

    Game() {
        this.status = GAME_ON;
        this.lastPlayer = NONE;
        this.board = new Board();
    }

    private Game(Status status, Player lastPlayer, Board board) {
        this.status = status;
        this.lastPlayer = lastPlayer;
        this.board = board;
    }

    Game play(Square square) {
        if (board.alreadyTaken(square))
            return this;
        else
            return new Game(GAME_ON, nextPlayer(), board.take(square));
    }

    GameState state() {
        return new GameState(GAME_ON, nextPlayer());
    }

    private Player nextPlayer() {
        if (lastPlayer == NONE)
            return X;
        else
            return lastPlayer == X ? O : X;
    }
}
