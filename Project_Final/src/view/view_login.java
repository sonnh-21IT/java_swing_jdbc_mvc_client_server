package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.event_login;
import model.account;
import model.accountDAO;
import model.accountRMI;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class view_login extends JFrame {

	private static JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	public static JPanel pnLogin;
	private Registry reg;
	private accountRMI rmi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_login frame = new view_login();
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
	public view_login() {
		//client
		clientRun();
		
		event_login ev=new event_login(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		pnLogin = new JPanel();
		pnLogin.setBounds(0, 0, 306, 325);
		contentPane.add(pnLogin);
		pnLogin.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setText("UserName");
		txtUser.setBounds(88, 122, 179, 23);
		pnLogin.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setText("PassWord");
		txtPass.setEchoChar('✮');
		txtPass.setBounds(88, 156, 179, 23);
		pnLogin.add(txtPass);
		
		JCheckBox checkbox = new JCheckBox("Ẩn Mật Khẩu");
		checkbox.setSelected(true);
		checkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txtPass.setEchoChar('✮');
				} else {
					txtPass.setEchoChar((char) 0);
				}
			}
		});
		checkbox.setBounds(88, 186, 179, 23);
		pnLogin.add(checkbox);
		
		JButton btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(ev);
		btnLogin.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\login_25px.png"));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogin.setBounds(40, 216, 227, 34);
		pnLogin.add(btnLogin);
		
		JButton btnSignup = new JButton("Đăng Ký");
		btnSignup.addActionListener(ev);
		btnSignup.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\add_user_male_25px.png"));
		btnSignup.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSignup.setBounds(40, 256, 227, 34);
		pnLogin.add(btnSignup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(49, 252, 212, 3);
		pnLogin.add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\administrator_male_75px.png"));
		lblNewLabel.setBounds(110, 11, 101, 100);
		pnLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\people_25px.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(32, 122, 46, 23);
		pnLogin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Project_Final\\icon_image\\key_24px.png"));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(32, 156, 46, 23);
		pnLogin.add(lblNewLabel_1_1);
	}
	public void signUp() {
		view_signup suv = new view_signup();
		FromView(suv);
	}

	public static void FromView(JPanel pnnew) {
		contentPane.removeAll();
		contentPane.add(pnnew);
		pnnew.setBounds(0, 0, 375, 411);
		contentPane.validate();
		contentPane.repaint();
	}
	public void clientRun() {
		try {
			reg=LocateRegistry.getRegistry(2706);
			rmi=(accountRMI) reg.lookup("server");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void checkAccount() {
		String pass=new String(txtPass.getPassword());
		String user=txtUser.getText();
		
		StringBuilder sb = new StringBuilder();

		if (user.equals("")) {
			sb.append("Tài Khoản Không Được Để Trống\n");
		} else if (pass.equals("")) {
			sb.append("Mật Khẩu Không Được Để Trống\n");
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(this, sb.toString(), "Thất Bại", JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			try {
				MessageDigest md=MessageDigest.getInstance("MD5");
				byte []passcustom=pass.getBytes("UTF-8");
				byte []passcharacters=md.digest(passcustom);
				BigInteger bigint=new BigInteger(1,passcharacters);
				String passMD=bigint.toString(16).toUpperCase();
				
				account acc=new account(user,passMD);
				
				account accreal=rmi.checkData(acc);
				if(accreal!=null) {
					new view_main().setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(this,"Đăng Nhập Thành Công !");
				}else {
					JOptionPane.showMessageDialog(this, "Sai Tài Khoản hoặc Mật Khẩu", "Thất bại",JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
			}
		}
	}
}
