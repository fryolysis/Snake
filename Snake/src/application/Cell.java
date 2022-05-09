package application;

public class Cell {
	static int cellSize;
	int x;
	int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Cell move(Dir dir) {
		Cell res = new Cell(this.x, this.y);
		
		switch(dir) {
		case LEFT:
			res.x--; break;
		case RIGHT:
			res.x++; break;
		case UP:
			res.y--; break;
		case DOWN:
			res.y++; break;
		}
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
