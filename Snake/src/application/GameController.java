package application;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;


enum GameState {
	WAITING,
	GOT_INPUT,
	PAUSE,
	OVER
}

public class GameController {
	@FXML
	Canvas canvas;
	
	GraphicsContext g;
	SnakeManager controller;
	AnimationTimer timer;
	Dir input;
	Dir prevInput;
	private GameState state;
	double fps;
	double prevTime;
	int numOfOpponents;
	
	
	public void handleUserInput(KeyEvent e) {
		switch(e.getCode()) {
		case LEFT: case RIGHT: case UP: case DOWN:
			Dir dir = Dir.fromKeyCode(e.getCode());
			if(state == GameState.WAITING &&  dir.getOpposite() != prevInput) {
				state = GameState.GOT_INPUT;
				input = dir;
			}
			break;
		case SPACE:
			switch(state) {
			case WAITING:
				timer.stop();
				state = GameState.PAUSE;
				break;
			case PAUSE:
				timer.start();
				state = GameState.WAITING;
				break;
			default:
			}
			break;
		default:
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
		controller = new SnakeManager(g, numOfOpponents);
		prevTime = 0;
		input = Dir.DOWN;
		prevInput = null;
		state = GameState.WAITING;
		
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(now - prevTime > 1e9/fps) {
					prevTime = now;
					controller.move(input);
					controller.drawFrame();
					state = GameState.WAITING;
					prevInput = input;
				}
			}
		};
		
		timer.start();
	}
	
	
}
