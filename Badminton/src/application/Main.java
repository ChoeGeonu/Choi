package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
<<<<<<< HEAD
			Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
=======
			Parent root = FXMLLoader.load(getClass().getResource("/View/loginView.fxml"));
>>>>>>> d4d620748565a371115d39eafe0ed7909a97bec2
			Scene scene = new Scene(root);

			primaryStage.setTitle("배드민턴");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
