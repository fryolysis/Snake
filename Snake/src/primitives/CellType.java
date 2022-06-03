package primitives;

import javafx.scene.paint.Color;

public enum CellType {
	FREE,
	SNAKE,
	FOOD;
	
	public Color getColor() {
		switch(this) {
		case FREE:
			return Color.WHITE;
		case SNAKE:
			return Color.GREEN;
		case FOOD:
			return Color.BROWN;
		default:
			return null;
		}
	}
}