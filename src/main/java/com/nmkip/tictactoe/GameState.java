package com.nmkip.tictactoe;

import java.util.Objects;

public class GameState {

    private final Status status;
    private final Player nextUp;

    GameState(Status status, Player nextUp) {
        this.status = status;
        this.nextUp = nextUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return status == gameState.status &&
                nextUp == gameState.nextUp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nextUp);
    }

    @Override
    public String toString() {
        return "Status: " + status + ", next up: " + nextUp;
    }
}
