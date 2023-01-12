public class gameOfLife {
    public static void main(String[] args) {
        final Grid grid = new Grid();

        grid.addCell(new Cell(1, 1));
        grid.addCell(new Cell(3, 1));
        //grid.addCell(new Cell(2, 2));
        grid.addCell(new Cell(3, 2));
        grid.addCell(new Cell(2, 3));
        grid.addCell(new Cell(2, 4));

        grid.run(5);
    }
}
