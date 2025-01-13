package org.game;
import org.game.enums.Color;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessTest {

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

class PawnTest {

    // Não implementado:

        // En passant
            // Só captura en passant logo após adversário mover 2 células
            // Pode ignorar o en passant

        // Castling
            // Pode fazer o castling se caminho liberado?
            // Não pode fazer o castling se tiver movido o rei

    private Chess chess;

    @BeforeEach
    void setUp() {
        chess = new Chess();
    }

    @Test
    public void pawnCanMoveOneCell() {

        chess.playMove(Move.of("a2", "a3"));
        chess.playMove(Move.of("d7", "d6"));
    }

    @Test
    public void pawnCanMoveTwoCellsOnFirstMove() {

        chess.playMove(Move.of("a2", "a4"));
        chess.playMove(Move.of("d7", "d5"));
    }

    @Test
    public void pawnShouldNotMoveTwoCellsOnSecondMove() {

        chess.playMove(Move.of("a2", "a4"));
        chess.playMove(Move.of("d7", "d5"));

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a4", "a6")));
    }

    @Test
    public void pawnShouldNotMoveDiagonally() {

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a2", "b3")));
    }

    @Test
    public void pawnShouldNotMoveThreeCells() {

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a2", "b5")));
    }

    @Test
    public void pawnShouldNotMoveThreeCellsOnSecondMove() {

        chess.playMove(Move.of("a2", "a3"));
        chess.playMove(Move.of("d7", "d5"));

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("a3", "a6")));
    }

    @Test
    public void pawnCanNotCaptureOnCrossMove() {

        chess.playMove(Move.of("d2", "d4"));
        chess.playMove(Move.of("d7", "d5"));

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("d4", "d5")));
    }

    @Test
    public void pawnCanCaptureOnDiagonalMove() {

        chess.playMove(Move.of("d2", "d4"));
        chess.playMove(Move.of("e7", "e5"));
        chess.playMove(Move.of("d4", "e5"));
    }

    @Test
    public void pawnCanNotMoveOnDiagonalIfNotCapturing() {

        chess.playMove(Move.of("d2", "d4"));
        chess.playMove(Move.of("e7", "e5"));

        assertThrows(InvalidMoveException.class, () -> chess.playMove(Move.of("d4", "f5")));
    }

}

class KnightTest {

    private Chess chess;

    private final Character[][] boardDraw = {
        {'r','n','b','q','k','b','n','r'},
        {'p','p','p','p','p','p','p','p'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'R','N','B','Q','K','B','N','R'}
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

class RookTest {

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
}

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
