package application;

import java.util.HashSet;

import javafx.scene.canvas.GraphicsContext;
import primitives.Dir;
import primitives.SnakeType;




/**
 * Responsible for initialization
 * @author fryolysis
 */
public class Manager {
	final Geographer geo;
	final Strategist strat;


	public Manager(GraphicsContext g, int numSnakes) {
		// create snakes
		HashSet<Snake> snakes = new HashSet<>();
		snakes.add(new Snake(SnakeType.PLAYER));
		for(int i=0; i<numSnakes - 1; i++)
			snakes.add(new Snake(SnakeType.AI));

		// create staff
		geo = new Geographer(g);
		strat = new Strategist(snakes, geo);

		for(Snake s: snakes) {
			s.setStrategist(strat);
			s.setGeographer(geo);
		}
	}

	public void singleStep(Dir input) {
		strat.move(input);
		geo.draw();
	}


}
