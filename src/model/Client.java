package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable{
		
	private static final long serialVersionUID = 1L;

	private String username;
	
	private List<InfoMessage> historicMessages ;

	public Client(String username) {
		this.username = username;
		this.historicMessages  = new LinkedList<InfoMessage>();
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void addMessageToHistoric(InfoMessage message) {
		historicMessages.add(message);
	}
	
	public List<InfoMessage> getHistoricMessages(){
		return historicMessages;
	}
	
	

}
