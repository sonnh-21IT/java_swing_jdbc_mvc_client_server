package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.event_viewaddproduct;
import model.product;
import model.productDAO;
import model.productRMI;

public class view_addproduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNAME;
	private JTextField txtPRODUCER;
	private JTextField txtPRICE;
	private JComboBox cbbSRC;
	private JComboBox cbbTYPE;

	private Registry reg;
	private productRMI rmi;
	public void clientRun() {
		try {
			reg=LocateRegistry.getRegistry(2706);
			rmi=(productRMI) reg.lookup("QLSANPHAM_Product");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_addproduct frame = new view_addproduct();
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
	public view_addproduct() {
		event_viewaddproduct ev=new event_viewaddproduct(this);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 572, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("THÊM SẢN PHẨM");
		lblNewLabel.setForeground(new Color(0, 204, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 536, 29);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 102, 204));
		separator.setBounds(115, 38, 331, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(31, 74, 46, 20);
		contentPane.add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Arial", Font.PLAIN, 12));
		txtID.setBounds(87, 74, 107, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("TÊN");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(31, 138, 46, 20);
		contentPane.add(lblNewLabel_1_1);
		
		txtNAME = new JTextField();
		txtNAME.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNAME.setColumns(10);
		txtNAME.setBounds(87, 138, 209, 20);
		contentPane.add(txtNAME);
		
		JLabel lblNewLabel_1_2 = new JLabel("NHÀ SẢN XUẤT");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(310, 74, 94, 20);
		contentPane.add(lblNewLabel_1_2);
		
		txtPRODUCER = new JTextField();
		txtPRODUCER.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPRODUCER.setColumns(10);
		txtPRODUCER.setBounds(414, 74, 107, 20);
		contentPane.add(txtPRODUCER);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("XUẤT XỨ");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(31, 202, 57, 22);
		contentPane.add(lblNewLabel_1_2_1);
		
		cbbSRC = new JComboBox();
		cbbSRC.setModel(new DefaultComboBoxModel(new String[] {"Khác", "Trong nước", "Nước ngoài"}));
		cbbSRC.setBounds(87, 202, 138, 22);
		contentPane.add(cbbSRC);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("LOẠI");
		lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1.setBounds(31, 244, 57, 22);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		cbbTYPE = new JComboBox();
		cbbTYPE.setModel(new DefaultComboBoxModel(new String[] {"Khác", "Nội thất", "Đồ dùng học tập", "Dụng cụ sửa chữa"}));
		cbbTYPE.setBounds(87, 244, 138, 22);
		contentPane.add(cbbTYPE);
		
		txtPRICE = new JTextField();
		txtPRICE.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPRICE.setColumns(10);
		txtPRICE.setBounds(414, 202, 107, 20);
		contentPane.add(txtPRICE);
		
		JLabel lblNewLabel_1_3 = new JLabel("GIÁ");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(358, 202, 46, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JButton btnREFRESH = new JButton("Làm Mới");
		btnREFRESH.setBackground(new Color(255, 202, 58));
		btnREFRESH.addActionListener(ev);
		btnREFRESH.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_autorenew_black_18dp.png"));
		btnREFRESH.setFont(new Font("Arial", Font.PLAIN, 12));
		btnREFRESH.setBounds(233, 292, 107, 29);
		contentPane.add(btnREFRESH);
		
		JButton btnADD = new JButton("Thêm");
		btnADD.setBackground(new Color(138, 201, 38));
		btnADD.addActionListener(ev);
		btnADD.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_playlist_add_black_24dp.png"));
		btnADD.setFont(new Font("Arial", Font.PLAIN, 12));
		btnADD.setBounds(44, 292, 107, 29);
		contentPane.add(btnADD);
		
		JButton btnCLOSE = new JButton("Đóng");
		btnCLOSE.setBackground(new Color(255, 202, 58));
		btnCLOSE.addActionListener(ev);
		btnCLOSE.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_cancel_presentation_black_18dp.png"));
		btnCLOSE.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCLOSE.setBounds(414, 292, 107, 29);
		contentPane.add(btnCLOSE);
		
		clientRun();
	}
	public void addData() {
		try {
			StringBuilder error = new StringBuilder();
			if (txtID.getText().equals("") || txtNAME.getText().equals("") || txtPRICE.equals("")
					|| txtPRODUCER.getText().equals("") ) {
				error.append("Hãy Nhập Đầy Đủ Thông Tin\n");
			}
			if (!error.isEmpty()) {
				JOptionPane.showMessageDialog(this, error.toString());
				return;
			} else {
				product product=new product();
				product.setId(txtID.getText());
				product.setName(txtNAME.getText());
				product.setType((String)cbbTYPE.getSelectedItem());
				product.setSrc((String)cbbSRC.getSelectedItem());
				product.setProducer(txtPRODUCER.getText());
				product.setPrice(Integer.parseInt(txtPRICE.getText()));
				int output = JOptionPane.showConfirmDialog(rootPane, "Bạn Có Muốn Thêm Đối Tượng Này Vào Danh Sách Quản Lý Không?", "Thông Báo",
						JOptionPane.YES_NO_OPTION);
				if (output == JOptionPane.YES_OPTION) {

					boolean check=rmi.insert(product);
					if(check) {
						dispose();
						JOptionPane.showMessageDialog(this, "Thành Công !");
					}else {
						JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
		}
	}
	public void refresh() {
		txtID.setText("");
		txtNAME.setText("");
		txtPRICE.setText("");
		txtPRODUCER.setText("");
		cbbSRC.setSelectedIndex(0);
		cbbTYPE.setSelectedIndex(0);
	}
	public void close() {
		dispose();
	}
}
