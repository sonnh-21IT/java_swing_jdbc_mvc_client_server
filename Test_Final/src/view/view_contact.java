package view;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import controller.event_contact;
import model.chat_socket;
import model.client;
import model.server;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class view_contact extends JPanel {
	private JTextField txtMessageAdmin;
	public static JTextField txtPort;
	public static chat_socket chatSocket;
	public static JTextField txtServerHost;
	private server server;
	private client client;
	private final int port=2407;
	private final String serverhost="127.0.0.1";
	private JButton btnSendAdmin;
	private static JTextArea txtAdmin;
	private JScrollPane scrollPane;
	private view_staff vst;

	/**
	 * Create the panel.
	 */
	public view_contact(view_staff vst) {
		this.vst=vst;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(213, 99, 305, 364);
		add(panel);
		panel.setLayout(null);
		
		event_contact ev=new event_contact(this);
		
		txtMessageAdmin = new JTextField();
		txtMessageAdmin.setBounds(10, 333, 214, 20);
		panel.add(txtMessageAdmin);
		txtMessageAdmin.setColumns(10);
		
		btnSendAdmin = new JButton("");
		btnSendAdmin.addActionListener(ev);
		btnSendAdmin.setBackground(new Color(138, 201, 38));
		btnSendAdmin.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\send_FILL0_wght400_GRAD0_opsz484.png"));
		btnSendAdmin.setBounds(234, 324, 61, 35);
		panel.add(btnSendAdmin);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 285, 302);
		panel.add(scrollPane);
		
		txtAdmin = new JTextArea();
		txtAdmin.setLineWrap(true);
		scrollPane.setViewportView(txtAdmin);
		txtAdmin.setEditable(false);
		txtAdmin.setAutoscrolls(true);
		
		server=new server(port);
		client=new client(serverhost, port);
		
		txtPort = new JTextField();
		txtPort.setBounds(119, 37, 144, 20);
		add(txtPort);
		txtPort.setText(server.getPort()+"");
		txtPort.setEditable(false);
		txtPort.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPort.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Port");
		lblNewLabel.setBounds(48, 37, 61, 20);
		add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblServerHost = new JLabel("Server Host");
		lblServerHost.setBounds(483, 37, 86, 20);
		add(lblServerHost);
		lblServerHost.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtServerHost = new JTextField();
		txtServerHost.setBounds(579, 37, 144, 20);
		add(txtServerHost);
		txtServerHost.setText(client.getHost());
		txtServerHost.setFont(new Font("Arial", Font.PLAIN, 12));
		txtServerHost.setEditable(false);
		txtServerHost.setColumns(10);
	}
	public void listen() {
		try {
			int port=Integer.parseInt(txtPort.getText());
			ServerSocket serverSocket=new ServerSocket(port);
			Thread th=new Thread() {
				public void run() {
					try {
						Socket socket=serverSocket.accept();
						chatSocket = new chat_socket(socket, txtAdmin);
					} catch (Exception exception) {
						// TODO: handle exception
						txtAdmin.setText("Error:"+exception.getMessage());
						exception.printStackTrace();
					}
				}
			};
			th.start();
		} catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
	}
	public void Send(String message) {
		if(message.equals("")) {
			return;
		}
		vst.setVisible(true);
		chatSocket.send(message);
//		message.setText("");
	}
	public void close() throws Exception {
		chatSocket.close();
	}
	public JButton getBtnSendAdmin() {
		return btnSendAdmin;
	}
	public JTextField getTxtMessageAdmin() {
		return txtMessageAdmin;
	}
}
