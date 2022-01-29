package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class InfoMessage implements Serializable {

	private static final long serialVersionUID = -7940156738805996476L;

	private String text;
	
	private String senderUsername;
	
	private LocalDate sendDate;
	
	private LocalTime sendTime;

	public InfoMessage(String text, String senderUsername) {
		super();
		this.text = text;
		this.senderUsername = senderUsername;
		this.sendDate = LocalDate.now();
		this.sendTime = LocalTime.now();
	}

	public String getText() {
		return text;
	}


	public String getSenderUsername() {
		return senderUsername;
	}


	public LocalDate getSendDate() {
		return sendDate;
	}
	

	public LocalTime getSendTime() {
		return sendTime;
	}

	
	public String toString() {
		if(text.contains("Desconectado"))
			return "--> "+senderUsername+" saiu do chat ! ("+sendDate+": "+sendTime+") <--";
		
		return ">> ("+sendDate+": "+sendTime+") "+senderUsername+" diz: "+text;
	}
}
