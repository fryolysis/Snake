package primitives;

public class Cell {
	public static final int cellSize = 15;
	public static int numCellsRow;
	public static int numCellsCol;
	
	public final int x;
	public final int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Cell move(Dir input) {
		int newX = x;
		int newY = y;
		
		switch(input) {
		case LEFT:
			newX--; break;
		case RIGHT:
			newX++; break;
		case UP:
			newY--; break;
		case DOWN:
			newY++; break;
		default:
			;
		}
		// wrap the map
		newX = (newX + numCellsRow) % numCellsRow;
		newY = (newY + numCellsCol) % numCellsCol;
		return new Cell(newX, newY);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Cell) {
			Cell c = (Cell) o;
			return c.x == x && c.y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ( x << 8*(Integer.BYTES/2) ) | y;
	}
	
}
