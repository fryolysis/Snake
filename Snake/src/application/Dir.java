package application;

import javafx.scene.input.KeyCode;

enum Dir {
	LEFT,
	RIGHT,
	UP,
	DOWN;

	public Dir getOpposite() {
		switch(this) {
		case LEFT:
			return Dir.RIGHT;
		case RIGHT:
			return Dir.LEFT;
		case UP:
			return Dir.DOWN;
		case DOWN:
			return Dir.UP;
		}
		return null;
	}
	
	public static Dir fromKeyCode(KeyCode key) {
		switch(key) {
		case LEFT:
			return Dir.LEFT;
		case RIGHT:
			return Dir.RIGHT;
		case UP:
			return Dir.UP;
		case DOWN:
			return Dir.DOWN;
		default:
			return null;
		}
	}
}
