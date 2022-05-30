package application;

import javafx.scene.input.KeyCode;

public class Cell {
	static final int cellSize = 15;
	static int numCellsRow;
	static int numCellsCol;
	
	final int x;
	final int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Cell move(KeyCode input) {
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
