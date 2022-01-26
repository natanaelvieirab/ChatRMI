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

	private static final long serialVersionUID = 1L;

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
			System.out.println("deu ruim!");
			e.printStackTrace();
		}
	}
	
	private void init() {
		try {
			
			String urlRmi = "rmi://" + RMIConfiguration.getHOST() + ":" + RMIConfiguration.getPORT()+ "/" + RMIConfiguration.getNAME();
			
			LocateRegistry.createRegistry(RMIConfiguration.getPORT());
			Naming.bind(urlRmi, this);
			
			System.out.println("Running in "+ urlRmi + "...");
		}
		catch(Exception  e) {
			e.printStackTrace();		
		} 
	}



	@Override
	public boolean addClientes(Client client) throws RemoteException {
		if(!isRegistered(client.getUsername())) {
			clients.put(client.getUsername(), client);
			return true;
		}
		return false;
	}

	@Override
	public boolean sendMessage(String message, String senderUsername) throws RemoteException {
		if(isRegistered(senderUsername)) {
			InfoMessage msg = new InfoMessage(message, senderUsername);
			historicMessages.add(msg);
			
			clients.values().forEach(c -> c.addMessageToHistoric(msg));
						
			return true;
		}
		return false;
	}
	
	private boolean isRegistered(String username) {
		return clients.containsKey(username); 
	}
}
