package com.nmkip.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.nmkip.tictactoe.Player.*;
import static com.nmkip.tictactoe.Square.*;
import static com.nmkip.tictactoe.Status.*;
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
        Game game = new Game();

        game = game.play(TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, O)));
    }

    @Test
    void alternate_players() {
        Game game = new Game();

        game = game.play(TOP_LEFT);
        game = game.play(TOP_RIGHT);

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }

    @Test
    void not_allow_different_players_to_play_on_a_played_square() {
        Game game = new Game();

        game = game.play(TOP_LEFT);
        game = game.play(TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, O)));
    }

    @Test
    void not_allow_the_same_player_to_play_on_a_played_square() {
        Game game = new Game();

        game = game.play(TOP_LEFT);
        game = game.play(TOP_RIGHT);
        game = game.play(TOP_LEFT);

        assertThat(game.state(), is(new GameState(GAME_ON, X)));
    }
}
