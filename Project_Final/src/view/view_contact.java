package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.event_contact;
import model.chat_socket;
import model.client;
import model.server;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class view_contact extends JPanel {
	private JTextField txtHost;
	private JTextField txtPort;
	private static JTextField smsAdmin;
	private static JTextField smsStaff;
	private server server;
	private client client;
	private chat_socket chatSocket;
	private final int port=2003;
	private final String serverhost="127.0.0.1";
	private JTextArea txtAdmin;
	private JTextArea txtStaff;
	private JButton btnSendAdmin;
	private JButton btnSendStaff;

	/**
	 * Create the panel.
	 */
	public view_contact() {
		setLayout(null);
		event_contact ev=new event_contact(this);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(304, 0, 238, 290);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 218, 228);
		panel.add(scrollPane);
		
		txtAdmin = new JTextArea();
		txtAdmin.setEditable(false);
		scrollPane.setViewportView(txtAdmin);
		
		smsAdmin = new JTextField();
		smsAdmin.setBounds(10, 259, 160, 20);
		panel.add(smsAdmin);
		smsAdmin.setColumns(10);
		
		btnSendAdmin = new JButton("");
		btnSendAdmin.addActionListener(ev);
		btnSendAdmin.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\send_FILL0_wght400_GRAD0_opsz482.png"));
		btnSendAdmin.setBounds(176, 251, 52, 31);
		panel.add(btnSendAdmin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Staff", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(552, 0, 238, 290);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 20, 218, 228);
		panel_1.add(scrollPane_1);
		
		txtStaff = new JTextArea();
		txtStaff.setEditable(false);
		scrollPane_1.setViewportView(txtStaff);
		
		smsStaff = new JTextField();
		smsStaff.setColumns(10);
		smsStaff.setBounds(10, 259, 160, 20);
		panel_1.add(smsStaff);
		
		btnSendStaff = new JButton("");
		btnSendStaff.addActionListener(ev);
		btnSendStaff.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\send_FILL0_wght400_GRAD0_opsz482.png"));
		btnSendStaff.setBounds(176, 251, 52, 31);
		panel_1.add(btnSendStaff);
		
		server = new server(port);
		client = new client(serverhost,port);
		
		txtHost = new JTextField();
		txtHost.setText(client.getHost());
		txtHost.setEditable(false);
		txtHost.setBounds(66, 11, 160, 20);
		add(txtHost);
		txtHost.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("host");
		lblNewLabel.setBounds(10, 11, 56, 20);
		add(lblNewLabel);
		
		txtPort = new JTextField();
		txtPort.setText(server.getPort()+"");
		txtPort.setEditable(false);
		txtPort.setColumns(10);
		txtPort.setBounds(66, 42, 107, 20);
		add(txtPort);
		
		JLabel lblHost = new JLabel("port");
		lblHost.setBounds(10, 42, 56, 20);
		add(lblHost);

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
	public void connect() {
		try {
			int port=Integer.parseInt(txtPort.getText());
			Socket socket=new Socket(txtHost.getText(),port);
			
			chatSocket=new chat_socket(socket,txtStaff);
		} catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
	}
	public void Send(String message) {
		if(message.equals("")) {
			return;
		}
		chatSocket.send(message);
//		message.setText("");
	}
	public JButton getBtnSendAdmin() {
		return btnSendAdmin;
	}
	public JButton getBtnSendStaff() {
		return btnSendStaff;
	}
	public static JTextField getTxtMessageAdmin() {
		return smsAdmin;
	}
	public static JTextField getTxtMessageStaff() {
		return smsStaff;
	}
}
