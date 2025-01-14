package org.game;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.iterators.boardIterator;
import org.game.pieces.King;
import org.game.pieces.Pawn;
import org.game.records.Coord;
import org.game.records.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private Piece[][] cells;
    private static final Coord boardShape = new Coord(8, 8);

    public Board() {

        cells = new Piece[8][8];

        Character[][] boardDraw = {
            {'r','n','b','q','k','b','n','r'},
            {'p','p','p','p','p','p','p','p'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'P','P','P','P','P','P','P','P'},
            {'R','N','B','Q','K','B','N','R'}
        };

        // Estado inicial
        this.setBoard(boardDraw);
    }

    public Board clone() {

        Board board = new Board();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (this.cells[i][j] == null)
                    board.cells[i][j] = null;
                else
                    board.cells[i][j] = this.cells[i][j].clone();
            }
        }

        return board;
    }

    public static boolean isValidCoord(Coord coord) {

        int column = coord.column();
        int row = coord.row();

        return (
            column >= 0
            && row >= 0
            && column < Board.boardShape.column()
            && row < Board.boardShape.row()
        );
    }

    public void setBoard(Character[][] boardDraw) {

        boardIterator iter = new boardIterator();
        while (iter.hasNext()) {
            Coord coord = iter.next();

            Character symbol = boardDraw[7-coord.row()][coord.column()];
            Piece piece = Piece.ofSymbol(symbol, this);
            this.setPiece(coord, piece);
        }
    }

    public boolean equals(Board board) {

        boardIterator iter = new boardIterator();
        while (iter.hasNext()) {
            Coord coord = iter.next();

            Piece piece1 = this.getPiece(coord);
            Piece piece2 = board.getPiece(coord);

            if ((piece1 == null && piece2 != null) || (piece1 != null && !piece1.equal(piece2)))
                return false;
        }

        return true;
    }

    public void movePiece(Move move) {

        Piece pieceToMove = this.getPiece(move.from());
        pieceToMove.flagAsMoved();

        this.setPiece(move.from(), null);
        this.setPiece(move.to(), pieceToMove);
    }

    public Piece getPiece(Coord coord) {
        return this.cells[coord.column()][coord.row()];
    }

    public void setPiece(Coord coord, Piece piece) {
        this.cells[coord.column()][coord.row()] = piece;
    }

    public boolean freePath(Move move) {

        /*
        Retorna se o caminho está livre para realizar o movimento.
        Itera pelas posições do caminho entre "from" e "to", e retorna falso se
        encontrar alguma peça.
        */

        Coord step = move.getStep();

        for (Coord coord = move.from().add(step); !coord.equals(move.to()); coord = coord.add(step))
            if (this.getPiece(coord) != null)
               return false;

        return true;
    }

    // CHECK

    public List<Color> inCheck() {

        List<Color> colorsInCheck = new ArrayList<>();

        // Encontrar os rei
        Map<Color, Coord> kingsCoords = new HashMap<>();

        boardIterator iter = new boardIterator();
        while (iter.hasNext()) {
            Coord coord = iter.next();
            Piece piece = this.getPiece(coord);

            if (piece instanceof King)
                kingsCoords.put(piece.getColor(), coord);
        }

        // Verificar se estão em cheque
        iter = new boardIterator();
        while (iter.hasNext()) {
            Coord coord = iter.next();
            Piece piece = this.getPiece(coord);

            // Listar moves possíveis
            // Verificar moves possíveis
            // Verificar se algum move possível captura o rei
            // Listar a cor
        }

        return colorsInCheck;
    }

    // CASTLING

    public boolean validCastling(Move move) {

        /*
        Valida o castling
        */

        Piece pieceToMove = this.getPiece(move.from());

        // Tem que ser rei que não foi movido
        if (pieceToMove.getType() != Type.KING || pieceToMove.hasBeeMoved())
            return false;

        // Tem que ser movido dois pro lado
        if (Math.abs(move.getDelta().column()) != 2)
            return false;

        // Achar torre correspondente
        Coord rookCoord = this.castlingRookCoord(move);
        Piece rook = this.getPiece(rookCoord);

        // A torre tem que existir e não ser movida
        if (rook == null || rook.hasBeeMoved())
            return false;

        // O caminho tem que estar livre
        if (!this.freePath(new Move(move.from(), rookCoord)))
            return false;

        return true;
    }

    public void doCastlingMove(Move move) {

        // Mover rei
        this.movePiece(move);

        // Mover torre
        Coord rookCoord = this.castlingRookCoord(move);
        Coord newRookCoord = move.to().sub(move.getStep());
        this.movePiece(new Move(rookCoord, newRookCoord));
    }

    private Coord castlingRookCoord(Move move) {

        boolean rightRook = move.getDelta().column() > 0;
        return new Coord(rightRook ? 7: 0, move.from().row());
    }

    // EN PASSANT

    public boolean validEnPassant(Move move) {

        Piece pieceToMove = this.getPiece(move.from());

        // Movimento tem que ser com peão
        if (!(pieceToMove instanceof Pawn))
            return false;

        // Movimento tem que ser diagonal de distância 1
        if (!move.isDiagonalMove() || move.distance() != 1)
            return false;

        // Não pode ser uma captura comum - destino tem que estar vazio
        if (this.getPiece(move.to()) != null)
            return false;

        // Peça do lado
        Coord otherPieceCoord = this.enPassantOtherPieceCoord(move);
        Piece otherPiece= this.getPiece(otherPieceCoord);

        // Peça tem que existir e ser um peão também
        if (!(otherPiece instanceof Pawn))
            return false;

        if (!((Pawn) otherPiece).getEnPassantFlag())
            return false;

        return true;
    }

    public void updateEnPassantFlags(Move move) {

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {

                Coord coord = new Coord(column, row);
                Piece piece = this.getPiece(coord);

                if (piece instanceof Pawn pawn) {

                    // Se fez o movimento duplo, true, do contrário resetar flag
                    if (coord.equals(move.to()) && move.distance() == 2)
                        pawn.setEnPassantFlag(true);
                    else
                        pawn.setEnPassantFlag(false);
                }
            }
        }
    }

    public void doEnPassantMove(Move move) {

        this.movePiece(move);
        Coord otherPieceCoord = this.enPassantOtherPieceCoord(move);
        this.setPiece(otherPieceCoord, null);
    }

    private Coord enPassantOtherPieceCoord(Move move) {

        boolean toTheRight = move.getDelta().column() > 0;
        int otherPawnColumn = move.from().column() + (toTheRight ? 1 : -1);
        return new Coord(otherPawnColumn, move.from().row());
    }

    // REPRESENTATION

    public void printBoard() {

        System.out.println(this);
        System.out.println();
    }

    public String toString() {

        StringBuilder boardString = new StringBuilder();
        char rowSymbol = '8';
        char columnSymbol = 'a';

        for (int row = 7; row >= 0; row--) {

            // ID das linhas
            boardString.append(rowSymbol--);
            boardString.append("   ");

            for (int column = 0; column < 8; column++) {

                Piece piece = this.getPiece(new Coord(column, row));
                Character symbol = piece != null ? piece.getSymbol() : '.';
                boardString.append(symbol);
                boardString.append(" ");
            }
            boardString.append("\n");
        }

        // ID das colunas
        boardString.append("\n");
        boardString.append("    ");

        for (int column = 0; column < 8; column++) {
            boardString.append(columnSymbol++);
            boardString.append(" ");
        }

        return boardString.toString();
    }
}
