package org.game.pieces;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Coord;
import org.game.records.Move;
import org.game.Piece;

public class Knight extends Piece {

    public Knight(Color color) {
        this.type = Type.KNIGHT;
        this.color = color;
        this.baseSymbol = Piece.TYPES_SYMBOLS.get(this.type);
    }

    @Override
    public boolean isValidMove(Move move) {
        return Knight.isKnightMove(move);
    }

    public static boolean isKnightMove (Move move) {

        Coord delta = move.getDelta();
        int moveLength = Math.abs(delta.column());
        int moveHeight = Math.abs(delta.row());

        return (moveLength == 2 && moveHeight == 1) || (moveLength == 1 && moveHeight == 2);
    }
}
