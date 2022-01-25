package app;

import java.io.IOException;

import view.ServerView;

public class ServerApp {

	public static void main(String[] args) throws IOException {
		ServerView serverView = new ServerView();
		
		serverView.start();

	}
}
