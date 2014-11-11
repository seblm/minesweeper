package name.lemerdy.sebastian.minesweeper;

import java.util.HashSet;
import java.util.Set;

import static name.lemerdy.sebastian.minesweeper.CellType.EMPTY;
import static name.lemerdy.sebastian.minesweeper.CellType.MINE;

class Cell {
    private final Set<Cell> neibors;

    private boolean discovered;
    private CellType type;
    private DiscoveredListener discoveredListener;

    Cell() {
        this.neibors = new HashSet<>();
        this.discovered = false;
        this.type = EMPTY;
        this.discoveredListener = NO_DISCOVERED_LISTENER;
    }

    Cell addNeibor(Cell cell) {
        if (cell == null) {
            return this;
        }
        neibors.add(cell);
        return this;
    }

    Cell mine() {
        if (type == MINE) {
            return this;
        }
        type = MINE;
        neibors.stream()
                .filter(cell -> cell.type != MINE)
                .forEach(cell -> cell.type = cell.type.increment());
        return this;
    }

    @Override
    public String toString() {
        return discovered ? type.toString() : "#";
    }

    void discover() {
        discovered = true;
        discoveredListener.onDiscovered(this);
        if (type == EMPTY) {
            neibors.stream()
                    .filter(cell -> !cell.discovered)
                    .forEach(Cell::discover);
        }
    }

    public void setDiscoveredListener(DiscoveredListener discoveredListener) {
        this.discoveredListener = discoveredListener;
    }

    @FunctionalInterface
    public interface DiscoveredListener {
        public void onDiscovered(Cell cell);
    }

    public static final DiscoveredListener NO_DISCOVERED_LISTENER = cell -> {
    };
}
