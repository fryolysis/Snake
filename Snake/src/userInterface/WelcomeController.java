package userInterface;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WelcomeController {

	@FXML
	Slider difficultySlider, opponentSlider;

	GameController game;


	public void startGame() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));

		try {
			Parent root = (Parent)loader.load();
			game = loader.getController();

			Rectangle2D screen = Screen.getPrimary().getBounds();
			game.canvas.setWidth(screen.getWidth());
			game.canvas.setHeight(screen.getHeight());
			game.fps = 5 * difficultySlider.getValue();
			game.numSnakes = (int) opponentSlider.getValue() + 1;
			game.init();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setOnCloseRequest(e -> {
				game.gameOver();
			});
			stage.setFullScreen(true);
			stage.setResizable(false);
			stage.show();
			root.requestFocus();

		} catch (IOException e1) {
			e1.printStackTrace();
		}


	}
}
