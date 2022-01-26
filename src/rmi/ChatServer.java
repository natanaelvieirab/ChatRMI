package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Client;

public interface ChatServer extends Remote {
	public boolean addClientes(Client client) throws RemoteException;
	public boolean sendMessage(String message, String senderUsername) throws RemoteException;
}
