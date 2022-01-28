package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Client;
import model.InfoMessage;

public interface ChatServer extends Remote {
	public boolean addClient(Client client) throws RemoteException;
	public boolean sendMessage(String message, String senderUsername) throws RemoteException;
	public void exitChat(String username) throws RemoteException;
	
}
