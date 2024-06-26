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
        game = game.play(TOP_LEFT);

        game = game.play(TOP_MIDDLE);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.GAME_ON, Player.X));
    }

    @Test
    void not_allow_a_square_to_be_played_twice() {
        var game = play(TOP_LEFT, TOP_MIDDLE, TOP_LEFT);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.SQUARE_ALREADY_PLAYED, Player.X));
    }

    @ParameterizedTest
    @CsvSource({
        "TOP_LEFT,TOP_MIDDLE,TOP_RIGHT,CENTRE_LEFT,CENTRE_MIDDLE,BOTTOM_LEFT,CENTRE_RIGHT,BOTTOM_RIGHT,BOTTOM_MIDDLE",
        "TOP_LEFT,TOP_MIDDLE,TOP_RIGHT,CENTRE_MIDDLE,CENTRE_LEFT,CENTRE_RIGHT,BOTTOM_MIDDLE,BOTTOM_LEFT,BOTTOM_RIGHT"
    })
    void recognise_a_draw(Square s1, Square s2, Square s3, Square s4, Square s5, Square s6, Square s7, Square s8, Square s9) {
        var game = play(s1, s2, s3, s4, s5, s6, s7, s8, s9);

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
        "TOP_RIGHT,TOP_LEFT,CENTRE_RIGHT,CENTRE_LEFT,BOTTOM_RIGHT",
        "TOP_LEFT,BOTTOM_LEFT,CENTRE_MIDDLE,TOP_RIGHT,BOTTOM_RIGHT",
        "TOP_RIGHT,BOTTOM_RIGHT,CENTRE_MIDDLE,TOP_MIDDLE,BOTTOM_LEFT"
    })
    void recognise_when_x_has_won(Square s1, Square s2, Square s3, Square s4, Square s5) {
        Game game = play(s1, s2, s3, s4, s5);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.X_HAS_WON, Player.NOBODY));
    }

    @Test
    void recognise_when_o_has_won() {
        Game game = play(
            BOTTOM_LEFT,
            TOP_LEFT,
            CENTRE_LEFT,
            TOP_MIDDLE,
            CENTRE_MIDDLE,
            TOP_RIGHT);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.O_HAS_WON, Player.NOBODY));
    }

    @Test
    void not_permit_play_after_game_is_won() {
        var game = play(
            TOP_LEFT,
            CENTRE_LEFT,
            TOP_MIDDLE,
            CENTRE_MIDDLE,
            TOP_RIGHT,
            CENTRE_RIGHT);

        assertThat(game.state())
            .isEqualTo(new GameState(Status.X_HAS_WON, Player.NOBODY));
    }

    // X O X
    // O X X
    // O O X
    @Test
    void recognise_win_when_won_on_final_square() {
        Game game = play(
            TOP_LEFT,
            TOP_MIDDLE,
            TOP_RIGHT,
            CENTRE_LEFT,
            CENTRE_MIDDLE,
            BOTTOM_LEFT,
            CENTRE_RIGHT,
            BOTTOM_MIDDLE,
            BOTTOM_RIGHT
        );

        assertThat(game.state())
            .isEqualTo(new GameState(Status.X_HAS_WON, Player.NOBODY));
    }

    private Game play(Square... squares) {
        return Arrays.stream(squares)
            .reduce(new Game(), Game::play, (a, b) -> null);
    }
}
