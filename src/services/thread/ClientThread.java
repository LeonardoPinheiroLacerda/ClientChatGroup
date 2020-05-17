package services.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import application.ClientMain;
import control.ChatController;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import services.alerts.Alerts;
import services.dinamicView.Updates;
import services.stage.CreateStage;

public class ClientThread extends Thread{

	private static Socket client;
	private static String nome;
	
	private BufferedReader in;
	public static PrintWriter out;
	
	public void run() {
		instanceStreams();
		
		out.println(nome);
		
		String str;
		try {
			
			str = in.readLine();
			
			if(str.equals("OK")) {
				System.out.println("Conectado.");
				
				Platform.runLater(() -> {
					new CreateStage().create("/view/Chat.fxml");
					ClientMain.loginScreen.close();
				});
				
				
				while(true) {	//Escutando servidor
					
					String response = in.readLine();
					
					String[] elements = response.split(":");
					
					String nome = elements[1];
					String mensagem = elements[2];
					
					Platform.runLater(() -> {
						Updates.receive(nome, mensagem);
						ChatController.scrollDown();
					});
					
				}
				
			}else if(str.equals("TRYAGAIN")) {
				System.out.println("Conexão não concluida.");
				Platform.runLater(() -> Alerts.doAlert("Chat", "Nome não disponivel. Tente novamente.", AlertType.WARNING));
			}
			
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e2) {
			Platform.runLater(() -> Alerts.doAlert("Chat", "A conexão com o servidor foi perdida.", AlertType.ERROR));
			ChatController.sBtn_enviar.setDisable(true);
			ChatController.sTxf_mensagem.setDisable(true);
		}
		
	}
	
	
	public ClientThread(Socket Client, String Nome) {
		client = Client;
		nome = Nome;
	}
	public static Socket getClient() {
		return client;
	}
	public static void setClient(Socket Client) {
		client = Client;
	}
	public static String getNome() {
		return nome;
	}
	public static void setNome(String Nome) {
		nome = Nome;
	}
	
	public void instanceStreams() {
		
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
