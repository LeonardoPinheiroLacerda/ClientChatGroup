package services.dinamicView;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MessageMaker extends Maker{
	
	public AnchorPane sendMessage(String mensagem) {
		
		Label label = new Label();
		label.setTextFill(Color.WHITE);
		label.setWrapText(true);
		label.setFont(FONTE);
		label.setText(mensagem);
		
		AnchorPane inPane = new AnchorPane(label);
		
		AnchorPane.setTopAnchor(label, 10.0);
		AnchorPane.setBottomAnchor(label, 10.0);
		AnchorPane.setLeftAnchor(label, 10.0);
		AnchorPane.setRightAnchor(label, 10.0);
		
		inPane.setMaxWidth(397.0);
		
		inPane.setMaxHeight(500.0);
		
		double width = (getLinhas(mensagem)) * 16 + 20;
		inPane.setMinHeight(width);
		
		inPane.setStyle("-fx-background-color: " + SEND_COLOR + "; -fx-background-radius: 25");

		return new AnchorPane(inPane);

	}
	
	public AnchorPane recieveMessage(String nome, String mensagem) {
		
		Label labelNome = new Label();
		labelNome.setTextFill(Color.WHITE);
		labelNome.setWrapText(false);
		labelNome.setFont(NOME_FONTE);
		labelNome.setText(nome);
		labelNome.setTextAlignment(TextAlignment.RIGHT);
		labelNome.setAlignment(Pos.CENTER_RIGHT);
		labelNome.setUnderline(true);
		
		Label label = new Label();
		label.setTextFill(Color.WHITE);
		label.setWrapText(true);
		label.setFont(FONTE);
		label.setText(mensagem);
		label.setTextAlignment(TextAlignment.RIGHT);
				
		AnchorPane.setTopAnchor(labelNome, 10.0);
		AnchorPane.setRightAnchor(labelNome, 10.0);
		AnchorPane.setLeftAnchor(labelNome, 10.0);
		
		AnchorPane.setTopAnchor(label, 28.0);
		AnchorPane.setBottomAnchor(label, 10.0);
		AnchorPane.setLeftAnchor(label, 10.0);
		AnchorPane.setRightAnchor(label, 10.0);
		
		AnchorPane inPane = new AnchorPane(labelNome, label);
		
		AnchorPane.setRightAnchor(inPane, 0.0);
		
		inPane.setMaxWidth(397.0);
		
		inPane.setMaxHeight(500.0);
		
		double height = (getLinhas(mensagem) + 1) * 16 + 25;
		inPane.setMinHeight(height);
		
		inPane.setStyle("-fx-background-color: " + RECIEVE_COLOR + "; -fx-background-radius: 25");

		return new AnchorPane(inPane);
		
	}
	
	public int getLinhas(String message) {
		
		int linhas = 1;
		int contPalavra = 0;
		String[] palavras = message.split(" ");
		
		for(int i = 0; i < palavras.length; i ++) {
			contPalavra += palavras[i].length();
			
			if(contPalavra > LETRAS_POR_LINHA) {
				contPalavra = palavras[i].length();
				linhas += 1;
			}			
			
			contPalavra += 1; //Espaço
		}
		
		return linhas;
	}
	
}
