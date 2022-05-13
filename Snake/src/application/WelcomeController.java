package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WelcomeController implements Initializable {
	@FXML
	CheckBox snakeToFoodCheckBox;
	@FXML
	Slider speedSlider, cellSizeSlider;
	@FXML
	ColorPicker snakeColorPicker, foodColorPicker;
	
	GameController game;
	
	

	public void startGame() {
		Cell.cellSize = (int) cellSizeSlider.getValue();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
		try {
			Parent root = (Parent)loader.load();
			game = loader.getController();
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setOnCloseRequest(e -> {
				game.controller.gameOver = true;
			});
			stage.show();
			root.requestFocus();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		game.controller.snake.color = snakeColorPicker.getValue();
		game.controller.foodColor = foodColorPicker.getValue();
		game.fps = speedSlider.getValue();
		
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		snakeColorPicker.setValue(Color.BLACK);
		foodColorPicker.setValue(Color.BLUE);
	}
}
