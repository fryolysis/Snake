package application;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake {
	
	private Cell head;
	private boolean foodEaten;
	private final Queue<Cell> body; // first element is the tail
	private final SnakeController controller;
	private final boolean isOpponent;
	
	public Snake(SnakeController _controller, boolean _isOpponent) {
		body = new LinkedList<>();
		isOpponent = _isOpponent;
		controller = _controller;
		head = new Cell(0, 0);
		addCell(head);
		foodEaten = false;
	}
	
	
	private void addCell(Cell c) {
		body.add(c);
		controller.alloc(c, this);
	}
	
	private void removeCell() {
		controller.dealloc(body.remove());
	}

	
	void die() {
		controller.dealloc(body);
	}
	
	void eatFood() {
		foodEaten = true;
	}
	
	void move(Dir input) {
		if(isOpponent)
			input = calculateDir();
		
		if(foodEaten)
			foodEaten = false;
		else
			removeCell();
		
		head = head.move(input);
		addCell(head);
		
	}
	
	/**
	 * strategy to reach food
	 * very naive logic
	 * TODO migrate this method to controller
	 */
	private Dir calculateDir() {
		return Dir.DOWN;
	}


	public void draw(GraphicsContext g) {
		if(isOpponent)
			g.setFill(Color.CHOCOLATE);
		else
			g.setFill(Color.GREENYELLOW);
		for(Cell c: body) {
			g.fillRect(c.x*Cell.cellSize, c.y*Cell.cellSize, Cell.cellSize, Cell.cellSize);
		}
			
	}
}
