package org.game.pieces;

import org.game.Board;
import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Coord;
import org.game.records.Move;
import org.game.Piece;

public class Pawn extends Piece {

    private boolean enPassantFlag;

    public Pawn(Color color, Board board) {
        this.type = Type.PAWN;
        this.color = color;
        this.baseSymbol = Piece.TYPES_SYMBOLS.get(this.type);
        this.board = board;
        this.enPassantFlag = false;
    }

    @Override
    public boolean isValidMove(Move move) {

        boolean ahead = this.isGoingAhead(move);

        boolean singleMovement = move.distance() == 1;
        boolean doubleMovement = move.distance() == 2;
        boolean firstMove = this.isFirstMove(move);

        boolean diagonalMovement = move.isDiagonalMove();
        boolean isCapturing = this.isCapturing(move.to());
        //

        if (!ahead)
            return false;

        // Avanço
        if (!diagonalMovement && !isCapturing) {

            if (singleMovement)
                return true;

            if (doubleMovement && firstMove)
                return true;
        }

        // Captura
        if (diagonalMovement && isCapturing && singleMovement)
            return true;

        // Em passant
        // TODO: Adicionar

        return false;
    }

    private boolean isGoingAhead(Move move) {

        Coord delta = move.getDelta();

        if (this.color == Color.WHITE)
            return delta.row() > 0;
        else
            return delta.row() < 0;
    }

    private boolean isFirstMove(Move move) {
        if (this.color == Color.WHITE)
            return move.from().row() == 1;
        else
            return move.from().row() == 6;
    }

    private boolean isCapturing(Coord coord) {

        if (this.board == null)
            throw  new RuntimeException("This pawn has not assigned board");

        return this.board.getPiece(coord) != null;
    }

    public void setEnPassantFlag(boolean enPassantFlag) {
        this.enPassantFlag = enPassantFlag;
    }

    public boolean getEnPassantFlag() {
        return this.enPassantFlag;
    }

    public Piece clone() {
        return new Pawn(this.color, this.board);
    }
}
