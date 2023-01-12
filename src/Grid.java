import java.util.ArrayList;
import java.util.Comparator;
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

    public void removeCell(Cell cell) {
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
        final List<Cell> deadCells = new ArrayList<>();
        final List<Cell> newCells = new ArrayList<>();

        for (int i = cells.size() - 1; i >= 0; i--) {
            final Cell cell = cells.get(i);
            final int numberOfNeighbors = numNeighbors(cell);

            if (numberOfNeighbors < 2 || numberOfNeighbors > 3) {
                deadCells.add(cell);
                // cells.remove(i);
            }
        }

        for (Cell neighborCell : neighborCells) {
            final int numberOfNeighbors = neighborCell.getNumberOfNeighbors();

            if (numberOfNeighbors == 3 && !cells.contains(neighborCell)) {
                newCells.add(neighborCell);
                // cells.add(neighborCell);
            }
        }

        for (Cell c : deadCells) {
            removeCell(c);
        }

        for (Cell c : newCells) {
            addCell(c);
        }
    }

    public void run(int iterations) {
        while (iterations > 0 && cells.size() > 0) {
            print();
            applyRules();

            System.out.println();
            iterations--;
        }
    }

    private void print() {
        cells.sort(Comparator.comparingInt(Cell::hashCode));

        final Cell minCell = calculateMin(cells.get(0));
        int posX = minCell.getX();
        int posY = minCell.getY();

        System.out.println();
        System.out.print("[" + posX + "," + posY + "]");

        for (Cell cell : cells) {
            while (posY <= cell.getY()) {
                System.out.println();
                System.out.print(posY + ": ");
                posY++;
                posX = minCell.getX();
            }

            posX = printRowSpaceBetweenCurrentAndCellPosition(posX, cell.getX(), cell.getY());
            System.out.print("*");
            posX++;
        }
    }

    private int printRowSpaceBetweenCurrentAndCellPosition(int posX, int x, int y) {
        for (; x > posX; posX++) {
//            final Cell posCell = new Cell(posX, y);
//            final int neighborCount = numNeighbors(posCell);
//            System.out.print(neighborCount == 0 ? " " : neighborCount);
            System.out.print(" ");
        }

        return posX;
    }

    private Cell calculateMin(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        for (Cell c : cells) {
            if (x > c.getX()) {
                x = c.getX();
            }

            if (y > c.getY()) {
                y = c.getY();
            }
        }

        return new Cell(x, y);
    }
}
