package services.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts{
	
	public static void doAlert(String title, String text, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(text);
		alert.setHeaderText(null);
		alert.show();
	}

	public static void doAlert(String title, String text) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(text);
		alert.setHeaderText(null);
		alert.show();
	}
	
}
