package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Status.DRAW;
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
        this.lastPlayer = lastPlayer;
        this.board = board;

        if(board.hasDrawCombination())
            this.status = DRAW;
        else
            this.status = status;

    }

    Game play(Square square) {
        if (board.alreadyTaken(square))
            return this;
        else
            return new Game(GAME_ON, nextPlayer(), board.take(square));
    }

    GameState state() {
        return new GameState(status, nextPlayer());
    }

    private Player nextPlayer() {
        if(status == DRAW)
            return NONE;
        else if (lastPlayer == NONE)
            return X;
        else
            return lastPlayer == X ? O : X;
    }
}
