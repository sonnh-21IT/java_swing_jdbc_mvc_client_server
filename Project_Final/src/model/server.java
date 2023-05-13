package model;

import java.net.ServerSocket;

public class server {
	private int port;

	public server(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
