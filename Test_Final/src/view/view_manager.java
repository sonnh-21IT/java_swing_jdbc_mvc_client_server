package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.event_viewmanager;
import model.accountRMI;
import model.productDAO;
import model.productRMI;

public class view_manager extends JFrame {

	private JPanel contentPane;
	private JPanel pnView;
	public static JLabel lblAdmin;
	private view_staff vst;
	private view_contact vct;
	/**
	 * Create the frame.
	 */
	public view_manager(view_contact vct,view_staff vst) {
		this.vct=vct;
		this.vst=vst;
		event_viewmanager ev=new event_viewmanager(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50,1000,600);
//		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnMenu = new JPanel();
		pnMenu.setBackground(Color.WHITE);
		pnMenu.setBounds(0, 0, 250, 561);
		contentPane.add(pnMenu);
		pnMenu.setLayout(null);
		
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(236, 70, 70));
		pnTitle.setBounds(0, 0, 250, 80);
		pnMenu.add(pnTitle);
		pnTitle.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Manager");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_manage_accounts_white_24dp.png"));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 250, 80);
		pnTitle.add(lblNewLabel);
		
		JButton btnHome = new JButton("Màn Hình Chính");
		btnHome.addActionListener(ev);
		btnHome.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_home_black_18dp.png"));
		btnHome.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHome.setBorder(new LineBorder(new Color(176, 224, 230), 1, true));
		btnHome.setBackground(new Color(110, 203, 99));
		btnHome.setBounds(10, 107, 230, 60);
		pnMenu.add(btnHome);
		
		JButton btnQuanly = new JButton("Quản Lý Sản Phẩm");
		btnQuanly.addActionListener(ev);
		btnQuanly.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_analytics_black_18dp.png"));
		btnQuanly.setFont(new Font("Arial", Font.PLAIN, 15));
		btnQuanly.setBorder(new LineBorder(new Color(176, 224, 230), 1, true));
		btnQuanly.setBackground(new Color(110, 203, 99));
		btnQuanly.setBounds(10, 196, 230, 60);
		pnMenu.add(btnQuanly);
		
		JButton btnDong = new JButton("Đóng");
		btnDong.addActionListener(ev);
		btnDong.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_logout_black_18dp.png"));
		btnDong.setFont(new Font("Arial", Font.PLAIN, 15));
		btnDong.setBorder(new LineBorder(new Color(176, 224, 230), 1, true));
		btnDong.setBackground(new Color(110, 203, 99));
		btnDong.setBounds(10, 379, 230, 60);
		pnMenu.add(btnDong);
		
		JButton btnLH = new JButton("Liên Hệ Admin");
		btnLH.addActionListener(ev);
		btnLH.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\connect_without_contact_FILL0_wght400_GRAD0_opsz482.png"));
		btnLH.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLH.setBorder(new LineBorder(new Color(176, 224, 230), 1, true));
		btnLH.setBackground(new Color(110, 203, 99));
		btnLH.setBounds(10, 286, 230, 60);
		pnMenu.add(btnLH);
		
		lblAdmin = new JLabel("");
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setIcon(new ImageIcon("C:\\Users\\hongs\\Documents\\Code_Java\\QLDETAI\\icon_image\\admin.png"));
		lblAdmin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdmin.setBounds(10, 516, 230, 34);
		pnMenu.add(lblAdmin);
		
		pnView = new JPanel();
		pnView.setBorder(new LineBorder(new Color(67, 97, 238)));
		view_home vh=new view_home();
		pnView.setBounds(250, 0, 733, 561);
		setView(vh);
		contentPane.add(pnView);
		pnView.setLayout(null);
	}
	public void viewHome() {
		view_home vh=new view_home();
		setView(vh);
	}
	public void viewManeger() {
		view_main vm=new view_main();
		setView(vm);
	}
	public void viewLogin() {
		view_login vlg=new view_login(vct,vst);
		dispose();
		vlg.setVisible(true);
	}
	public void viewContatc() {
		vst.setVisible(true);
		setView(vct);
	}
	public void closeChat() {
		if(vst!=null) {
			try {
				vst.close();
//				vct.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setView(JPanel newview) {
		newview.setBounds(0, 0,733,560);
		pnView.removeAll();
		pnView.add(newview);
		pnView.validate();
		pnView.repaint();
	}
}
