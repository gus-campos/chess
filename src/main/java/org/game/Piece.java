package org.game;
import org.game.enums.Color;
import org.game.enums.Type;
import org.game.pieces.*;
import org.game.records.Move;

import javax.lang.model.element.PackageElement;
import java.util.Map;

public abstract class Piece {

    protected Type type;
    protected Color color;
    protected Character baseSymbol;
    protected Board board;
    protected boolean moved;

    protected static final Map<Type, Character> TYPES_SYMBOLS = Map.of(
            Type.KING, 'K',
            Type.QUEEN, 'Q',
            Type.BISHOP, 'B',
            Type.PAWN, 'P',
            Type.KNIGHT, 'N',
            Type.ROOK, 'R'
    );

    protected static final Map<Character, Type> SYMBOLS_TYPES = Map.of(
            'K', Type.KING,
            'Q', Type.QUEEN,
            'B', Type.BISHOP,
            'P', Type.PAWN,
            'N', Type.KNIGHT,
            'R', Type.ROOK
    );

    abstract public boolean isValidMove(Move move);

    public boolean equal(Piece piece) {

        if (piece == null)
            return false;

        return this.type == piece.type && this.color == piece.color;
    }

    public abstract Piece clone();

    public static Piece ofSymbol(Character symbol, Board board) {

        if (symbol == ' ')
            return null;

        Type type = Piece.SYMBOLS_TYPES.get(Character.toUpperCase(symbol));
        Color color = Piece.symbolColor(symbol);

        // Trocar por istance of as verificações de tipo?

        switch (type) {

            case Type.KING:
                return new King(color);

            case Type.QUEEN:
                return new Queen(color);

            case Type.BISHOP:
                return new Bishop(color);

            case Type.KNIGHT:
                return new Knight(color);

            case Type.ROOK:
                return new Rook(color);

            case Type.PAWN:
                return new Pawn(color, board);

            default:
                throw new IllegalArgumentException("Not a valid piece symbol");
        }
    }

    private static Color symbolColor(Character symbol) {
        return Character.isUpperCase(symbol) ? Color.WHITE : Color.BLACK;
    }

    public void flagAsMoved() { this.moved = true; }

    public boolean hasBeeMoved() { return moved; }

    public Character getSymbol() {
        return this.color == Color.WHITE ? this.baseSymbol : Character.toLowerCase(this.baseSymbol);
    }

    public Type getType() {
        return this.type;
    }

    public Color getColor() {
        return this.color;
    }
}