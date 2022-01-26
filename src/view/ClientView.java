package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

import controller.ClientController;
import model.Client;

public class ClientView extends JFrame implements ActionListener, KeyListener{
	
	private JTextArea screenOutputJT;
	private JTextField messageTField;
	private JButton btnSend;
	private JButton btnExit;
	private JLabel historicMsgJL;
	private JLabel messageJL;
	private JPanel contentPainel;

	private JTextField usernameTField;
	
	private ClientController clientController ;
	
	public void start() {
		String username =  infoClient();
		
		try {
			this.clientController = new ClientController(username);
			clientController.connectChat();
			loadScreen();
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
	    screenOutputJT = new JTextArea(10,20);
	    screenOutputJT.setEditable(false);
	    screenOutputJT.setBackground(new Color(240,240,240));
	    
	    messageTField = new JTextField(20);
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
	    setSize(250,300);   
	    
	    setVisible(true);
	    
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
	           clientController.sendMessage(messageTField.getText());
	       }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals(btnSend.getActionCommand())) {
				clientController.sendMessage(messageTField.getText());
				screenOutputJT.append(messageTField.getText());
				
				return;
			}				
			
			if(e.getActionCommand().equals(btnExit.getActionCommand())) {
				clientController.exit();
				screenOutputJT.append("Desconectado \r\n");
			}					
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
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
