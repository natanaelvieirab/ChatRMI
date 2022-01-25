package controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import model.Client;

public class ClientController {

	private Client infoClient;
	
	private Socket socket;
	private OutputStream ou ;
	private Writer ouw;
	private BufferedWriter bfw;	
	
	public void createConnection(Client infoClient)  {
		try {
			this.infoClient = infoClient;
			
			socket = new Socket(infoClient.getIP(),Integer.parseInt(infoClient.getPort()));
			ou = socket.getOutputStream();
			ouw = new OutputStreamWriter(ou);
			bfw = new BufferedWriter(ouw);
		  
			bfw.write(infoClient.getUsername()+"\r\n");
		  
			bfw.flush();
		} catch (NumberFormatException | IOException e) {			
			e.printStackTrace();
		}
		  
	}
	
	public void exit() {
		
        try {
        	sendMessage("Desconectado \r\n");
        	
			bfw.close();
			ouw.close();
	        ou.close();
	        
	        socket.close();
	        
		} catch (IOException ex) {
			
			ex.printStackTrace();
		}
        
	}
	
	public void sendMessage(String msg) {
		try {
			bfw.write(msg+"\r\n");
			bfw.flush();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
