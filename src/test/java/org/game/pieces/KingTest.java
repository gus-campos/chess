package org.game.pieces;

import org.game.Chess;
import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {

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
    public void kingCanMoveInCrossShapeOneCell() {

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("e1", "e2"));
    }

    @Test
    public void kingCanNotMoveInCrossShapeTwoCell() {

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("e1", "e2"));
    }

    @Test
    public void kingCanMoveDiagonallyOneCell() {

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("e1", "d2"));
    }

    @Test
    public void kingCanNotMoveDiagonallyTwoCells() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("e1", "b3")));
    }

    @Test
    public void kingCanNotMoveInLetterLShape() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("e1", "f3")));
    }
}
