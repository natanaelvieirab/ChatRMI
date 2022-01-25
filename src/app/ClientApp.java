package app;

import java.io.IOException;

import view.ClientView;

public class ClientApp {

	public static void main(String[] args) throws IOException {
		ClientView clientView = new ClientView();
		
		clientView.start();

	}

}
