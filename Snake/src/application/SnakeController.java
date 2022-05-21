package application;

import java.util.LinkedList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SnakeController {
	final Color foodColor = Color.BROWN;

	LinkedList<Snake> snakes;
	int numOfOpponents;
	Cell food;
	GraphicsContext g;
	boolean gameOver;
	boolean foodEaten;

	public SnakeController(GraphicsContext g, int _numOfOpponents) {
		this.g = g;
		gameOver = false;
		foodEaten = false;
		numOfOpponents = _numOfOpponents;
		snakes = new LinkedList<>();
		snakes.add(new Snake(this, false)); // player snake
		for(int i=0; i<numOfOpponents; i++) // opponents
			snakes.add(new Snake(this, true));
		spawnFood();
	}

	public void move(KeyCode input) {
		for(Snake s: snakes)
			s.move(input);	
	}

	/*
	 * Called by snakes
	 */
	void foodEaten() {
		spawnFood();
	}

	// TODO snake hits another snake control
	void hits() {

	}

	private void spawnFood() {
		Random r = new Random();
		int randX, randY;
		do {
			randY = r.nextInt(Cell.numCellsCol);
			randX = r.nextInt(Cell.numCellsRow);
		} while(!isValidFood(randX, randY));

		food = new Cell(randX, randY);
	}

	private void drawFood() {
		g.setFill(foodColor);
		g.fillRect(food.x*Cell.cellSize, food.y*Cell.cellSize, Cell.cellSize, Cell.cellSize);
	}

	private boolean isValidFood(int x, int y) {
		for(Snake s: snakes)
			for(Cell c: s.body)
				if(c.x == x && c.y == y)
					return false;
		return true;
	}

	public void drawFrame() {
		g.clearRect(0, 0, Cell.numCellsRow*Cell.cellSize, Cell.numCellsCol*Cell.cellSize);
		drawFood();
		for(Snake s: snakes)
			s.draw(g);
	}
}
