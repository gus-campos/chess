package org.game.pieces;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Move;
import org.game.Piece;

public class King extends Piece {

    public King(Color color) {
        this.type = Type.KING;
        this.color = color;
        this.baseSymbol = Piece.TYPES_SYMBOLS.get(this.type);
    }

    @Override
    public boolean isValidMove(Move move) {
        return (move.isDiagonalMove() || move.isCrossMove()) && move.distance() == 1;
    }

    public Piece clone() {
        return new King(this.color);
    }
}
