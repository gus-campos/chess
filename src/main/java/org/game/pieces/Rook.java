package org.game.pieces;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Move;
import org.game.Piece;

public class Rook extends Piece {

    public Rook(Color color) {
        this.type = Type.ROOK;
        this.color = color;
        this.baseSymbol = Piece.TYPES_SYMBOLS.get(this.type);
    }

    @Override
    public boolean isValidMove(Move move) {

        return move.isCrossMove();
    }

    public Piece clone() {
        return new Rook(this.color);
    }
}
