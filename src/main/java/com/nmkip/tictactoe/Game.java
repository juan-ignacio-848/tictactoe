package com.nmkip.tictactoe;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Square.NONE;
import static com.nmkip.tictactoe.Status.*;

class Game {

    private final Player lastPlayer;
    private final Square lastSquare;
    private final GameState state;

    Game() {
        this.lastPlayer = X;
        this.lastSquare = NONE;
        this.state = new GameState(X_IS_PLAYING);
    }

    private Game(Player lastPlayer, Square lastSquare, GameState state) {
        this.lastPlayer = lastPlayer;
        this.lastSquare = lastSquare;
        this.state = state;
    }

    Game placeMarkOn(Square square) {
        if (square != lastSquare)
            return new Game(nextPlayer(), square, nextState());
        else
            return this;
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
        return lastPlayer == X ? O : X;
    }
}
