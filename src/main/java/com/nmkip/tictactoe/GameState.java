package com.nmkip.tictactoe;

import java.util.Objects;

import static com.nmkip.tictactoe.Player.X;
import static com.nmkip.tictactoe.Status.*;

class GameState {

    private final Status status;

    GameState(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return status == gameState.status;
    }

    GameState nextState(Board nextBoard, Player currentPlayer, Player nextPlayer) {
        if (nextBoard.hasWinningCombination(currentPlayer))
            return winner(currentPlayer);
        else if (nextBoard.allTaken())
            return draw();
        else
            return playing(nextPlayer);
    }

    private GameState winner(Player currentPlayer) {
        return stateFor(currentPlayer, X_WON, O_WON);
    }

    private GameState draw() {
        return new GameState(IS_A_DRAW);
    }

    private GameState playing(Player nextPlayer) {
        return stateFor(nextPlayer, X_IS_PLAYING, O_IS_PLAYING);
    }

    private GameState stateFor(Player player, Status X_status, Status O_status) {
        return player == X
                ? new GameState(X_status)
                : new GameState(O_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "Status: " + status;
    }
}
