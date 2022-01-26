package controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import model.Client;
import model.InfoMessage;
import rmi.ChatServer;
import utils.RMIConfiguration;

public class ClientController {

	private Client infoClient;
	private ChatServer server;
	
	
	public ClientController(String username) {
		this.infoClient  = new Client(username);		
		
		createConnection();
	}
	
	
	private void createConnection()  {
		try {			
			
			this.server = (ChatServer) Naming.lookup(RMIConfiguration.getUrl());
			
		}catch(MalformedURLException ex) {
			System.out.println("Servidor não encontrado! \nError: "+ex.getMessage());			
		}catch (NotBoundException | RemoteException ex) {
			ex.printStackTrace();			
		} 
		  
	}
	
	public void exit() {
		
        try {
        	sendMessage("Desconectado \r\n");
        	
	        
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
        
	}
	
	public boolean connectChat() {
		try {
			server.addClientes(infoClient);
			
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
			
			server.sendMessage(msg, infoClient.getUsername());		
			
		}catch (Exception ex) {
			System.out.println("Houve um erro durante o envio da mensagem!");
			ex.printStackTrace();
		}
	}
	
	public List<InfoMessage> getHistoricMessages(){
		return infoClient.getHistoricMessages();
	}
	
	public String getUsername(){
		return infoClient.getUsername();
	}

}
