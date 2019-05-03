package com.nmkip.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static com.nmkip.tictactoe.Square.TOP_LEFT;
import static com.nmkip.tictactoe.Square.TOP_RIGHT;
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

    @ParameterizedTest
    @CsvSource({
            "TOP_LEFT, MIDDLE_LEFT, TOP_CENTER, MIDDLE_CENTER, TOP_RIGHT",
            "MIDDLE_LEFT, TOP_LEFT, MIDDLE_CENTER, TOP_CENTER, MIDDLE_RIGHT",
            "BOTTOM_LEFT, TOP_LEFT, BOTTOM_CENTER, TOP_CENTER, BOTTOM_RIGHT",
            "TOP_LEFT, TOP_RIGHT, MIDDLE_LEFT, MIDDLE_RIGHT, BOTTOM_LEFT",
            "TOP_CENTER, TOP_RIGHT, MIDDLE_CENTER, MIDDLE_RIGHT, BOTTOM_CENTER",
            "TOP_LEFT, TOP_RIGHT, MIDDLE_CENTER, MIDDLE_LEFT, BOTTOM_RIGHT",
            "TOP_RIGHT, TOP_CENTER, MIDDLE_CENTER, BOTTOM_RIGHT, BOTTOM_LEFT"
    })
    void recognise_winning_combinations(Square s1, Square s2, Square s3, Square s4, Square s5) {
        game = placeMarksOn(s1, s2, s3, s4, s5);

        assertThat(game.state(), is(new GameState(X_WON)));
    }

    private Game placeMarksOn(Square... squares) {
        return Stream.of(squares)
              .reduce(new Game(),
                      Game::placeMarkOn,
                      (game1, game2) -> game1);
    }
}
