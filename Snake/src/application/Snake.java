package application;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


enum SnakeState {
	ROAMING,
	FOOD_EATEN,
	DEAD
}

enum SnakeType {
	PLAYER,
	AI
}

public class Snake {
	
	private Cell head;
	private SnakeState state;
	private final SnakeType type;
	private final Queue<Cell> body; // first element is the tail
	private final SnakeManager manager;
	
	public Snake(SnakeManager _manager, SnakeType _type) {
		body = new LinkedList<>();
		type = _type;
		manager = _manager;
		head = manager.allocRandom();
		addCell(head);
		state = SnakeState.ROAMING;
	}
	
	
	private void addCell(Cell c) {
		body.add(c);
		manager.alloc(c, this);
	}
	
	private void removeCell() {
		manager.dealloc(body.remove());
	}

	
	void die() {
		manager.dealloc(body);
	}
	
	void eatFood() {
		state = SnakeState.FOOD_EATEN;
	}
	
	void move(Dir input) {
		if(type == SnakeType.AI)
			input = calculateDir();
		
		switch(state) {
		case ROAMING:
			removeCell();
			break;
		case FOOD_EATEN:
			state = SnakeState.ROAMING;
			break;
		default:
		}
		
		head = head.move(input);
		addCell(head);
		
	}
	
	/**
	 * strategy to reach food
	 * very naive logic
	 * TODO migrate this method to manager
	 */
	private Dir calculateDir() {
		return Dir.DOWN;
	}


	public void draw(GraphicsContext g) {
		switch(type) {
		case PLAYER:
			g.setFill(Color.GREENYELLOW);
			break;
		case AI:
			g.setFill(Color.CHOCOLATE);
			break;
		}
		for(Cell c: body) {
			g.fillRect(c.x*Cell.cellSize, c.y*Cell.cellSize, Cell.cellSize, Cell.cellSize);
		}
			
	}
}
