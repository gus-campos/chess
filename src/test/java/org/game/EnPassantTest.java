package org.game;

import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnPassantTest {

    private Chess chess;

    @BeforeEach
    void setUp() {
        chess = new Chess();
    }

    @Test
    public void enPassantOnFreshlyDoubledMovedPawnResultsInExpectedResult() {

        Chess model = new Chess();

        final Character[][] expectedDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', ' ', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };

        model.setState(expectedDraw, Color.BLACK);

        chess.playMove(Move.of("d2", "d4"));
        chess.playMove(Move.of("c7", "c5"));
        chess.playMove(Move.of("d4", "d5"));
        chess.playMove(Move.of("e7", "e5"));
        chess.playMove(Move.of("d5", "e6"));

        assertTrue(chess.equals(model));
    }

    @Test
    public void canDoEnPassantOnNotFreshlyDoubledMovedPawn() {

        chess.playMove(Move.of("d2", "d4"));
        chess.playMove(Move.of("c7", "c5"));
        chess.playMove(Move.of("d4", "d5"));
        chess.playMove(Move.of("e7", "e5"));
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("d5", "c6")));
    }
}
