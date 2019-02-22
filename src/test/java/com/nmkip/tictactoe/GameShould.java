package com.nmkip.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Square.*;
import static com.nmkip.tictactoe.Status.*;
import static java.util.Arrays.stream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@DisplayName("TicTacToe Game should")
class GameShould {

    @Test
    void wait_for_X_to_make_the_first_move() {
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
        Game game = play(TOP_LEFT, TOP_RIGHT);

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }

    @Test
    void not_allow_different_players_to_play_on_a_played_square() {
        Game game = play(TOP_LEFT, TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, O)));
    }

    @Test
    void not_allow_the_same_player_to_play_on_a_played_square() {
        Game game = play(TOP_LEFT, TOP_RIGHT, TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }

    @Test
    void recognise_a_draw() {
        Game game = play(
                CENTER_MIDDLE,
                TOP_RIGHT,
                TOP_LEFT,
                BOTTOM_RIGHT,
                CENTER_RIGHT,
                CENTER_LEFT,
                BOTTOM_LEFT,
                BOTTOM_MIDDLE,
                TOP_MIDDLE
        );

        assertThat(game.state(), is(new GameState(DRAW, NONE)));
    }

    @ParameterizedTest
    @CsvSource({
            "TOP_LEFT, BOTTOM_LEFT, TOP_MIDDLE, BOTTOM_MIDDLE, TOP_RIGHT",
            "CENTER_LEFT, BOTTOM_LEFT, CENTER_MIDDLE, BOTTOM_MIDDLE, CENTER_RIGHT",
            "BOTTOM_LEFT, TOP_LEFT, BOTTOM_MIDDLE, TOP_MIDDLE, BOTTOM_RIGHT",
            "TOP_LEFT, TOP_RIGHT, CENTER_LEFT, CENTER_RIGHT, BOTTOM_LEFT",
            "TOP_MIDDLE, TOP_RIGHT, CENTER_MIDDLE, CENTER_RIGHT, BOTTOM_MIDDLE",
            "TOP_RIGHT, TOP_LEFT, CENTER_RIGHT, CENTER_LEFT, BOTTOM_RIGHT",
            "TOP_LEFT, CENTER_RIGHT, CENTER_MIDDLE, BOTTOM_LEFT, BOTTOM_RIGHT",
            "TOP_RIGHT, CENTER_LEFT, CENTER_MIDDLE, BOTTOM_RIGHT, BOTTOM_LEFT"
    })
    void recognise_winning_combinations(Square s1, Square s2, Square s3, Square s4, Square s5) {
        Game game = play(s1, s2, s3, s4, s5);

        assertThat(game.state(), is(new GameState(WINNER_IS_X, NONE)));
    }

    @Test
    void not_declare_a_win_a_draw() {
        Game game = play(
                CENTER_MIDDLE,
                TOP_MIDDLE,
                TOP_LEFT,
                TOP_RIGHT,
                BOTTOM_LEFT,
                CENTER_LEFT,
                BOTTOM_MIDDLE,
                CENTER_RIGHT,
                BOTTOM_RIGHT
        );

        assertThat(game.state(), is(new GameState(WINNER_IS_X, NONE)));
    }

    @Test
    void recognise_winning_by_O() {
        Game game = play(TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT, CENTER_MIDDLE, BOTTOM_MIDDLE, CENTER_RIGHT);

        assertThat(game.state(), is(new GameState(WINNER_IS_O, NONE)));
    }

    private Game play(Square... squares) {
        return stream(squares)
                .reduce(new Game(), Game::play, (game, game2) -> game);
    }

}
