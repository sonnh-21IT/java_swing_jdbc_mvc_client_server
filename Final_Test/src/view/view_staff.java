package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.chat_socket;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class view_staff extends JFrame {

	private JPanel contentPane;
	private JTextField txtMessageStaff;
	private final int port=2003;
	private final String serverhost="127.0.0.1";
	private chat_socket chatSocket;
	private JTextArea txtStaff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_staff frame = new view_staff();
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
	public view_staff() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 437, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Staff", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 400, 351);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 380, 273);
		panel.add(scrollPane);
		
		txtStaff = new JTextArea();
		scrollPane.setViewportView(txtStaff);
		
		txtMessageStaff = new JTextField();
		txtMessageStaff.setColumns(10);
		txtMessageStaff.setBounds(10, 301, 253, 39);
		panel.add(txtMessageStaff);
		
		JButton btnSendAdmin = new JButton("Send");
		btnSendAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Send(txtMessageStaff.getText());
			}
		});
		btnSendAdmin.setBounds(273, 301, 117, 39);
		panel.add(btnSendAdmin);
	}
	public void connect() {
		try {
			int port=this.port;
			Socket socket=new Socket(serverhost,port);
			
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
}
