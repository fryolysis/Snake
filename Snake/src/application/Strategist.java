package application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import primitives.Cell;
import primitives.Dir;

/**
 * Responsible for AI logic
 * @author fryolysis
 *
 */
public class Strategist {
	final Geographer geo;
	final HashMap<Snake, Dir> dirs;
	
	
	
	Strategist(HashSet<Snake> snakes, Geographer _geo){
		geo = _geo;
		dirs = new HashMap<>();
		for(Snake s: snakes)
			dirs.put(s, null);
	}


	void snakeDead(Snake snake) {
		dirs.remove(snake);
	}

	void move(Dir input) {
		developStrategy(input);
		for(Snake s: dirs.keySet()) {
			switch(s.getType()) {
			case PLAYER:
				s.move(input);
				break;
			case AI:
				s.move(dirs.get(s));
				break;
			}
		}
	}
	
	/**
	 * The main part to improve
	 * TODO 
	 * 	locate the closest snake to food and make it trace the food
	 * 	other snakes should move with some randomness and they should
	 * 	avoid hitting any other snake.
	 */
	private void developStrategy(Dir input) {
		// TODO: replace dummy logic
		for(Snake s: dirs.keySet())
			switch(s.getType()) {
			case PLAYER:
				dirs.put(s, input);
				break;
			case AI:
				dirs.put(s, input.getOpposite());
				break;
			}			
	}
	
	
	
	// utility functions
	
	private static void randomDirChange(Dir oldDir, double prob) {
		
	}
	
	
	private static int manhattanDistance(Cell c1, Cell c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}
}
