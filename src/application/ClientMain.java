package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClientMain extends Application {
	
	public final String VIEW_PATH = "/view/Login.fxml";
	public static Stage loginScreen;
	
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource(VIEW_PATH));
			
			primaryStage.setTitle("chat");
			primaryStage.setScene(new Scene(parent));
			
			primaryStage.show();
			loginScreen = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
