package rmi;

import java.rmi.RemoteException;
import java.util.List;

import model.InfoMessage;

import java.rmi.Remote;

public interface ClientInterface extends Remote {
	public void addMessageToHistoric(InfoMessage message) throws RemoteException;
	public String getUsername() throws RemoteException;	
}
