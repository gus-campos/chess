package org.game.pieces;

import org.game.Chess;
import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
