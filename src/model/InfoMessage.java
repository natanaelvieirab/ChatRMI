package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class InfoMessage {

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

	public void setText(String text) {
		this.text = text;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public LocalTime getSendTime() {
		return sendTime;
	}

	public void setSendTime(LocalTime sendTime) {
		this.sendTime = sendTime;
	}

	public String toString() {
		if(text.contains("Desconectado"))
			return "--> "+senderUsername+" saiu do chat ! ("+sendDate+": "+sendTime+") <--";
		
		return ">> ("+sendDate+": "+sendTime+") "+senderUsername+" diz: "+text;
	}
}
