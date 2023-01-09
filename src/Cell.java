import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;

    private int numNeighbors;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        numNeighbors = 0;
    }

    public Cell(Cell gridCell) {
        this.x = gridCell.x;
        this.y = gridCell.y;
        this.numNeighbors = gridCell.numNeighbors;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getNumberOfNeighbors() {
        return numNeighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void increaseNeighbors() {
        numNeighbors++;
    }

    public void decreaseNeighbors() {
        numNeighbors--;
    }
}
