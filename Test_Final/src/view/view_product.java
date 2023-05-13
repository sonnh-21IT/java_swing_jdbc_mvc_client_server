package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.event_viewproduct;
import model.product;
import model.productDAO;
import model.productRMI;
import model.work_with_XML;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class view_product extends JFrame {

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
					view_product frame = new view_product();
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
	public view_product() {
		//client
		clientRun();
		
		event_viewproduct ev = new event_viewproduct(this);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 572, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("XEM SẢN PHẨM");
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
		
		JButton btnDELETE = new JButton("Xóa");
		btnDELETE.setBackground(new Color(236, 70, 70));
		btnDELETE.addActionListener(ev);
		btnDELETE.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_cancel_presentation_black_18dp.png"));
		btnDELETE.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDELETE.setBounds(40, 292, 107, 29);
		contentPane.add(btnDELETE);
		
		JButton btnREFRESH = new JButton("Đóng");
		btnREFRESH.setBackground(new Color(255, 202, 58));
		btnREFRESH.addActionListener(ev);
		btnREFRESH.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_disabled_by_default_black_18dp.png"));
		btnREFRESH.setFont(new Font("Arial", Font.PLAIN, 12));
		btnREFRESH.setBounds(414, 292, 107, 29);
		contentPane.add(btnREFRESH);
		
		JButton btnUPDATE = new JButton("Sửa");
		btnUPDATE.setBackground(new Color(138, 201, 38));
		btnUPDATE.addActionListener(ev);
		btnUPDATE.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_edit_note_black_18dp.png"));
		btnUPDATE.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUPDATE.setBounds(233, 292, 107, 29);
		contentPane.add(btnUPDATE);
	}
	public void OpenViewPRD(String id) {
		try {
			product product_on_db = rmi.Search(id);
			product product_on_xml=work_with_XML.search(id);
			product product=null;
			
			if ((product_on_db != null && product_on_xml==null)||(product_on_db != null && product_on_xml!=null)) {
				product=product_on_db;
			}else if (product_on_db == null && product_on_xml!=null) {
				product =product_on_xml;
			}
			if(product!=null) {
				display_product(product);
				setVisible(true);
				return;
			}else {
				JOptionPane.showMessageDialog(this, "Sai Thông Tin Hoặc Đối Tượng Không Tồn Tại Trong Danh Sách");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void display_product(product product) {
		txtID.setText(product.getId());
		txtNAME.setText(product.getName());
		txtPRODUCER.setText(product.getProducer());
		txtPRICE.setText(product.getPrice()+"");
		
		if (product.getType().equalsIgnoreCase("Khác")) {
			cbbTYPE.setSelectedIndex(0);
		}
		if (product.getType().equalsIgnoreCase("Nội Thất")) {
			cbbTYPE.setSelectedIndex(1);
		}
		if (product.getType().equalsIgnoreCase("Đồ Dùng Học Tập")) {
			cbbTYPE.setSelectedIndex(2);
		}
		if (product.getType().equalsIgnoreCase("Dụng Cụ Sửa Chữa")) {
			cbbTYPE.setSelectedIndex(3);
		}
		
		if (product.getSrc().equalsIgnoreCase("Khác")) {
			cbbSRC.setSelectedIndex(0);
		}
		if (product.getSrc().equalsIgnoreCase("Trong Nước")) {
			cbbSRC.setSelectedIndex(1);
		}
		if (product.getSrc().equalsIgnoreCase("Nước Ngoài")) {
			cbbSRC.setSelectedIndex(2);
		}
	}
	public void closeViewPRD() {
		dispose();
	}
	public void deletePRD() {
		try {
			String id=txtID.getText();
			StringBuilder error = new StringBuilder();
			if (id.equals("")) {
				error.append("ID Sản Phẩm Không Được Để Trống");
			}
			if (!error.isEmpty()) {
				JOptionPane.showMessageDialog(this, error.toString());
				return;
			} else {
				int output = JOptionPane.showConfirmDialog(rootPane, "Bạn Có Muốn Xoá Đối Tượng Này Không?",
						"Thông Báo", JOptionPane.YES_NO_OPTION);
				if (output == JOptionPane.YES_OPTION) {
					boolean check=rmi.delete(id);
					if(check) {
						dispose();
						JOptionPane.showMessageDialog(this, "Đã Xoá !");
					}else {
						JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
		}
	}
	public void upDatePRD() {
		try {
			String id=txtID.getText();
			StringBuilder error = new StringBuilder();
			if (txtID.getText().equals("")||txtNAME.getText().equals("")||txtPRICE.getText().equals("")||txtPRODUCER.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Hãy Nhập Đầy Đủ Thông Tin\\n");
				return;
			} else {
				product product = new product();
				product.setId(txtID.getText());
				product.setName(txtNAME.getText());
				product.setPrice(Integer.parseInt(txtPRICE.getText()));
				product.setProducer(txtPRODUCER.getText());
				product.setType((String)cbbTYPE.getSelectedItem());
				product.setSrc((String)cbbSRC.getSelectedItem());

				boolean check=rmi.update(product);
				if(check) {
					dispose();
					JOptionPane.showMessageDialog(this, "Hoàn Tất !");
				}else {
					JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
		}
	}
}
