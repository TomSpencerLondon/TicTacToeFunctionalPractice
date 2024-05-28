package com.tomspencerlondon.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

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
        game = game.play();

        game = game.play();

        assertThat(game.state())
            .isEqualTo(new GameState(Status.GAME_ON, Player.X));
    }
}
