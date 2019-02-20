package com.nmkip.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Square.*;
import static com.nmkip.tictactoe.Status.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@DisplayName("TicTacToe Game should")
class GameShould {

    @Test
    void wait_for_X_to_play_first() {
        Game game = new Game();

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }

    @Test
    void wait_for_O_to_play_after_X() {
        Game game = play(TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, O)));
    }

    @Test
    void alternate_players() {
        Game game = play(TOP_LEFT, BOTTOM_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }

    @Test
    void not_allow_to_take_the_same_square_twice() {
        Game game = play(TOP_LEFT, TOP_RIGHT, TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }

    // X O X
    // X O O
    // O X X
    @Test
    void recognise_a_draw() {
        Game game = play(
                TOP_LEFT,
                TOP_MIDDLE,
                TOP_RIGHT,
                CENTRE_MIDDLE,
                CENTRE_LEFT,
                CENTRE_RIGHT,
                BOTTOM_MIDDLE,
                BOTTOM_LEFT,
                BOTTOM_RIGHT
        );

        assertThat(game.state(), is(new GameState(DRAW, NONE)));
    }


    @ParameterizedTest
    @CsvSource({
            "TOP_LEFT, BOTTOM_MIDDLE, TOP_MIDDLE, BOTTOM_LEFT, TOP_RIGHT",
            "BOTTOM_LEFT, TOP_MIDDLE, BOTTOM_MIDDLE, TOP_LEFT, BOTTOM_RIGHT",
            "CENTRE_LEFT, TOP_MIDDLE, CENTRE_MIDDLE, TOP_LEFT, CENTRE_RIGHT",
            "TOP_LEFT, CENTRE_LEFT, CENTRE_MIDDLE, BOTTOM_LEFT, BOTTOM_RIGHT",
            "TOP_RIGHT, CENTRE_LEFT, CENTRE_MIDDLE, TOP_LEFT, BOTTOM_LEFT",
            "TOP_LEFT, CENTRE_MIDDLE, CENTRE_LEFT, TOP_MIDDLE, BOTTOM_LEFT",
            "TOP_MIDDLE, TOP_LEFT, CENTRE_MIDDLE, CENTRE_LEFT, BOTTOM_MIDDLE",
            "TOP_RIGHT, TOP_LEFT, CENTRE_RIGHT, CENTRE_LEFT, BOTTOM_RIGHT"
    })
    void recognise_a_win(Square s1, Square s2, Square s3, Square s4, Square s5) {
        Game game = play(s1, s2, s3, s4, s5);

        assertThat(game.state(), is(new GameState(WINNER_IS_X, NONE)));
    }

    @Test
    void recognise_a_win_by_O() {
        Game game = play(TOP_RIGHT, TOP_LEFT, TOP_MIDDLE, CENTRE_LEFT, CENTRE_RIGHT, BOTTOM_LEFT);

        assertThat(game.state(), is(new GameState(WINNER_IS_O, NONE)));
    }

    @Test
    void not_permit_any_further_play_after_game_is_over() {
        Game game = play(TOP_RIGHT, TOP_LEFT, TOP_MIDDLE, CENTRE_LEFT, CENTRE_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT);

        assertThat(game.state(), is(new GameState(WINNER_IS_O, NONE)));
    }

    @Test
    void not_declare_a_win_a_draw_by_mistake() {
        Game game = play(
                CENTRE_MIDDLE,
                TOP_LEFT,
                TOP_MIDDLE,
                BOTTOM_LEFT,
                CENTRE_LEFT,
                CENTRE_RIGHT,
                BOTTOM_RIGHT,
                TOP_RIGHT,
                BOTTOM_MIDDLE
                );

        assertThat(game.state(), is(new GameState(WINNER_IS_X, NONE)));
    }

    private Game play(Square... squares) {
        return Arrays.stream(squares)
                .reduce(new Game(), (game, square) -> game.play(square), (a, b) -> null);
    }

}
