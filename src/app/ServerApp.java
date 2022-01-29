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
import rmi.ServerInterface;
import rmi.ClientInterface;
import utils.RMIConfiguration;

public class ServerApp extends UnicastRemoteObject implements ServerInterface{

	private Map<String, ClientInterface> clients ;
	private List<InfoMessage> historicMessages;

	private static final long serialVersionUID = 21321321321321321L;

	protected ServerApp() throws RemoteException {
		super();
		clients = new HashMap<String, ClientInterface>();
		historicMessages = new LinkedList<InfoMessage>();
		
		init();
	}

	public static void main(String[] args) {
		try {
			ServerInterface chat = new ServerApp();			
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
	public boolean addClient( ClientInterface client) throws RemoteException {
		if(!isRegistered(client.getUsername())) {
			
			clients.put(client.getUsername(), client);

			System.out.println(client.getUsername()+" foi adicionado ao chat!");			
			
			return true;
		}
		return false;
	}

	@Override
	public boolean sendMessage(String message, String senderUsername) throws RemoteException {
		if(isRegistered(senderUsername)) {
			InfoMessage msg = new InfoMessage(message, senderUsername);
			historicMessages.add(msg);
			
			clients.values().forEach(client -> {
				try {
					client.addMessageToHistoric(msg);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			System.out.println("Lista de clientes cadastrado:");			
			for(ClientInterface c : clients.values()) {
				System.out.println(c.getUsername());
				
//				c.getHistoricMessages().forEach(h -> {
//					System.out.println(h.toString());
//				});				
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
		
		clients.values().forEach(client -> {
			try {
				client.addMessageToHistoric(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
}
