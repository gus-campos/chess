package org.game.pieces;

import org.game.enums.Color;
import org.game.records.Move;
import org.game.enums.Type;
import org.game.Piece;

public class Bishop extends Piece {

    public Bishop(Color color) {
        this.type = Type.BISHOP;
        this.color = color;
        this.baseSymbol = Piece.TYPES_SYMBOLS.get(this.type);
    }

    @Override
    public boolean isValidMove(Move move) {
        return move.isDiagonalMove();
    }
}
