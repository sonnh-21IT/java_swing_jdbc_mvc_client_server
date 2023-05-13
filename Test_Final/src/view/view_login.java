package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.event_login;
import model.account;
import model.accountDAO;
import model.accountRMI;
import model.productDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class view_login extends JFrame {

	private JPanel contentPane;
	private static JPanel pnRoot;
	private JTextField txtUser;
	private JPasswordField txtPass;
	public static JPanel pnLogin;
	private Registry reg;
	private accountRMI rmi;
	private view_contact vct;
	private view_staff vst;
	
	/**
	 * Create the frame.
	 */
	public view_login(view_contact vct,view_staff vst) {
		this.vct=vct;
		this.vst=vst;
		//client
		clientAccountRun();
		
		event_login ev=new event_login(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(33, 158, 188));
		panel.setBounds(0, 0, 375, 411);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_public_white_48dp.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 84, 355, 131);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Topic Manager");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 235, 355, 58);
		panel.add(lblNewLabel_1);

		pnRoot = new JPanel();
		pnRoot.setBounds(374, 0, 375, 411);
		pnRoot.setLayout(null);

		pnLogin = new JPanel();
		pnLogin.setBounds(0, 0, 375, 411);
		pnLogin.setBackground(new Color(142,202, 230));

		pnLogin.setLayout(null);

		txtUser = new JTextField();
		txtUser.setText("UserName");
		txtUser.setForeground(Color.WHITE);
		txtUser.setFont(new Font("Arial", Font.PLAIN, 13));
		txtUser.setBorder(null);
		txtUser.setBackground(new Color(142,202, 230));
		txtUser.setBounds(132, 93, 200, 25);
		pnLogin.add(txtUser);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_account_circle_white_24dp.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(57, 80, 65, 52);
		pnLogin.add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(132, 118, 200, 2);
		pnLogin.add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_password_white_24dp.png"));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(57, 129, 65, 52);
		pnLogin.add(lblNewLabel_1_1);

		txtPass = new JPasswordField();
		txtPass.setText("PassWord");
		txtPass.setForeground(Color.WHITE);
		txtPass.setBorder(null);
		txtPass.setEchoChar('✮');
		txtPass.setBackground(new Color(142,202, 230));
		txtPass.setBounds(132, 142, 200, 25);
		pnLogin.add(txtPass);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(132, 167, 200, 2);
		pnLogin.add(separator_1);

		JCheckBox chkAH = new JCheckBox("Ẩn Mật Khẩu");
		chkAH.setSelected(true);
		chkAH.setForeground(Color.WHITE);
		chkAH.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txtPass.setEchoChar('✮');
				} else {
					txtPass.setEchoChar((char) 0);
				}
			}
		});
		chkAH.setFont(new Font("Arial", Font.PLAIN, 12));
		chkAH.setBackground(new Color(142,202, 230));
		chkAH.setBounds(132, 200, 97, 23);
		pnLogin.add(chkAH);

		JButton btnDN = new JButton("Đăng Nhập");
		btnDN.addActionListener(ev);
		btnDN.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_exit_to_app_black_18dp.png"));
		btnDN.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDN.setBackground(new Color(241, 250, 238));
		btnDN.setBounds(57, 243, 123, 31);
		pnLogin.add(btnDN);

		JButton btnDK = new JButton("Đăng Ký");
		btnDK.addActionListener(ev);
		btnDK.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_person_add_black_18dp.png"));
		btnDK.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDK.setBackground(new Color(241, 250, 238));
		btnDK.setBounds(209, 243, 123, 31);
		pnLogin.add(btnDK);

		JButton btnT = new JButton("Thoát");
		btnT.addActionListener(ev);
		btnT.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_cancel_presentation_black_18dp.png"));
		btnT.setFont(new Font("Arial", Font.PLAIN, 12));
		btnT.setBackground(new Color(241, 250, 238));
		btnT.setBounds(57, 285, 275, 31);
		pnLogin.add(btnT);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(57, 278, 275, 2);
		pnLogin.add(separator_2);
		pnRoot.add(pnLogin);
		contentPane.add(pnRoot);
		
	}
	public static void FromView(JPanel pnnew) {
		pnRoot.removeAll();
		pnRoot.add(pnnew);
		pnnew.setBounds(0, 0, 375, 411);
		pnRoot.validate();
		pnRoot.repaint();
	}
	public void signUp() {
		view_signup suv=new view_signup();
		FromView(suv);
	}

	public void exit() {
		System.exit(0);
	}
	public void checklogin() {
		String username = txtUser.getText();
		String password = new String(txtPass.getPassword());
		
		StringBuilder sb = new StringBuilder();

		if (username.equals("")) {
			sb.append("Tài Khoản Không Được Để Trống\n");
		} else if (password.equals("")) {
			sb.append("Mật Khẩu Không Được Để Trống\n");
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(this, sb.toString(), "Thất Bại", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			try {
				MessageDigest md=MessageDigest.getInstance("MD5");
				byte []passcustom=password.getBytes("UTF-8");
				byte []passcharacters=md.digest(passcustom);
				BigInteger bigint=new BigInteger(1,passcharacters);
				String passMD=bigint.toString(16).toUpperCase();
				
		        account acc = new account(username, passMD);
		        
				account accreal = rmi.checkData(acc);
				if (accreal != null) {
					new view_manager(vct,vst).setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(this, "Đăng Nhập Thành Công");
				} else {
					JOptionPane.showMessageDialog(this, "Sai Tài Khoản hoặc Mật Khẩu", "Thất bại",JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
			}

		}
	}
	public void clientAccountRun() {
		try {
			reg=LocateRegistry.getRegistry(2706);
			rmi=(accountRMI) reg.lookup("QLSANPHAM_Account");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
	}
}
