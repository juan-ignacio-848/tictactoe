package com.nmkip.tictactoe;

import java.util.Objects;

class GameState {

    private final Status status;
    private final Player next;

    GameState(Status status, Player next) {
        this.status = status;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return status == gameState.status &&
                next == gameState.next;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, next);
    }

    @Override
    public String toString() {
        return "Status: " + status + ", next: " + next;
    }
}
