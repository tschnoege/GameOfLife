import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RulesTest {
    @Test
    public void livingCellWithLessThan2Neighbors_dies() {
        final Grid grid = new Grid();

        grid.addCell(new Cell(0, 0));
        grid.applyRules();
        assertFalse(grid.hasCell(0, 0), "cell with 0 neighbours dies");

        grid.addCell(new Cell(0, 0));
        grid.addCell(new Cell(0, 1));
        grid.applyRules();
        assertFalse(grid.hasCell(0, 0), "cell with 1 neighbours dies");
    }

    @Test
    public void livingCellWith2or3Neighbors_survives() {
        final Grid grid = new Grid();

        grid.addCell(new Cell(0, 0));
        grid.addCell(new Cell(0, 1));
        grid.addCell(new Cell(1, 0));
        grid.applyRules();
        assertTrue(grid.hasCell(0, 0), "cell with 2 neighbours stays alive");

        grid.addCell(new Cell(1, 1));
        grid.applyRules();
        assertTrue(grid.hasCell(0, 0), "cell with 3 neighbours stays alive");
    }

    @Test
    public void livingCellWithMoreThan3Neighbors_dies() {
        final Grid grid = new Grid();

        grid.addCell(new Cell(0, 0));
        grid.addCell(new Cell(0, 1));
        grid.addCell(new Cell(1, 0));
        grid.addCell(new Cell(1, 1));
        grid.addCell(new Cell(-1, 0));
        grid.applyRules();
        assertFalse(grid.hasCell(0, 0), "cell with more than 3 neighbours dies");
    }

    @Test
    public void deadCellWithExactly3Neighbors_becomesAlive() {
        final Grid grid = new Grid();

        // grid.addCell(new Cell(0,0));
        grid.addCell(new Cell(0, 1));
        grid.addCell(new Cell(1, 0));
        grid.addCell(new Cell(1, 1));
        grid.applyRules();
        assertTrue(grid.hasCell(0, 0), "cell with exactly 3 neighbours becomes Alive");
    }
}
