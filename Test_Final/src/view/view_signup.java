package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.event_signup;
import model.account;
import model.accountDAO;
import model.accountRMI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class view_signup extends JPanel {
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPassCF;
	private JTextField txtAdminname;
	private Registry reg;
	private accountRMI rmi;
	/**
	 * Create the panel.
	 */
	public view_signup() {
		event_signup ev=new event_signup(this);
		
		setBackground(new Color(142,202, 230));
		setLayout(null);

		txtUser = new JTextField();
		txtUser.setText("UserName");
		txtUser.setForeground(Color.WHITE);
		txtUser.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUser.setBorder(null);
		txtUser.setBackground(new Color(142,202, 230));
		txtUser.setBounds(124, 116, 200, 25);
		add(txtUser);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_account_circle_white_24dp.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(49, 103, 65, 52);
		add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(124, 141, 200, 2);
		add(separator);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_password_white_24dp.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(49, 152, 65, 52);
		add(lblNewLabel_1);

		txtPass = new JPasswordField();
		txtPass.setText("PassWord");
		txtPass.setForeground(Color.WHITE);
		txtPass.setBorder(null);
		txtPass.setBackground(new Color(142,202, 230));
		txtPass.setBounds(124, 165, 200, 25);
		txtPass.setEchoChar('✮');
		add(txtPass);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(124, 190, 200, 2);
		add(separator_1);

		JCheckBox chkAH = new JCheckBox("Ẩn Mật Khẩu");
		chkAH.setSelected(true);
		chkAH.setForeground(Color.WHITE);
		chkAH.setFont(new Font("Arial", Font.PLAIN, 12));
		chkAH.setBackground(new Color(142,202, 230));
		chkAH.setBounds(124, 265, 97, 23);
		add(chkAH);

		JButton btnDN = new JButton("Đăng Nhập");
		btnDN.addActionListener(ev);
		btnDN.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_exit_to_app_black_18dp.png"));
		btnDN.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDN.setBackground(new Color(241, 250, 238));
		btnDN.setBounds(49, 350, 275, 31);
		add(btnDN);

		JButton btnDK = new JButton("Đăng Ký");
		btnDK.addActionListener(ev);
		btnDK.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_person_add_black_18dp.png"));
		btnDK.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDK.setBackground(new Color(241, 250, 238));
		btnDK.setBounds(49, 295, 275, 31);
		add(btnDK);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(49, 337, 275, 2);
		add(separator_2);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_password_white_24dp.png"));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(49, 206, 65, 52);
		add(lblNewLabel_1_1);

		txtPassCF = new JPasswordField();
		txtPassCF.setText("PassWord");
		txtPassCF.setEchoChar('✮');
		txtPassCF.setForeground(Color.WHITE);
		txtPassCF.setBorder(null);
		txtPassCF.setBackground(new Color(142,202, 230));
		txtPassCF.setBounds(124, 219, 200, 25);
		add(txtPassCF);

		chkAH.addItemListener(new ItemListener() {
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

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(124, 244, 200, 2);
		add(separator_1_1);

		txtAdminname = new JTextField();
		txtAdminname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtAdminname.setText("");
			}
		});
		txtAdminname.setText("AdminName");
		txtAdminname.setForeground(Color.WHITE);
		txtAdminname.setFont(new Font("Arial", Font.PLAIN, 12));
		txtAdminname.setBorder(null);
		txtAdminname.setBackground(new Color(142,202, 230));
		txtAdminname.setBounds(124, 66, 200, 25);
		add(txtAdminname);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_manage_accounts_white_24dp(1).png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(49, 53, 65, 52);
		add(lblNewLabel_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(124, 91, 200, 2);
		add(separator_3);
		
		//client
		clientAccountRun();
	}
	public void clientAccountRun() {
		try {
			reg=LocateRegistry.getRegistry(2706);
			rmi=(accountRMI) reg.lookup("QLSANPHAM_Account");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void login() {
		view_login.FromView(view_login.pnLogin);
	}

	public void signup() {
		String pass = new String(txtPass.getPassword());
		String passcf = new String(txtPassCF.getPassword());
		String user = txtUser.getText();
		String name = txtAdminname.getText();
		try {

			if(pass.equals("") || passcf.equals("") || user.equals("") || name.equals("")) {
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
				acc.setName(name);
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
}
