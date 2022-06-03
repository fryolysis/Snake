package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import primitives.Cell;
import primitives.CellType;

/**
 * Responsible for keeping track of the terrain
 * @author fryolysis
 */
public class Geographer {
	private Cell food;
	private final GraphicsContext g;
	private final HashMap<Cell, CellType> map;
	private final HashSet<Cell> freeCells;
	private final Random r;
	
	Geographer(GraphicsContext _g){
		g = _g;
		r = new Random();
		map = new HashMap<>();
		freeCells = new HashSet<>();
		
		for(int i=0; i<Cell.numCellsRow; i++)
		for(int j=0; j<Cell.numCellsCol; j++)
			setCellType(new Cell(i, j), CellType.FREE); 
		
		spawnFood();
			
	}
	
	void draw() {
		for(Cell c: map.keySet()) {
			g.setFill(map.get(c).getColor());
			g.fillRect(c.x * Cell.cellSize, 
					c.y * Cell.cellSize, 
					Cell.cellSize, 
					Cell.cellSize);
		}
	}

	
	void spawnFood() {
		food = getFreeCell();
		setCellType(food, CellType.FOOD);
	}

	CellType getCellType(Cell c) {
		return map.get(c);
	}
	
	void setCellType(Cell c, CellType t) {
		if(t == getCellType(c)) 
			return;
		else if(getCellType(c) == CellType.FREE)
			freeCells.remove(c);
		else if(t == CellType.FREE)
			freeCells.add(c);
		
		map.put(c, t);
	}
	
	Cell getFreeCell() {
		int n = r.nextInt(freeCells.size());
		int i = 0;
		for(Cell c: freeCells) {
			if(i == n)
				return c;
			i++;
		}
		return null;
	}
}
