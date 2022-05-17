package application;

import java.util.LinkedList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SnakeController {
	Snake snake;
	Cell food;
	Color foodColor;
	int width, height;
	GraphicsContext g;
	boolean gameOver;
	
	public SnakeController(GraphicsContext g, int width, int height) {
		this.height = height;
		this.width = width;
		this.g = g;
		gameOver = false;
		snake = new Snake();
		foodColor = Color.BLUE;
		spawnFood();
	}
	
	public void move(KeyCode input) {
		snake.move(input);
		if(hits())
			gameOver = true;
		if(eatsFood()) {
			snake.foodEaten = true;
			spawnFood();
		}
	}
	
	private boolean eatsFood() {
		return snake.eatsFood(food);
	}
	
	private boolean hits() {
		return snake.hits();
	}
	
	private void spawnFood() {
		Random r = new Random();
		int randX, randY;
		do {
			randY = r.nextInt(height);
			randX = r.nextInt(width);
		} while(!isValidFood(randX, randY));

		food = new Cell(randX, randY);
	}
	
	private void drawFood() {
		g.setFill(foodColor);
		g.fillRect(food.x*Cell.cellSize, food.y*Cell.cellSize, Cell.cellSize, Cell.cellSize);
	}
	
	private boolean isValidFood(int x, int y) {
		LinkedList<Cell> body = snake.body;
		for(Cell c: body)
			if(c.x == x && c.y == y)
				return false;
		return true;
	}
	
	public void drawFrame() {
		g.clearRect(0, 0, width*Cell.cellSize, height*Cell.cellSize);
		drawFood();
		snake.draw(g);
	}
}
