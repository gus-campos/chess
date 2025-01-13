package org.game.pieces;

import org.game.Chess;
import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopTest {

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
    public void bishopCanMoveDiagonally() {

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("c1", "e3"));
    }

    @Test
    public void bishopShouldNotMoveInCrossShape() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("c1", "c4")));
    }

    @Test
    public void bishopCanNotMoveInLetterLShape() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("c1", "d3")));
    }

    @Test
    public void bishopCanNotMoveOverOtherPieces() {
        chess.setState(boardDraw, Color.BLACK);

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("c8", "e6")));
    }
}
