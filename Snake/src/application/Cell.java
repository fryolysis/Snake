package application;

import javafx.scene.input.KeyCode;

public class Cell {
	static final int cellSize = 15;
	static int numCellsRow;
	static int numCellsCol;
	
	int x;
	int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Cell move(KeyCode input) {
		Cell res = new Cell(this.x, this.y);
		
		switch(input) {
		case LEFT:
			res.x--; break;
		case RIGHT:
			res.x++; break;
		case UP:
			res.y--; break;
		case DOWN:
			res.y++; break;
		default:
			;
		}
		// wrap the map
		res.x = (res.x + numCellsRow) % numCellsRow;
		res.y = (res.y + numCellsCol) % numCellsCol;
		return res;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Cell) {
			Cell c = (Cell)o;
			if(c.x == this.x && c.y == this.y)
				return true;
		}
		return false;
	}
	
}
