package org.game;

import org.game.exceptions.InvalidMoveException;
import org.game.records.Move;

import java.util.Scanner;

public class PlayChess {
    public static void main(String[] args) {

        Chess chess = new Chess();
        chess.printState();

        while (true)
            PlayChess.playInputIfValid(chess);

    }

    private static void playInput(Chess chess) {

        System.out.print("Insert move: ");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] cellsStrings = line.split(" ");

        chess.playMove(Move.of(cellsStrings[0], cellsStrings[1]));
        System.out.println();
        chess.printState();
    }

    private static void playInputIfValid(Chess chess) {

        try {
            PlayChess.playInput(chess);
        }
        catch (InvalidMoveException e) {

        }
    }
}