package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.O;
import static com.nmkip.tictactoe.Player.X;
import static com.nmkip.tictactoe.Status.O_IS_PLAYING;
import static com.nmkip.tictactoe.Status.X_IS_PLAYING;

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
            return new Game(nextPlayer(), board.take(square), nextState());
    }

    GameState state() {
        return state;
    }

    private GameState nextState() {
        return nextPlayer() == X
                ? new GameState(X_IS_PLAYING)
                : new GameState(O_IS_PLAYING);
    }

    private Player nextPlayer() {
        return currentPlayer == X ? O : X;
    }
}
