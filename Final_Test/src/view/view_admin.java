package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import model.chat_socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class view_admin extends JFrame {

	private JPanel contentPane;
	private JTextField txtMessageAdmin;
	private final int port=2003;
	private final String serverhost="127.0.0.1";
	private JTextArea txtAdmin;
	protected chat_socket chatSocket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_admin frame = new view_admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view_admin() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 437, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 400, 351);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 380, 273);
		panel.add(scrollPane);
		
		txtAdmin = new JTextArea();
		txtAdmin.setEditable(false);
		scrollPane.setViewportView(txtAdmin);
		
		txtMessageAdmin = new JTextField();
		txtMessageAdmin.setBounds(10, 301, 253, 39);
		panel.add(txtMessageAdmin);
		txtMessageAdmin.setColumns(10);
		
		JButton btnSendStaff = new JButton("Send");
		btnSendStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Send(txtMessageAdmin.getText());
			}
		});
		btnSendStaff.setBounds(273, 301, 117, 39);
		panel.add(btnSendStaff);
	}
	public void listen() {
		try {
			int port=this.port;
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
		chatSocket.send(message);
//		message.setText("");
	}
}
