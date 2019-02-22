package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Status.DRAW;
import static com.nmkip.tictactoe.Status.GAME_ON;
import static com.nmkip.tictactoe.Status.WINNER_IS_X;

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

        if (board.hasDrawCombination())
            this.status = DRAW;
        else if (board.hasWinningCombination())
            this.status = WINNER_IS_X;
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
        if (status == DRAW || status == WINNER_IS_X)
            return NONE;
        else if (lastPlayer == NONE)
            return X;
        else
            return lastPlayer == X ? O : X;
    }
}
