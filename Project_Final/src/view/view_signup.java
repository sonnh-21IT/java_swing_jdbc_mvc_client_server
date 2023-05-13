package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controller.event_signup;
import model.account;
import model.accountRMI;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class view_signup extends JPanel {
	private JTextField txtUser;
	private JPasswordField txtPassCF;
	private JPasswordField txtPass;
	private Registry reg;
	private accountRMI rmi;

	/**
	 * Create the panel.
	 */
	public view_signup() {
		setLayout(null);
		event_signup ev=new event_signup(this);
		JButton btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(ev);
		btnLogin.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\login_25px.png"));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogin.setBounds(45, 240, 227, 34);
		add(btnLogin);
		
		JButton btnSignup = new JButton("Đăng Ký");
		btnSignup.addActionListener(ev);
		btnSignup.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\add_user_male_25px.png"));
		btnSignup.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSignup.setBounds(45, 280, 227, 34);
		add(btnSignup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 276, 212, 3);
		add(separator);
		
		txtUser = new JTextField();
		txtUser.setText("UserName");
		txtUser.setColumns(10);
		txtUser.setBounds(93, 86, 179, 23);
		add(txtUser);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\people_25px.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(37, 86, 46, 23);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\key_24px.png"));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(37, 176, 46, 23);
		add(lblNewLabel_1_1);
		
		txtPassCF = new JPasswordField();
		txtPassCF.setText("PassWord");
		txtPassCF.setEchoChar('✮');
		txtPassCF.setBounds(93, 176, 179, 23);
		add(txtPassCF);
		
		JCheckBox checkbox = new JCheckBox("Ẩn Mật Khẩu");
		checkbox.setSelected(true);
		checkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txtPass.setEchoChar('✮');
					txtPassCF.setEchoChar('✮');
				} else {
					txtPass.setEchoChar((char) 0);
					txtPassCF.setEchoChar((char) 0);
				}
			}
		});
		checkbox.setBounds(93, 206, 179, 23);
		add(checkbox);
		
		txtPass = new JPasswordField();
		txtPass.setText("PassWord");
		txtPass.setEchoChar('✮');
		txtPass.setBounds(93, 134, 179, 23);
		add(txtPass);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\key_24px.png"));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(37, 134, 46, 23);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\administrator_male_48px.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(115, 0, 82, 75);
		add(lblNewLabel);
		//client
		clientAccountRun();

	}
	public void login() {
		view_login.FromView(view_login.pnLogin);
	}
	public void signup() {
		String pass = new String(txtPass.getPassword());
		String passcf = new String(txtPassCF.getPassword());
		String user = txtUser.getText();
		try {

			if(pass.equals("") || passcf.equals("") || user.equals("")) {
				JOptionPane.showMessageDialog(this,"Vui Lòng Nhập Đầy Đủ Thông Tin !");
			} else if (pass.length() < 8||pass.length()>20 || user.length() < 8||user.length()>20) {
				JOptionPane.showMessageDialog(this,"Tài Khoản,Mật KHẩu Có Ít Nhất 8 kí tự Và Không Quá 20 kí tự !");
			} else if (!pass.equals(passcf)) {
				txtPassCF.setText("");
				txtPass.setText("");
				JOptionPane.showMessageDialog(this,"Nhập Lại Mật Khẩu Không Đúng !");
			} else{
				MessageDigest md=MessageDigest.getInstance("MD5");
				byte []passcustom=pass.getBytes("UTF-8");
				byte []passcharacters=md.digest(passcustom);
				BigInteger bigint=new BigInteger(1,passcharacters);
				String passMD=bigint.toString(16).toUpperCase();
				
				account acc = new account();
				acc.setUser(user);
				acc.setPass(passMD);
				boolean check=rmi.addAccount(acc);
				if(check) {
					JOptionPane.showMessageDialog(this,"Đăng Ký Thành Công !");
				}else {
					JOptionPane.showMessageDialog(this,"Tài Khoản Đã Tồn Tại !");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
		}
	}
	public void clientAccountRun() {
		try {
			reg=LocateRegistry.getRegistry(2706);
			rmi=(accountRMI) reg.lookup("server");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
