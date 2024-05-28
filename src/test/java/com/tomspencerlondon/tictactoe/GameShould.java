package com.tomspencerlondon.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
        "TOP_LEFT,CENTRE_LEFT,TOP_MIDDLE,CENTRE_MIDDLE,TOP_RIGHT",
        "CENTRE_LEFT,TOP_LEFT,CENTRE_MIDDLE,TOP_MIDDLE,CENTRE_RIGHT",
        "BOTTOM_LEFT,TOP_LEFT,BOTTOM_MIDDLE,TOP_MIDDLE,BOTTOM_RIGHT",
        "TOP_LEFT,TOP_MIDDLE,CENTRE_LEFT,CENTRE_MIDDLE,BOTTOM_LEFT",
        "TOP_MIDDLE,TOP_LEFT,CENTRE_MIDDLE,CENTRE_LEFT,BOTTOM_MIDDLE",
        "TOP_RIGHT,TOP_MIDDLE,CENTRE_RIGHT,CENTRE_MIDDLE,BOTTOM_RIGHT",
    })
    void recognise_when_x_has_won(Square s1, Square s2, Square s3, Square s4, Square s5) {
        Game game = play(s1, s2, s3, s4, s5);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.X_HAS_WON, Player.NOBODY));
    }

    private Game play(Square... squares) {
        return Arrays.stream(squares)
            .reduce(new Game(), Game::play, (a, b) -> null);
    }
}
