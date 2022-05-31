package application;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeManager {
	private final Color foodColor = Color.BROWN;
	private final HashSet<Snake> snakes;
	private final HashSet<Cell> occupied;
	private final int numOfOpponents;
	private final GraphicsContext g;
	private Cell food;

	public SnakeManager(GraphicsContext g, int _numOfOpponents) {
		this.g = g;
		numOfOpponents = _numOfOpponents;
		snakes = new HashSet<>();
		occupied = new HashSet<>();
		snakes.add(new Snake(this, SnakeType.PLAYER)); // player snake
		for(int i=0; i<numOfOpponents; i++) // AI snakes
			snakes.add(new Snake(this, SnakeType.AI));
		spawnFood();
	}

	void move(Dir input) {
		for(Snake s: snakes)
			s.move(input);	
	}

	void alloc(Cell c, Snake s) {
		if(c.equals(food)) {
			s.eatFood();
			spawnFood();
		}
		else if(!occupied.add(c))
			kill(s);	
	}

	void dealloc(Cell c) {
		occupied.remove(c);
	}
	
	void dealloc(Collection<Cell> cells) {
		occupied.removeAll(cells);
	}
	
	void drawFrame() {
		g.clearRect(0, 0, Cell.numCellsRow*Cell.cellSize, Cell.numCellsCol*Cell.cellSize);
		drawFood();
		for(Snake s: snakes)
			s.draw(g);
	}
	
	
	private void kill(Snake s) {
		s.die();
		snakes.remove(s);
	}
	
	private void spawnFood() {
		Random r = new Random();
		int randX, randY;
		do {
			randY = r.nextInt(Cell.numCellsCol);
			randX = r.nextInt(Cell.numCellsRow);
			food = new Cell(randX, randY);
		} while(occupied.contains(food));
	}

	private void drawFood() {
		g.setFill(foodColor);
		g.fillRect(food.x*Cell.cellSize, food.y*Cell.cellSize, Cell.cellSize, Cell.cellSize);
	}

}
