package com.tomspencerlondon.tictactoe;

import static com.tomspencerlondon.tictactoe.Player.O;
import static com.tomspencerlondon.tictactoe.Player.X;

public class Game {

    private final Player currentPlayer;

    public  Game() {
        currentPlayer = null;
    }

    private Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        return new GameState(Status.GAME_ON, nextPlayer());
    }

    public Game play() {
        return new Game(nextPlayer());
    }

    private Player nextPlayer() {
        if (currentPlayer == null) {
            return X;
        } else {
            return currentPlayer == X ? O : X;
        }
    }
}
