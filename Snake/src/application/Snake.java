package application;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Snake {
	final Color color = Color.GREEN;
	
	LinkedList<Cell> body; // first element is the tail
	boolean foodEaten;
	SnakeController controller;
	boolean isOpponent;
	
	public Snake(SnakeController _controller, boolean _isOpponent) {
		body = new LinkedList<>();
		body.add(new Cell(0, 0));
		body.add(new Cell(0, 1));
		foodEaten = false;
		isOpponent = _isOpponent;
		controller = _controller;
	}
	
	private Cell getHead() {
		return body.getLast();
	}

	public void move(KeyCode input) {
		if(isOpponent)
			input = calculateDir();
		
		int beginIndex = 0;
		if(foodEaten) {
			body.addFirst(body.getFirst());
			beginIndex = 1;
			foodEaten = false;
		}
			
		for(int i=beginIndex; i<body.size()-1; i++) {
			body.set(i, body.get(i+1));
		}

		body.set(body.size()-1, getHead().move(input));
		
		
		if(ateFood()) {
			foodEaten = true;
			controller.foodEaten();
		}
		
		if(hitMyself()) {
			controller.snakes.remove(this);
			if(!isOpponent)
				controller.gameOver = true;
		}
	}
	
	/**
	 * strategy to reach food
	 * very naive logic
	 */
	private KeyCode calculateDir() {
		Cell head = getHead();
		Cell food = controller.food;
		if(food.x == head.x)
			return KeyCode.UP;
		else if(food.y == head.y)
			return KeyCode.RIGHT;
		else
			return KeyCode.DOWN;
	}

	private boolean ateFood() {
		return getHead().equals(controller.food);
	}

	private boolean hitMyself() {
		Cell head = getHead();
		for(int i=0; i<body.size()-1; i++)
			if(head.equals(body.get(i)))
				return true;
		return false;
	}

	public void draw(GraphicsContext g) {
		g.setFill(color);
		for(Cell c: body) {
			g.fillRect(c.x*Cell.cellSize, c.y*Cell.cellSize, Cell.cellSize, Cell.cellSize);
		}
			
	}
}
