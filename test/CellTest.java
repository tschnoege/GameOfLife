import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CellTest {
    @Test
    void testEquals() {
        Cell cell23 = new Cell(2, 3);
        Cell cell11 = new Cell(1, 1);

        assertEquals(cell23, cell23, "cell equals itself");
        assertNotEquals(cell23, cell11, "cells at different positions are not equal");
    }
}