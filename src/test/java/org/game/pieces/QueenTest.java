package org.game.pieces;

import org.game.Chess;
import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueenTest {

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
    public void queenCanMoveInCrossShape() {

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("d1", "d4"));
    }

    @Test
    public void queenCanMoveDiagonallyO() {

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("d1", "a4"));
    }

    @Test
    public void queenCanNotMoveInLetterLShape() {

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("d1", "e3")));
    }

    @Test
    public void queenCanNotMoveOverOtherPieces() {
        chess.setState(boardDraw, Color.BLACK);

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("d8", "d6")));
    }
}
