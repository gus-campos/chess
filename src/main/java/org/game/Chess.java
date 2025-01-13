package org.game;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Move;
import org.game.exceptions.InvalidMoveException;

public class Chess {

    private final Board board;
    private Color currentPlayer;
    private Color inCheck;
    private Color winner;

    public Chess() {

        this.board = new Board();
        this.currentPlayer = Color.WHITE;
        this.inCheck = null;
        this.winner = null;
    }

    public void setState(Character[][] boardDraw, Color currentPlayer) {
        this.board.setBoard(boardDraw);
        this.currentPlayer = currentPlayer;
    }

    public void playMove(Move move) {

        // Fazer castling e retornar
        if (this.board.validCastling(move)) {
            this.board.doCastlingMove(move);
        }

        // Verificar e fazer en passant
        else if (this.board.validEnPassant(move)) {
            this.board.doEnPassantMove(move);
        }

        // Processar movimentação comum
        else {
            this.validateMove(move);
            this.board.movePiece(move);
        }

        this.board.updateEnPassantFlags(move);
        this.progressPlayers();

        // Verificar se colocou em cheque
        if (this.board.isInCheck(this.getOpponent()))
            this.inCheck = this.getOpponent();

        // Verificar cheque mate
            // Está em cheque
            // Não tem como sair do cheque
                // Listar todas as ações possíveis
                // Em algum jogo resultante, o rei está fora do cheque?


                // Verificar para cada movimento possível do rei se algum o livra do cheque
                // Considerar para cada peça adversária, se alguma pode capturar o rei
                // Verificar para cada movimento possível das outras peças, se algum livra o rei do cheque
                    // Verificar se alguma peça pode capturar a peça que põe em cheque (mais de uma?)
                    // Verificar se alguma peça pode proteger o rei de ser capturado (observar exceção do cavalo)

    }

    public boolean equals(Chess chess) {
        return this.board.equals(chess.board) && this.currentPlayer == chess.currentPlayer;
    }

    public void printState() {

        this.board.printBoard();
        this.printCurrentPlayer();
    }

    private void printCurrentPlayer() {
        System.out.println("Next Player: " + this.currentPlayer);
        System.out.println();
    }

    private void validateMove(Move move) {

        Piece pieceToMove = this.board.getPiece(move.from());
        Piece pieceToCapture = this.board.getPiece(move.to());

        if (pieceToMove == null)
            throw new InvalidMoveException("No piece to move in the given cell");

        if (pieceToMove.getColor() != this.currentPlayer)
            throw new InvalidMoveException("Can only play with its own pieces");

        if (move.from().equals(move.to()))
            throw new InvalidMoveException("From and to cell must be different");

        if (!pieceToMove.isValidMove(move))
            throw new InvalidMoveException("Invalid move for " + pieceToMove.getType());

        if (!this.board.freePath(move) && pieceToMove.getType() != Type.KNIGHT)
            throw new InvalidMoveException("Can't jump over pieces to move");

        if (pieceToCapture != null && pieceToCapture.getColor() == this.currentPlayer)
            throw new InvalidMoveException("Can't capture its own pieces");
    }

    private void progressPlayers() {
        this.currentPlayer = this.getOpponent();
    }

    private Color getOpponent() {
        return this.currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
