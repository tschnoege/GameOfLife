import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Grid {
    private final List<Cell> neighborCells = new ArrayList<>();
    private final List<Cell> cells = new ArrayList<>();

    public Grid() {
    }

    public boolean hasCell(int x, int y) {
        return cells.contains(new Cell(x, y));
    }

    public int numNeighbors(Cell cell) {
        final int index = neighborCells.indexOf(cell);

        return (index == -1) ? 0 : neighborCells.get(index).getNumberOfNeighbors();
    }

    public void addCell(Cell cell) {
        assert (cell != null);

        if (!cells.contains(cell)) {
            cells.add(new Cell(cell));
            addNeighbor(cell);
        }
    }

    public void remove(Cell cell) {
        assert (cell != null);
        cells.remove(cell);
        removeNeighbor(cell);
    }

    private void addNeighbor(Cell newNeighbor) {
        final int x = newNeighbor.getX();
        final int y = newNeighbor.getY();

        for (int ny = y - 1; ny <= y + 1; ny++) {
            for (int nx = x - 1; nx <= x + 1; nx++) {
                if (ny != y || nx != x) {
                    increaseNeighborCount(nx, ny);
                }
            }
        }
    }

    private void removeNeighbor(Cell neighbor) {
        final int x = neighbor.getX();
        final int y = neighbor.getY();

        for (int ny = y - 1; ny <= y + 1; ny++) {
            for (int nx = x - 1; nx <= x + 1; nx++) {
                if (ny != y || nx != x) {
                    decreaseNeighborCount(nx, ny);
                }
            }
        }
    }

    private void increaseNeighborCount(int x, int y) {
        Cell cell;
        int index = neighborCells.indexOf(new Cell(x, y));

        if (index != -1) {
            cell = neighborCells.get(index);
        } else {
            cell = new Cell(x, y);
            neighborCells.add(cell);
        }

        cell.increaseNeighbors();
    }

    private void decreaseNeighborCount(int x, int y) {
        Cell cell;
        int index = neighborCells.indexOf(new Cell(x, y));

        if (index != -1) {
            cell = neighborCells.get(index);
            cell.decreaseNeighbors();
        } else {
            throw new NoSuchElementException("no neighbor found at [" + x + ", " + y + "]");
        }

    }

    public void applyRules() {
        final List<Integer> deadCellsIndexList = new ArrayList<>();

        for (int i = cells.size() - 1; i >= 0; i--) {
            final Cell cell = cells.get(i);
            final int numNeighbors = numNeighbors(cell);

            if (numNeighbors < 2 || numNeighbors > 3) {
                deadCellsIndexList.add(i);
            }
        }

        for (int i : deadCellsIndexList) {
            cells.remove(i);
        }

        for (Cell cell : neighborCells) {
            if (cell.getNumberOfNeighbors() == 3) {
                cells.add(cell);
            }
        }
    }
}
