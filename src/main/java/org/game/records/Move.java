package org.game.records;

public record Move(Coord from, Coord to) {

    public static Move of(String from, String to) {
        return new Move(Coord.of(from), Coord.of(to));
    }

    public Coord getDelta() {
        return to.sub(from);
    }

    public int distance() {
        Coord delta = getDelta();

        int column = Math.abs(delta.column());
        int row = Math.abs(delta.row());

        return Math.max(column, row);
    }

    public Coord getStep() {

        Coord delta = this.getDelta();
        int multiplicity = this.distance();

        return delta.divide(multiplicity);
    }

    public boolean isDiagonalMove() {
        Coord delta = getDelta();
        return Math.abs(delta.column()) == Math.abs(delta.row());
    }

    public boolean isCrossMove() {
        Coord delta = getDelta();
        return (delta.column() == 0 && delta.row() != 0) || (delta.row() == 0 && delta.column() != 0);
    }

}
