package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.event_staff;
import model.chat_socket;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class view_staff extends JFrame {

	private JPanel contentPane;
	private JTextField txtMessagerStaff;
	public static JTextArea txtStaff;
	private JButton btnSendStaff;
	private chat_socket chatSocket;
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
		event_staff ev=new event_staff(this);
		setBounds(900,150, 322, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Staff", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 306, 364);
		contentPane.add(panel);
		
		txtMessagerStaff = new JTextField();
		txtMessagerStaff.setColumns(10);
		txtMessagerStaff.setBounds(10, 333, 214, 20);
		panel.add(txtMessagerStaff);
		
		btnSendStaff = new JButton("");
		btnSendStaff.addActionListener(ev);
		btnSendStaff.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\send_FILL0_wght400_GRAD0_opsz482.png"));
		btnSendStaff.setBackground(new Color(138, 201, 38));
		btnSendStaff.setBounds(234, 324, 61, 35);
		panel.add(btnSendStaff);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 285, 302);
		panel.add(scrollPane);
		
		txtStaff = new JTextArea();
		txtStaff.setEditable(false);
		scrollPane.setViewportView(txtStaff);
	}
	public void connect() {
		try {
			int port=Integer.parseInt(view_contact.txtPort.getText());
			Socket socket=new Socket(view_contact.txtServerHost.getText(),port);
			
		   chatSocket=new chat_socket(socket,view_staff.txtStaff);
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
	}
	public void close() throws Exception {
//		chatSocket.close();
		dispose();
	}

	public JTextField getTxtMessagerStaff() {
		return txtMessagerStaff;
	}

	public JTextArea getTxtStaff() {
		return txtStaff;
	}

	public JButton getBtnSendStaff() {
		return btnSendStaff;
	}
	
}
