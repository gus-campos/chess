package org.game.records;

import org.game.Board;

public record Coord(int column, int row) {

    public static Coord of(String cellName) {

        if (cellName.length() != 2)
            throw new IllegalArgumentException("Not valid cell name: " + cellName);

        char columnSymbol = Character.toLowerCase(cellName.charAt(0));
        char rowSymbol = cellName.charAt(1);

        if (!Character.isLetter(columnSymbol) || !Character.isDigit(rowSymbol))
            throw new IllegalArgumentException("Not valid cell name: " + cellName);

        int column = columnSymbol - 'a';
        int row = rowSymbol - '1';

        Coord coord = new Coord(column, row);

        if (!Board.isValidCoord(coord))
            throw new IllegalArgumentException("Not valid coord: " + coord);

        return coord;
    }

    public Coord add(Coord coord) {
        return new Coord(this.column + coord.column, this.row + coord.row);
    }

    public Coord sub(Coord coord) {
        return new Coord(this.column - coord.column, this.row - coord.row);
    }

    public Coord divide(int k) {

        return new Coord(this.column / k, this.row / k);
    }

    public boolean equals(Coord coord) {
        return this.column == coord.column && this.row == coord.row;
    }
}
