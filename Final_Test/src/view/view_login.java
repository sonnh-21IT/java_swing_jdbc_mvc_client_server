package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.account;
import model.accountRMI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.awt.event.ActionEvent;

public class view_login extends JFrame {

	private static JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	public static JPanel panel;
	private view_admin vad;
	private view_staff vst;
	private Registry reg;
	private accountRMI rmi;
	public void clientRun() {
		try {
			reg=LocateRegistry.getRegistry(2609);
			rmi=(accountRMI) reg.lookup("serverLogin");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					view_login frame = new view_login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public view_login(view_admin vad,view_staff vst) {
		this.vad=vad;
		this.vst=vst;
		clientRun();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 341, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 318, 34);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(64, 43, 197, 2);
		panel.add(separator);
		
		txtUser = new JTextField();
		txtUser.setBounds(120, 86, 208, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 86, 107, 20);
		panel.add(lblNewLabel_1);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(120, 134, 208, 20);
		panel.add(txtPass);
		
		JLabel lblNewLabel_1_1 = new JLabel("PassWord");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 134, 107, 20);
		panel.add(lblNewLabel_1_1);
		
		JButton btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(64, 186, 107, 23);
		panel.add(btnLogin);
		
		JButton btnSignup = new JButton("Đăng Ký");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FromView(new view_Signup());
			}
		});
		btnSignup.setBounds(181, 186, 107, 23);
		panel.add(btnSignup);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(64, 220, 224, 23);
		panel.add(btnExit);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(79, 215, 197, 2);
		panel.add(separator_1);
	}
	public static void FromView(JPanel pnNew) {
		contentPane.removeAll();
		contentPane.add(pnNew);
		pnNew.setBounds(0,0,341, 261);
		contentPane.validate();
		contentPane.repaint();
	}
	public void login() throws Exception{
		account acc=new account();
		acc.setUser(txtUser.getText());
		String pass=new String(txtPass.getPassword());
		
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte []passcustom=pass.getBytes("UTF-8");
		byte []passcharacters=md.digest(passcustom);
		BigInteger bigint=new BigInteger(1,passcharacters);
		String passMD=bigint.toString(16).toUpperCase();
		
		acc.setPass(passMD);
		if(rmi.checkData(acc)!=null) {
			dispose();
			new view_manager(vad,vst).setVisible(true);
			JOptionPane.showMessageDialog(this,"Đăng Nhập Thành Công");
		}else {
			JOptionPane.showMessageDialog(this,"Sai Tài Khoản Hoặc Mật Khẩu");
		}
	}
}
