package model;

public class InfoMessage {

	private String text;
	
	private String senderUsername;

	public InfoMessage(String text, String senderUsername) {
		super();
		this.text = text;
		this.senderUsername = senderUsername;
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

	@Override
	public String toString() {
		return ">> "+senderUsername.toUpperCase() + " diz: "+text;
	}
	
	
	
}
