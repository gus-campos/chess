package org.game;

import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CastlingTest {

    private Chess chess;

    @BeforeEach
    void setUp() {
        chess = new Chess();
    }

    @Test
    public void canDoCastlingRightNothingMoved() {

        final Character[][] boardDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("e1", "g1"));
    }

    @Test
    public void canDoCastlingLeftNothingMoved() {

        final Character[][] boardDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("e1", "c1"));
    }

    @Test
    public void canNotDoCastlingIfSomethingInTheWay() {

        final Character[][] boardDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', 'B', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        chess.setState(boardDraw, Color.WHITE);
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("e1", "c1")));
    }

    @Test
    public void canNotDoCastlingIfKingMoved() {

        final Character[][] boardDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("e1", "d1"));
        chess.playMove(Move.of("a7", "a6"));
        chess.playMove(Move.of("d1", "e1"));
        chess.playMove(Move.of("b7", "b6"));
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("e1", "c1")));
    }

    @Test
    public void canNotDoCastlingIfRookMoved() {

        final Character[][] boardDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        chess.setState(boardDraw, Color.WHITE);
        chess.playMove(Move.of("a1", "b1"));
        chess.playMove(Move.of("a7", "a6"));
        chess.playMove(Move.of("b1", "a1"));
        chess.playMove(Move.of("b7", "b6"));
        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("e1", "c1")));
    }

    @Test
    public void whenWhiteCastlingToRightExpectedResultIsGot() {

        final Character[][] initialDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        final Character[][] expectedDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '}
        };

        Chess chess1 = new Chess();
        chess1.setState(initialDraw, Color.WHITE);
        chess1.playMove(Move.of("e1", "g1"));

        Chess chess2 = new Chess();
        chess2.setState(expectedDraw, Color.BLACK);

        assertTrue(chess1.equals(chess2));
    }

    @Test
    public void whenWhiteCastlingToLeftExpectedResultIsGot() {

        final Character[][] initialDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
        };

        final Character[][] expectedDraw = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'K', 'R', ' ', ' ', ' ', 'R'}
        };

        Chess chess1 = new Chess();
        chess1.setState(initialDraw, Color.WHITE);
        chess1.playMove(Move.of("e1", "c1"));

        Chess chess2 = new Chess();
        chess2.setState(expectedDraw, Color.BLACK);

        assertTrue(chess1.equals(chess2));
    }

    static class ChessTest {

        private Chess chess1;
        private Chess chess2;

        @BeforeEach
        void setUp() {

            chess1 = new Chess();
            chess2 = new Chess();
        }

        @Test
        public void initialStateChessGamesAreEquals() {
            assertTrue(chess1.equals(chess2));
        }

        @Test
        public void gameWithSameMovesMadeAreStillEquals() {
            chess1.playMove(Move.of("a2","a4"));
            chess1.playMove(Move.of("b7","b6"));

            chess2.playMove(Move.of("a2","a4"));
            assertFalse(chess1.equals(chess2));
            chess2.playMove(Move.of("b7","b6"));
            assertTrue(chess1.equals(chess2));
        }
    }

    static class RookTest {

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
        public void rookCanMoveInCrossShape() {

            chess.setState(boardDraw, Color.WHITE);
            chess.playMove(Move.of("a1", "a4"));
            chess.playMove(Move.of("a7", "a6"));
            chess.playMove(Move.of("a4", "g4"));
        }

        @Test
        public void rookShouldNotMoveDiagonally() {

            chess.setState(boardDraw, Color.WHITE);
            assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a1", "c3")));
        }

        @Test
        public void rookCanNotMoveInLetterLShape() {

            chess.setState(boardDraw, Color.WHITE);
            assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a1", "b3")));
        }

        @Test
        public void rookCanNotMoveOverOtherPieces() {
            chess.setState(boardDraw, Color.BLACK);

            assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a8", "a6")));
        }
    }
}
