package com.tomspencerlondon.tictactoe;

import static com.tomspencerlondon.tictactoe.Square.BOTTOM_LEFT;
import static com.tomspencerlondon.tictactoe.Square.BOTTOM_MIDDLE;
import static com.tomspencerlondon.tictactoe.Square.BOTTOM_RIGHT;
import static com.tomspencerlondon.tictactoe.Square.CENTRE_LEFT;
import static com.tomspencerlondon.tictactoe.Square.CENTRE_MIDDLE;
import static com.tomspencerlondon.tictactoe.Square.CENTRE_RIGHT;
import static com.tomspencerlondon.tictactoe.Square.TOP_LEFT;
import static com.tomspencerlondon.tictactoe.Square.TOP_MIDDLE;
import static com.tomspencerlondon.tictactoe.Square.TOP_RIGHT;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Board {

    private final Set<Square> takenSquares;

    public Board() {
        takenSquares = new HashSet<>();
    }

    private Board(Set<Square> takenSquares) {
        this.takenSquares = takenSquares;
    }

    public boolean alreadyPlayed(Square toPlay) {
        return takenSquares.contains(toPlay);
    }

    public Board take(Square toPlay) {
        Set<Square> newBoard = new HashSet<>(takenSquares);
        newBoard.add(toPlay);
        return new Board(newBoard);
    }

    public boolean isFull() {
        return takenSquares.size() == 9;
    }

    public boolean hasWon() {
        Stream<Stream<Square>> winningCombinations = Stream.of(
            Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
            Stream.of(CENTRE_LEFT, CENTRE_MIDDLE, CENTRE_RIGHT),
            Stream.of(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),
            Stream.of(TOP_LEFT, CENTRE_LEFT, BOTTOM_LEFT),
            Stream.of(TOP_MIDDLE, CENTRE_MIDDLE, BOTTOM_MIDDLE),
            Stream.of(TOP_RIGHT, CENTRE_RIGHT, BOTTOM_RIGHT)
        );
        return winningCombinations.anyMatch(combo -> combo.allMatch(takenSquares::contains));
    }
}
