package application;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;


enum GameState {
	PLAY,
	PAUSE,
	OVER;
}

public class GameController {
	@FXML
	Canvas canvas;
	
	GraphicsContext g;
	SnakeController controller;
	AnimationTimer timer;
	KeyCode input;
	KeyCode prevInput;
	boolean gotInput;
	private GameState state;
	double fps;
	double prevTime;
	int numOfOpponents;
	
	
	public void handleUserInput(KeyEvent e) {
		switch(e.getCode()) {
		case LEFT: case RIGHT: case UP: case DOWN:
			if(!gotInput && !isOppositeDir(e.getCode(), prevInput)) {
				gotInput = true;
				input = e.getCode();
			}
			break;
		case SPACE:
			switch(state) {
			case PLAY:
				timer.stop();
				state = GameState.PAUSE;
				break;
			case PAUSE:
				timer.start();
				state = GameState.PLAY;
				break;
			default:
			}
			break;
		default:
		}
	}
	
	
	private boolean isOppositeDir(KeyCode dir1, KeyCode dir2) {
		switch(dir1) {
		case LEFT:
			return dir2 == KeyCode.RIGHT;
		case RIGHT:
			return dir2 == KeyCode.LEFT;
		case UP:
			return dir2 == KeyCode.DOWN;
		case DOWN:
			return dir2 == KeyCode.UP;
		default:
			return false;
		}
	}

	void gameOver() {
		state = GameState.OVER;
		timer.stop();
	}
	
	
	public void init() {
		g = canvas.getGraphicsContext2D();
		Cell.numCellsCol = (int)canvas.getHeight()/Cell.cellSize;
		Cell.numCellsRow = (int)canvas.getWidth()/Cell.cellSize;
		controller = new SnakeController(g, numOfOpponents);
		prevTime = 0;
		input = KeyCode.DOWN;
		prevInput = null;
		gotInput = false;
		state = GameState.PLAY;
		
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(now - prevTime > 1e9/fps) {
					prevTime = now;
					controller.move(input);
					controller.drawFrame();
					gotInput = false;
					prevInput = input;
				}
			}
		};
		
		timer.start();
	}
	
	
}
