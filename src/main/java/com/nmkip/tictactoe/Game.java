package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Status.*;

class Game {

    private final Status status;
    private final Player lastPlayer;
    private Board board;

    Game() {
        this.status = GAME_ON;
        this.lastPlayer = NONE;
        this.board = new Board();
    }

    private Game(Status status, Player lastPlayer, Board board) {
        this.lastPlayer = lastPlayer;
        this.board = board;
        if (board.hasWinningCombination(lastPlayer))
            this.status = lastPlayer == X ? WINNER_IS_X : WINNER_IS_O;
        else if (board.isFull())
            this.status = DRAW;
        else
            this.status = status;
    }

    Game play(Square square) {
        if(board.alreadyTaken(square) || gameOver())
            return this;
        else
            return new Game(GAME_ON, nextPlayer(), board.take(square, nextPlayer()));
    }

    GameState state() {
        if(gameOver())
            return new GameState(status, NONE);
        else
            return new GameState(status, nextPlayer());
    }

    private boolean gameOver() {
        return status == DRAW || status == WINNER_IS_X || status == WINNER_IS_O;
    }

    private Player nextPlayer() {
        return lastPlayer == NONE ? X : lastPlayer == X ? O : X;
    }
}
