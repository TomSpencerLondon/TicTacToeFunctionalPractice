package com.tomspencerlondon.tictactoe;

public class Game {

    private final Player nextPlayer;

    public  Game() {
        nextPlayer = Player.X;
    }

    private Game(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public GameState state() {
        return new GameState(Status.GAME_ON, nextPlayer);
    }

    public Game play() {
        return new Game(Player.O);
    }
}
