package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Server;

public class ServerController extends Thread {

	private Server server;
	private Socket connection;
	private String username;
	
	private InputStream inputStream;
	private InputStreamReader inputStreamReder;
	private BufferedReader bufferedReader;
	
	
	private static ServerSocket serverSocket;
	private static ArrayList<BufferedWriter> clients;
	
	public ServerController(Socket connection) {
		this.connection = connection;
		
		try {
			
			inputStream = connection.getInputStream();
			inputStreamReder = new InputStreamReader(inputStream);
			bufferedReader  = new BufferedReader(inputStreamReder);
			
		}catch (IOException e) {
	          e.printStackTrace();
	   }
	}

	public ServerController() {
		// TODO Auto-generated constructor stub
	}

	public void start(Server server) throws IOException {
		
		this.server = server;
		
		serverSocket = new ServerSocket(server.getPort());
		clients = new ArrayList<BufferedWriter>();
		
		while(true) {
			System.out.println("Aguardando conexão...");
			Socket connection = serverSocket.accept();
			
			System.out.println("Cliente conectado...");
			Thread thread = new ServerController(connection);
			
			thread.start();
		}
	
	}
	
	public void run() {
		try {
			
			String message;
			OutputStream outputStream = this.connection.getOutputStream();
			Writer writer = new OutputStreamWriter(outputStream);
			BufferedWriter buffWriter = new BufferedWriter(writer);
			
			clients.add(buffWriter);
			
			username = message = bufferedReader.readLine();
			
			while(message != null) {
				sendToAll(buffWriter, message);
				System.out.println(message);
			}
			
			
		}catch (Exception ex) {
			ex.printStackTrace();			
		}
	}
	
	private void sendToAll(BufferedWriter buffExit, String message)  {
		BufferedWriter writer ;
		
		try {
			for(BufferedWriter bw: clients) {
				writer = (BufferedWriter) bw;
				
				if(!(buffExit == writer)) {
					bw.write(username + "-> "+message+"\r\n");
					bw.flush();
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();			
		}
		
	}
	
}
