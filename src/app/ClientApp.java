package app;

import java.awt.EventQueue;
import java.io.IOException;

import view.ClientView;

public class ClientApp {

	public static void main(String[] args) throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView clientView = new ClientView();
					
					clientView.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
