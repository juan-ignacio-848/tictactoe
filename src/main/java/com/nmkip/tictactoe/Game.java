package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.O;
import static com.nmkip.tictactoe.Player.X;
import static com.nmkip.tictactoe.Status.*;

class Game {

    private final Player currentPlayer;
    private final GameState state;
    private final Board board;

    Game() {
        this.currentPlayer = X;
        this.board = new EmptyBoard();
        this.state = new GameState(X_IS_PLAYING);
    }

    private Game(Player currentPlayer, Board board, GameState state) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.state = state;
    }

    Game placeMarkOn(Square square) {
        if (board.taken(square))
            return this;
        else
            return nextGame(square);
    }

    GameState state() {
        return state;
    }

    private Game nextGame(Square square) {
        Board nextBoard = board.take(square, currentPlayer);
        return new Game(nextPlayer(), nextBoard, nextState(nextBoard));
    }

    private GameState nextState(Board nextBoard) {
        if (nextBoard.hasWinningCombination(currentPlayer))
            return currentPlayer == X
                    ? new GameState(X_WON)
                    : new GameState(O_WON);
        else if (nextBoard.allTaken())
            return new GameState(IS_A_DRAW);
        else
            return nextPlayer() == X
                ? new GameState(X_IS_PLAYING)
                : new GameState(O_IS_PLAYING);
    }

    private Player nextPlayer() {
        return currentPlayer == X ? O : X;
    }
}
