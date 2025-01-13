package org.game.iterators;
import java.util.Iterator;
import org.game.records.Coord;

public class boardIterator implements Iterator<Coord> {

    private final int cells = 64;
    private final int rows = 8;
    private int cell = 0;

    @Override
    public boolean hasNext() {
        return cell < cells;
    }

    @Override
    public Coord next() {
        return cellToCoord(cell++);
    }

    private Coord cellToCoord(int cell) {
        return new Coord(cell/rows, cell%rows);
    }
}
