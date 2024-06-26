package com.tomspencerlondon.tictactoe;

import static com.tomspencerlondon.tictactoe.Player.NOBODY;
import static com.tomspencerlondon.tictactoe.Player.O;
import static com.tomspencerlondon.tictactoe.Player.X;
import static com.tomspencerlondon.tictactoe.Status.DRAW;
import static com.tomspencerlondon.tictactoe.Status.O_HAS_WON;
import static com.tomspencerlondon.tictactoe.Status.X_HAS_WON;

public class Game {

    private final Status status;
    private final Player currentPlayer;
    private Board board;

    public  Game() {
        currentPlayer = null;
        board = new Board();
        status = Status.GAME_ON;
    }

    private Game(Status status, Board board, Player currentPlayer) {
        this.board = board;
        if (board.hasWon(currentPlayer)) {
            this.status = currentPlayer == X ? X_HAS_WON : O_HAS_WON;
        } else if(board.isFull()) {
            this.status = DRAW;
        } else {
            this.status = status;
        }
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        if (gameIsOver()) {
            return new GameState(status, NOBODY);
        }

        return new GameState(status, nextPlayer());
    }

    public Game play(Square toPlay) {
        if (gameIsOver()) {
            return this;
        } else if (board.alreadyPlayed(toPlay)) {
            return new Game(Status.SQUARE_ALREADY_PLAYED, board, currentPlayer);
        }

        return new Game(status, board.take(toPlay, nextPlayer()), nextPlayer());
    }

    private Player nextPlayer() {
        if (currentPlayer == null) {
            return X;
        } else {
            return currentPlayer == X ? O : X;
        }
    }

    private boolean gameIsOver() {
        return status == DRAW || status == X_HAS_WON || status == O_HAS_WON;
    }
}
