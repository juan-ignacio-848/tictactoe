package com.nmkip.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.nmkip.tictactoe.Square.*;
import static com.nmkip.tictactoe.Status.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameShould {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void wait_for_player_X_to_do_the_first_move() {
        assertThat(game.state(), is(new GameState(X_IS_PLAYING)));
    }

    @Test
    void wait_for_player_O_to_play_after_X() {
        game = game.placeMarkOn(TOP_LEFT);

        assertThat(game.state(), is(new GameState(O_IS_PLAYING)));
    }

    @Test
    void alternate_players() {
        game = game.placeMarkOn(TOP_LEFT);
        game = game.placeMarkOn(TOP_RIGHT);

        assertThat(game.state(), is(new GameState(X_IS_PLAYING)));
    }

    @Test
    void not_allow_placing_marks_on_used_squares() {
        game = game.placeMarkOn(TOP_LEFT);
        game = game.placeMarkOn(TOP_LEFT);

        assertThat(game.state(), is(new GameState(O_IS_PLAYING)));
    }

    @Test
    @Disabled
    void recognise_winning_combinations() {
        game = game.placeMarkOn(TOP_LEFT);
        game = game.placeMarkOn(MIDDLE_LEFT);
        game = game.placeMarkOn(TOP_CENTER);
        game = game.placeMarkOn(MIDDLE_CENTER);
        game = game.placeMarkOn(TOP_RIGHT);

        assertThat(game.state(), is(new GameState(X_WON)));
    }
}
