package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import utils.StatusConnection;

public class InfoMessage implements Serializable {

	private static final long serialVersionUID = -7940156738805996476L;

	private String text;
	
	private String senderUsername;
	
	private LocalDateTime moment;
	
	private boolean wasDisplayed;

	public InfoMessage(String text, String senderUsername) {
		super();
		
		this.text = text; 
		this.senderUsername = senderUsername;
		this.moment = LocalDateTime.now();	
		this.wasDisplayed = false;
	}

	public String getText() {
		return text;
	}


	public String getSenderUsername() {
		return senderUsername;
	}


	public LocalDateTime getSendDate() {
		return moment;
	}
		
	
	public boolean isWasDisplayed() {
		return wasDisplayed;
	}
	
	public void setWasDisplayed(boolean value) {
		this.wasDisplayed = value;
	}
	
	public String show() {

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");		
		
		String momentFormatter =  timeFormatter.format(moment);
		String msg ;
		
		if(text.equals(StatusConnection.CONNECTED.toString()))
			msg =  "   --> "+senderUsername+" entrou no chat ! ("+momentFormatter+")  <--\n\n";
		
		else if(text.equals(StatusConnection.DISCONNECTED.toString()))
			msg = "   --> "+senderUsername+" saiu do chat ! ("+momentFormatter+")  <--\n\n";
		
		else
			msg = "> ("+momentFormatter+") "+senderUsername+" diz: "+text+"\n";
		
		return msg;
	}
}
