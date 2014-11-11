package name.lemerdy.sebastian.minesweeper;

import java.util.HashMap;
import java.util.Map;

import static name.lemerdy.sebastian.minesweeper.Cell.DiscoveredListener;
import static name.lemerdy.sebastian.minesweeper.Status.WIN;

public class Grid {

    private final int width;
    private final int height;
    private final Map<Coordinates, Cell> cells;

    Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells.put(new Coordinates(x, y), new Cell());
            }
        }
        for (Map.Entry<Coordinates, Cell> cell : cells.entrySet()) {
            int x = cell.getKey().x;
            int y = cell.getKey().y;
            int west = x - 1;
            int east = x + 1;
            int south = y - 1;
            int north = y + 1;
            cell.getValue()
                    .addNeibor(cellAt(west, south))
                    .addNeibor(cellAt(west, y))
                    .addNeibor(cellAt(west, north))
                    .addNeibor(cellAt(east, south))
                    .addNeibor(cellAt(east, y))
                    .addNeibor(cellAt(east, north))
                    .addNeibor(cellAt(x, south))
                    .addNeibor(cellAt(x, north));
        }
    }

    Grid mine(int x, int y) {
        cellAt(x, y).mine();
        return this;
    }

    Grid discover(int x, int y) {
        cellAt(x, y).discover();
        return this;
    }

    Grid print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 0) {
                    System.out.print(' ');
                }
                System.out.print(cellAt(x, y));
                if (x == width - 1) {
                    System.out.println();
                }
            }
        }
        return this;
    }

    private Cell cellAt(int x, int y) {
        return cells.get(new Coordinates(x, y));
    }

    public Status status() {
        return WIN;
    }

    public Grid setDiscoveredListener(int x, int y, DiscoveredListener discoveredListener) {
        cellAt(x, y).setDiscoveredListener(discoveredListener);
        return this;
    }
}
