package services.stage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.thread.ClientThread;

public class CreateStage {

	public Stage create(String path) {
		
		Stage stage = new Stage();
		
		Parent parent;
		try {
			
			parent = FXMLLoader.load(getClass().getResource(path));
			
			stage.setTitle("chat");
			stage.setScene(new Scene(parent));
			
			stage.show();
			
			stage.setOnCloseRequest(e->{
				try {
					ClientThread.getClient().close();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			});
			
		} catch (IOException e) {
			
		}
		
		return stage;
		
	}
	
}
