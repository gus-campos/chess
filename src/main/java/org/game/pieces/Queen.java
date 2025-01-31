package org.game.pieces;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Move;
import org.game.Piece;

public class Queen extends Piece {

    public Queen(Color color) {
        this.type = Type.QUEEN;
        this.color = color;
        this.baseSymbol = Piece.TYPES_SYMBOLS.get(this.type);
    }

    @Override
    public boolean isValidMove(Move move) {

        return move.isDiagonalMove() || move.isCrossMove();
    }

    public Piece clone() {
        return new Queen(this.color);
    }
}
