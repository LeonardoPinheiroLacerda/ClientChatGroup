package services.dinamicView;

import control.ChatController;

public class Updates {

	private static MessageMaker mm = new MessageMaker();
	
	public static void send(String message) {
		ChatController.sMensagens.getChildren().add(mm.sendMessage(message));
	}
	
	public static void receive(String nome, String message) {
		ChatController.sMensagens.getChildren().add(mm.recieveMessage(nome, message));
	}
	
	public static void clearMensagens() {
		ChatController.sMensagens.getChildren().remove(0, ChatController.sMensagens.getChildren().size());
	}
	
} 
