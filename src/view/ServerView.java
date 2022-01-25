package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ServerController;
import model.Server;

public class ServerView {
	
	private Server server;
	private ServerController serverController;

	public ServerView() {
		this.serverController = new ServerController();
	}
	
	public void start() {
		try {
			this.server = infoServer();
			
			serverController.start(this.server);
			
			JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+
					this.server.getPort());
			
		}catch(Exception ex) {
			ex.printStackTrace();			
		}
	}
	
	private Server infoServer() {
		JLabel titleJL = new JLabel("Informe porta do servidor:");
		JTextField portTField = new JTextField("12345");
		
		Object[] texts = {titleJL, portTField};
		
		JOptionPane.showMessageDialog(null, texts);
		
		return new Server(Integer.parseInt(portTField.getText()));		
		 
	}
	
	
}
