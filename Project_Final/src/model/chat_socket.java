package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class chat_socket{
	private Socket socket;
	private JTextArea txtMain;
	private PrintWriter out;
	private BufferedReader reader;
	
	public chat_socket(Socket socket, JTextArea txtMain) {
		this.socket = socket;
		this.txtMain = txtMain;
		try {
			out = new PrintWriter(socket.getOutputStream());
			reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
			receive();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void receive() {
		Thread th=new Thread() {
			public void run() {
				while(true) {
					try {
						String line=reader.readLine();
						if(line!=null) {
							txtMain.setText(txtMain.getText()+"\n"+line);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
	}
	public void send(String txt) {
		String curent=txtMain.getText();
		txtMain.setText(curent+"\n"+txt);
		out.println(txt);
		out.flush();
	}
	public void close() throws Exception{
		out.close();
		reader.close();
		socket.close();
	}
}
