package app;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Client;
import model.InfoMessage;
import rmi.ChatServer;
import utils.RMIConfiguration;

public class ChatServerApp extends UnicastRemoteObject implements ChatServer{

	private Map<String, Client> clients ;
	private List<InfoMessage> historicMessages;

	private static final long serialVersionUID = 21321321321321321L;

	protected ChatServerApp() throws RemoteException {
		super();
		clients = new HashMap<String, Client>();
		historicMessages = new LinkedList<InfoMessage>();
		
		init();
	}

	public static void main(String[] args) {
		try {
			ChatServer chat = new ChatServerApp();			
		}
		catch(RemoteException e) {
			System.out.println("Ocorreu o seguinte erro:");
			e.printStackTrace();
		}
	}
	
	private void init() {
		try {
			
			LocateRegistry.createRegistry(RMIConfiguration.getPORT());
			Naming.bind(RMIConfiguration.getUrl(), this);
			
			System.out.println("Running in "+ RMIConfiguration.getUrl() + "...");
		}
		catch(Exception  e) {
			e.printStackTrace();		
		} 
	}


	@Override
	public boolean addClient( Client client) throws RemoteException {
		if(!isRegistered(client.getUsername())) {
			
			clients.put(client.getUsername(), client);

			System.out.println(client.getUsername()+" foi adicionado ao chat!");
			System.out.println(client);
			
			return true;
		}
		return false;
	}

	@Override
	public boolean sendMessage(String message, String senderUsername) throws RemoteException {
		if(isRegistered(senderUsername)) {
			InfoMessage msg = new InfoMessage(message, senderUsername);
			historicMessages.add(msg);
			
			clients.values().forEach(client -> client.addMessageToHistoric(msg));
			
			System.out.println("Lista de clientes cadastrado:");			
			for(Client c : clients.values()) {
				System.out.println(c.getUsername());
				
				c.getHistoricMessages().forEach(h -> {
					System.out.println(h.toString());
				});				
			}				
				
			return true;
		}
		return false;
	}
	
	private boolean isRegistered(String username) {
		return clients.containsKey(username); 
	}

	@Override
	public void exitChat(String username) throws RemoteException {
		clients.remove(username);
		
		InfoMessage msg = new InfoMessage("Desconectado", username);
		
		historicMessages.add(msg);
		
		clients.values().forEach(client -> client.addMessageToHistoric(msg));
		
	}
}
