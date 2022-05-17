package application;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Snake {
	LinkedList<Cell> body; // first element is the tail
	boolean foodEaten;
	Color color;
	
	public Snake() {
		body = new LinkedList<>();
		body.add(new Cell(0, 0));
		body.add(new Cell(0, 1));
		foodEaten = false;
		color = Color.GREEN;
	}
	
	public Cell getHead() {
		return body.getLast();
	}

	public void move(KeyCode input) {
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
	}

	public boolean eatsFood(Cell food) {
		return getHead().equals(food);
	}

	public boolean hits() {
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
