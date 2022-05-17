package application;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;




public class GameController {
	@FXML
	Canvas canvas;
	
	GraphicsContext g;
	SnakeController controller;
	AnimationTimer timer;
	KeyCode input;
	KeyCode prevInput;
	boolean gotInput;
	boolean paused;
	double fps;
	double prevTime;
	
	
	public void handleUserInput(KeyEvent e) {
		switch(e.getCode()) {
		case LEFT: case RIGHT: case UP: case DOWN:
			if(!gotInput && !isOppositeDir(e.getCode(), prevInput)) {
				gotInput = true;
				input = e.getCode();
			}
			break;
		case SPACE:
			if(paused && !controller.gameOver)
				timer.start();
			else
				timer.stop();
			paused = !paused;
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

	public void gameOver() {
		g.setFont(Font.font(20));
		g.strokeText("GAME OVER", canvas.getWidth()/2, 
				canvas.getWidth()/2);		
	}
	
	
	public void myInitialize() {
		g = canvas.getGraphicsContext2D();
		controller = new SnakeController(g,
				(int)canvas.getWidth()/Cell.cellSize, 
				(int)canvas.getHeight()/Cell.cellSize);
		prevTime = 0;
		input = KeyCode.DOWN;
		prevInput = null;
		gotInput = false;
		
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(now - prevTime > 1000000000L/fps) {
					prevTime = now;
					controller.move(input);
					controller.drawFrame();
					gotInput = false;
					prevInput = input;
					if(controller.gameOver) {
						timer.stop();
						gameOver();
					}
				}
			}
		};
		
		timer.start();
	}
	
	
}
