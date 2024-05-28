package com.tomspencerlondon.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game adjudicator should")
public class GameShould {

    @Test
    void wait_for_x_to_play_first() {
        var game = new Game();

        assertThat(game.state())
            .isEqualTo(new GameState(Status.GAME_ON, Player.X));
    }

    @Test
    void alternate_the_players() {
        var game = new Game();
        game = game.play(Square.TOP_LEFT);

        game = game.play(Square.TOP_MIDDLE);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.GAME_ON, Player.X));
    }

    @Test
    void not_allow_a_square_to_be_played_twice() {
        var game = play(Square.TOP_LEFT, Square.TOP_MIDDLE, Square.TOP_LEFT);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.SQUARE_ALREADY_PLAYED, Player.X));
    }

    // X O X
    // O X X
    // O X O
    @Test
    void recognise_a_draw() {
        var game = play(
            Square.TOP_LEFT,
            Square.TOP_MIDDLE,
            Square.TOP_RIGHT,
            Square.CENTRE_LEFT,
            Square.CENTRE_MIDDLE,
            Square.CENTRE_RIGHT,
            Square.BOTTOM_LEFT,
            Square.BOTTOM_MIDDLE,
            Square.BOTTOM_RIGHT);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.DRAW, Player.NOBODY));
    }

    private Game play(Square... squares) {
        return Arrays.stream(squares)
            .reduce(new Game(), Game::play, (a, b) -> null);
    }
}
