package control;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.alerts.Alerts;
import services.thread.ClientThread;

public class LoginController implements Initializable{

	@FXML
	public TextField txf_nome;
	
	@FXML
	public TextField txf_IP;
	
	@FXML
	public TextField txf_porta;
	
	@FXML
	public Button btn_conectar;
	
	public static Socket client;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txf_IP.setOnAction(e -> conect());
		txf_nome.setOnAction(e -> conect());
		txf_porta.setOnAction(e -> conect());
		btn_conectar.setOnAction(e -> conect());
	}
	
	public void conect() {
		
		if(txf_IP.getText().length() != 0 && txf_nome.getText().length() != 0 && txf_porta.getText().length() != 0) {
			
			try {
				
				System.out.println("Tentando conectar.");
				client = new Socket(txf_IP.getText(), Integer.parseInt(txf_porta.getText()));
				
				ClientThread clientThread = new ClientThread(client, txf_nome.getText());
				clientThread.start();
				
				
			} catch (NumberFormatException err1) {
				Alerts.doAlert("chat", "Digite um valor valido no campo porta.", AlertType.WARNING);
			} catch (IOException err2) {
				Alerts.doAlert("chat", "Servidor não localizado.", AlertType.ERROR);
			}
			
		}else {
			Alerts.doAlert("chat", "Digite todos as informações requeridas.", AlertType.WARNING);
		}
		
	}
	
}
