package org.game;

import org.game.enums.Color;
import org.game.enums.Type;
import org.game.records.Move;
import org.game.exceptions.InvalidMoveException;

import java.util.List;

public class Chess {

    private Board board;
    private Color currentPlayer;
    private boolean gameInCheck;
    private Color winner;

    public Chess() {

        this.board = new Board();
        this.currentPlayer = Color.WHITE;
        this.gameInCheck = false;
        this.winner = null;
    }

    public Chess clone() {

        Chess chess = new Chess();
        Board board = this.board.clone();
        chess.setState(board, this.currentPlayer);

        return chess;
    }

    public void setState(Character[][] boardDraw, Color currentPlayer) {
        this.board.setBoard(boardDraw);
        this.currentPlayer = currentPlayer;
    }

    public void setState(Board board, Color currentPlayer) {
        this.board = board.clone();
        this.currentPlayer = currentPlayer;
    }

    public void playMove(Move move) {

        // Verificar fazer castling
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

        this.verifyCheck();
        this.board.updateEnPassantFlags(move);
        this.progressPlayers();

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
        this.printCheck();
    }

    private void verifyCheck() {

        // Verificar quem está colocado em check
        List<Color> inCheck = this.board.inCheck();

        if (inCheck.contains(this.currentPlayer))
            throw new InvalidMoveException("Can't put your own king in check");

        if (inCheck.contains(this.getOpponent()))
            this.gameInCheck = true;
    }

    private void printCurrentPlayer() {
        System.out.println("Next Player: " + this.currentPlayer);
        System.out.println();
    }

    private void printCheck() {

        if (this.gameInCheck) {
            System.out.println("CHECK!");
            System.out.println();
        }
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

        if (this.gameInCheck && !this.willGetOutOfCheck(move))
            throw new InvalidMoveException("Must leave check");
    }

    private boolean willGetOutOfCheck(Move move) {

        if (!this.gameInCheck)
            throw new AssertionError("Current player not in check");

        Chess chess = this.clone();
        chess.playMove(move);

        List<Color> inCheck = this.board.inCheck();

        // Retornar se o player atual estará em check na próxima partida
        return !inCheck.contains(this.currentPlayer);
    }

    private void progressPlayers() {
        this.currentPlayer = this.getOpponent();
    }

    private Color getOpponent() {
        return this.currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
