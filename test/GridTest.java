import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void add_remove_hasCell() {
        Grid grid = new Grid();

        assertFalse(grid.hasCell(2, 3), "empty grid has no cell 2,3");

        grid.addCell(new Cell(2, 3));
        assertTrue(grid.hasCell(2, 3), "get cell that was added before");

        grid.removeCell(new Cell(2, 3));
        assertFalse(grid.hasCell(2, 3), "cell 2,3 was removed");
    }


    @Test
    void numNeighbors() {
        final Grid grid = new Grid();
        final Cell cell_1_1 = new Cell(1, 1);

        grid.addCell(cell_1_1);
        assertEquals(0, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(0, 0));
        assertEquals(1, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(0, 1));
        assertEquals(2, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(0, 2));
        assertEquals(3, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(1, 0));
        assertEquals(4, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(1, 2));
        assertEquals(5, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(2, 0));
        assertEquals(6, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(2, 1));
        assertEquals(7, grid.numNeighbors(cell_1_1));

        grid.addCell(new Cell(2, 2));
        assertEquals(8, grid.numNeighbors(cell_1_1));

        final Cell cell_0_1 = new Cell(0, 1);
        assertEquals(5, grid.numNeighbors(cell_0_1));

        final Cell cell_2_2 = new Cell(2, 2);
        assertEquals(3, grid.numNeighbors(cell_2_2));
    }

}