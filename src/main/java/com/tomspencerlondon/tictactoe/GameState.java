package com.tomspencerlondon.tictactoe;

public class GameState {

    private final Status status;
    private final Player nextUp;

    public GameState(Status status, Player nextUp) {
        this.status = status;
        this.nextUp = nextUp;
    }

    @Override
    public String toString() {
        return "GameState{" +
            "status=" + status +
            ", nextUp=" + nextUp +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameState gameState = (GameState) o;

        if (status != gameState.status) {
            return false;
        }
        return nextUp == gameState.nextUp;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + nextUp.hashCode();
        return result;
    }
}
