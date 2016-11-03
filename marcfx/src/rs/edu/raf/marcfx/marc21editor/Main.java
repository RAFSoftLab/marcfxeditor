package rs.edu.raf.marcfx.marc21editor;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
	public static Form form;

	@Override
	public void start(Stage primaryStage) throws Exception {
		form = Form.getInstance();
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
