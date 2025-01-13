package org.game.pieces;

import org.game.Chess;
import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest {

    private Chess chess;

    private final Character[][] boardDraw = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };

    @BeforeEach
    void setUp() {
        chess = new Chess();
    }

    @Test
    public void knightShouldMoveInShapeOfLetterLEvenOverOtherPieces() {

        chess.playMove(Move.of("b1", "c3"));
        chess.playMove(Move.of("b8", "a6"));
        chess.playMove(Move.of("g1", "h3"));
        chess.playMove(Move.of("g8", "h6"));
    }

    @Test
    public void knightShouldNotMoveDiagonally() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("b1", "c2")));
    }

    @Test
    public void knightShouldNotMoveInCrossShape() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("b1", "b2")));
    }
}
