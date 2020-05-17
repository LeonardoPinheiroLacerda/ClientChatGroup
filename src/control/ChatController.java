package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import services.dinamicView.Updates;
import services.thread.ClientThread;

public class ChatController implements Initializable{

	@FXML
	public VBox mensagens;
	
	@FXML
	public Label lbl_nome;
	
	@FXML
	public TextField txf_mensagem;
	
	@FXML
	public Button btn_enviar;
	
	@FXML
	public ScrollPane scroll;
	
	public static VBox sMensagens; 
	public static TextField sTxf_mensagem;
	public static Button sBtn_enviar;
	public static ScrollPane sScroll;
	
	public static List<VBox> vBoxList = new ArrayList<>();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		sMensagens = mensagens;
		sTxf_mensagem = txf_mensagem;
		sBtn_enviar = btn_enviar;
		sScroll = scroll;
		
		lbl_nome.setText(ClientThread.getNome());
		
		btn_enviar.setOnAction(e -> {
			send();
		});
		
		txf_mensagem.setOnAction(e->{
			send();
		});
		
	}
	
	public void send() {
		if(txf_mensagem.getText().length() != 0) {
			ClientThread.out.println("SEND:" + lbl_nome.getText() + ":" + txf_mensagem.getText());
			Updates.send(txf_mensagem.getText());
			txf_mensagem.setText("");
			
			scrollDown();
		}
	}
	
	public static void scrollDown() {
		Thread t = new Thread() {
			public void run() {
				try {
					sleep(50);
				} catch (InterruptedException e) {
				}
				Platform.runLater(() -> ChatController.sScroll.setVvalue(ChatController.sScroll.getVmax()));
			}
		};
		t.start();
		t = null;
	}
	
	
	
}
