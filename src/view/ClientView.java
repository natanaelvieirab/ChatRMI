package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.swing.*;

import model.Client;
import model.InfoMessage;

public class ClientView extends JFrame implements ActionListener, KeyListener{
	
	private JTextArea screenOutputJT;
	private JTextField messageTField;
	private JButton btnSend;
	private JButton btnExit;
	private JLabel historicMsgJL;
	private JLabel messageJL;
	private JPanel contentPainel;
	
	private final int PAINEL_WIDTH = 400;
	private final int PAINEL_HEIGHT = 450;
			 
	private JTextField usernameTField;
	
	private Client client ;
	
	public void start() {
		String username =  infoClient();
		
		try {
			this.client = new Client(username);
			
			if(this.client.connectChat())
				loadScreen();
			else 
				System.out.println("Erro ao se conectar ao chat!");
			
			// Criando tarefa para verificar se há mensagen para o cliente
			Timer time = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					showMessages();					
				}
			};
			
			time.scheduleAtFixedRate(task, 0, 1000);			
			
				
		}
		catch(Exception ex) {
			System.out.println("Aconteceu o seguinte erro: \n"+ex.getMessage());
		}
	}
	
	private String infoClient() {
		JLabel lblMessage = new JLabel("Informe o seu nome:");
		
	    usernameTField = new JTextField("Cliente");
	    
	    Object[] texts = {lblMessage, usernameTField };	    
	    
	    JOptionPane.showMessageDialog(null, texts);
	   	    
	    return usernameTField.getText(); 
	}
	
	
	private void loadScreen() {
		contentPainel = new JPanel();
	    screenOutputJT = new JTextArea(20,30);
	    screenOutputJT.setEditable(false);
	    screenOutputJT.setBackground(new Color(240,240,240));
	    
	    messageTField = new JTextField(30);
	    historicMsgJL = new JLabel("Histórico");
	    messageJL = new JLabel("Mensagem");
	    btnSend = new JButton("Enviar");
	    btnSend.setToolTipText("Enviar Mensagem");
	    btnExit = new JButton("Sair");
	    btnExit.setToolTipText("Sair do Chat");
	    
	    btnSend.addActionListener(this);
	    btnExit.addActionListener(this);
	    btnSend.addKeyListener(this);
	    messageTField.addKeyListener(this);
	    
	    JScrollPane scroll = new JScrollPane(screenOutputJT);
	    screenOutputJT.setLineWrap(true);
	    contentPainel.add(historicMsgJL);
	    contentPainel.add(scroll);
	    contentPainel.add(messageJL);
	    contentPainel.add(messageTField);
	    contentPainel.add(btnExit);
	    contentPainel.add(btnSend);
	    contentPainel.setBackground(Color.LIGHT_GRAY);
	    
	    screenOutputJT.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    messageTField.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
	    
	    setTitle(usernameTField.getText());
	    setContentPane(contentPainel);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setSize(PAINEL_WIDTH,PAINEL_HEIGHT);   
	    
	    setVisible(true);
	    
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
				sendMessage();
	       }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals(btnSend.getActionCommand())) {
				sendMessage();
				return;
			}				
			
			if(e.getActionCommand().equals(btnExit.getActionCommand())) {
				client.exitChat();
				
				screenOutputJT.append("\n Desconectado \r\n");
				System.exit(0);
			}					
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void sendMessage() {
		this.client.sendMessage(messageTField.getText());
		messageTField.setText("");
		showMessages();
	}
	
	private void showMessages() {
		
	
		client.getHistoricMessages().forEach(infoMsg -> {
			if(!infoMsg.isWasDisplayed()) {				
				screenOutputJT.append(infoMsg.show());
				infoMsg.setWasDisplayed(true);
			}			
		});
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
