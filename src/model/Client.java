package model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import rmi.ServerInterface;
import rmi.ClientInterface;
import utils.RMIConfiguration;

public class Client extends UnicastRemoteObject implements ClientInterface{

	
	private static final long serialVersionUID = -4683827120430115498L;
	
	private String username;	
	private List<InfoMessage> historicMessages ;	
	private transient ServerInterface server;

	public Client(String username) throws RemoteException {
		this.username = username;
		this.historicMessages  = new LinkedList<InfoMessage>();
		
		try {
			
			server = (ServerInterface) Naming.lookup(RMIConfiguration.getUrl());
			
		}catch(MalformedURLException ex) {
			System.out.println("Servidor não encontrado! \nError: "+ex.getMessage());			
		}catch (NotBoundException | RemoteException ex) {
			ex.printStackTrace();			
		}  
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	

	public void setHistoricMessages(List<InfoMessage> historicMessages) {
		this.historicMessages = historicMessages;
	}


	public void addMessageToHistoric(InfoMessage message) throws RemoteException {
		historicMessages.add(message);
		historicMessages.forEach(h -> System.out.println(h.getSenderUsername() + ": " + h.getText()));
	}
	
	public List<InfoMessage> getHistoricMessages(){
		if(historicMessages.isEmpty()) {
			List<InfoMessage> test = new LinkedList<InfoMessage>();
			test.add(new InfoMessage("text", "username"));
			return test;
		}
		return historicMessages;
	}
	
	public boolean connectChat() {
		try {
			server.addClient(this);	
						
			System.out.println("Usuário '"+username+"' se conectou ao chat.");
			
			return true;
		}catch (Exception ex) {
			System.out.println("Houve um erro durante o cadastro do usuário ao chat!");
			ex.printStackTrace();
			
			return false;
		}
	}
	
	public void sendMessage(String msg) {
		try {
			if(msg.length() == 0)
				return;
			
			server.sendMessage(msg, username);			
			
		}catch (Exception ex) {
			System.out.println("Houve um erro durante o envio da mensagem!");
			ex.printStackTrace();
		}
	}
	
	public void exitChat() {
		try {
        	server.exitChat(username);        	
	        
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}



}
