package com.tomspencerlondon.tictactoe;

public class Board {

    private final Square takenSquare;

    public Board() {
        takenSquare = null;
    }

    private Board(Square takenSquare) {
        this.takenSquare = takenSquare;
    }

    public boolean alreadyPlayed(Square toPlay) {
        return takenSquare == toPlay;
    }

    public Board take(Square toPlay) {
        return new Board(toPlay);
    }
}