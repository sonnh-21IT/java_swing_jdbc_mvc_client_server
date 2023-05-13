package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import model.account;
import model.accountRMI;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class view_Signup extends JPanel {
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPassCF;
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
	 * Create the panel.
	 */
	public view_Signup() {
		clientRun();
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 318, 34);
		add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(64, 43, 197, 2);
		add(separator);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(120, 69, 208, 20);
		add(txtUser);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 69, 107, 20);
		add(lblNewLabel_1);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(120, 132, 208, 20);
		add(txtPass);
		
		JLabel lblNewLabel_1_1 = new JLabel("PassWord");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 132, 107, 20);
		add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Đăng Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLogin();
			}
		});
		btnNewButton.setBounds(64, 227, 107, 23);
		add(btnNewButton);
		
		JButton btnngK = new JButton("Đăng Ký");
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addAccount();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnngK.setBounds(181, 227, 107, 23);
		add(btnngK);
		
		txtPassCF = new JPasswordField();
		txtPassCF.setBounds(120, 182, 208, 20);
		add(txtPassCF);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PassWord");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(10, 182, 107, 20);
		add(lblNewLabel_1_1_1);
	}
	public static void viewLogin() {
		view_login.FromView(view_login.panel);
	}
	public void addAccount() throws Exception {
		String user=txtUser.getText();
		String pass=new String(txtPass.getPassword());
		String passCf=new String(txtPassCF.getPassword());
		
		
		if(pass.equals(passCf)) {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte []passcustom=pass.getBytes("UTF-8");
			byte []passcharacters=md.digest(passcustom);
			BigInteger bigint=new BigInteger(1,passcharacters);
			String passMD=bigint.toString(16).toUpperCase();
			
			account acc=new account();
			acc.setUser(user);
			acc.setPass(passMD);
			if(rmi.addAccount(acc)) {
				JOptionPane.showMessageDialog(this,"Đăng Ký Thành Công");
			}else {
				JOptionPane.showMessageDialog(this,"Đã Xảy Ra Lỗi");
			}
		}else {
			JOptionPane.showMessageDialog(this,"Mật Khẩu Nhập Lại Không Đúng");
		}
	}
}
