package application;

import java.util.LinkedList;
import java.util.Queue;

import primitives.Cell;
import primitives.CellType;
import primitives.Dir;
import primitives.SnakeType;




public class Snake {
	
	private Cell head;
	private final SnakeType type;
	private final Queue<Cell> body; // first element is the tail
	private Geographer geo;
	private Strategist strat;
	
	public Snake(SnakeType t) {
		body = new LinkedList<>();
		type = t;
	}
	
	
	SnakeType getType() {
		return type;
	}
	
	void setGeographer(Geographer g) {
		geo = g;
		head = g.getFreeCell();
		addHead();
	}
	
	void setStrategist(Strategist s) {
		strat = s;
	}
	
	private void addHead() {
		geo.setCellType(head, CellType.SNAKE);
		body.add(head);
	}
	
	private void removeTail() {
		geo.setCellType(body.remove(), CellType.FREE);
	}
	
	private void die() {
		while(!body.isEmpty())
			removeTail();
	}
	
	
	void move(Dir dir) {
		head = head.move(dir);
		switch(geo.getCellType(head)) {
		case SNAKE:
			die();
			strat.snakeDead(this);
			break;
		case FREE:
			removeTail();
			addHead();
			break;
		case FOOD:
			addHead();
			geo.spawnFood();
		}
		
	}


}
