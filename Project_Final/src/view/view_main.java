package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.event_viewmain;
import model.fruit;
import model.fruitDAO;
import model.fruitRMI;
import model.work_with_XML;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class view_main extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtNum;
	private JTextField txtSRC;
	private JTextField txtPrice;
	private JTextField txtprovided;
	private static JPanel pnManager;
	private JPanel pnRoot;
	private DefaultTableModel model;
	private String[] columnHeader = { "SỐ THỨ TỰ", "ID", "TÊN TRÁI CÂY", "SỐ LƯỢNG", "XUẤT XỨ", "GIÁ",
	"NHÀ CUNG CẤP"};
	private TableRowSorter<TableModel> rowsorter = null;
	private List<fruit> list;
	
	private Registry reg;
	private fruitRMI rmi;
	public void clientRun() {
		try {
			reg=LocateRegistry.getRegistry(2407);
			rmi=(fruitRMI) reg.lookup("serverTC");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private view_contact vct;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_main frame = new view_main();
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
	public view_main() {
		//client
		clientRun();
		vct=new view_contact();
		vct.listen();
		vct.connect();
		
		event_viewmain ev=new event_viewmain(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(ev);
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdd.setBounds(10, 355, 100, 30);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(ev);
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUpdate.setBounds(148, 355, 100, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(ev);
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setBounds(277, 355, 100, 30);
		contentPane.add(btnDelete);
		
		JButton btnRead = new JButton("Đọc XML");
		btnRead.addActionListener(ev);
		btnRead.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRead.setBounds(416, 355, 100, 30);
		contentPane.add(btnRead);
		
		JButton btnRefresh = new JButton("Làm Mới");
		btnRefresh.addActionListener(ev);
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRefresh.setBounds(710, 8, 100, 30);
		contentPane.add(btnRefresh);
		
		JButton btnContact = new JButton("Chat");
		btnContact.addActionListener(ev);
		btnContact.setFont(new Font("Arial", Font.PLAIN, 12));
		btnContact.setBounds(565, 355, 100, 30);
		contentPane.add(btnContact);
		
		JButton btnBack = new JButton("Quay Lại");
		btnBack.addActionListener(ev);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBack.setBounds(710, 355, 100, 30);
		contentPane.add(btnBack);
		
		txtSearch = new JTextField();
		txtSearch.setText("search");
		txtSearch.setBounds(10, 11, 150, 25);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		pnRoot = new JPanel();
		pnRoot.setBounds(10, 47, 810, 292);
		pnRoot.setLayout(null);
		
		pnManager = new JPanel();
		pnManager.setBounds(0, 0, 800, 290);
		pnManager.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 0, 457, 290);
		pnManager.add(scrollPane);
		
		table = new JTable();
		this.initData();
		setRowsorter();
		initTable();
		table.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRowCount() == 1) {
						int row = table.getSelectedRow();
						String id = table.getValueAt(row, 1) + "";
						try {
							fruit fruit_on_db = rmi.Search(id);
							fruit fruit_on_xml=work_with_XML.search(id);
							if ((fruit_on_db != null&& fruit_on_xml==null)||(fruit_on_db != null&& fruit_on_xml!=null)) {
								txtID.setText(fruit_on_db.getId());
								txtName.setText(fruit_on_db.getName());
								txtNum.setText(fruit_on_db.getNum()+"");
								txtSRC.setText(fruit_on_db.getSrc());
								txtPrice.setText(fruit_on_db.getPrice()+"");
								txtprovided.setText(fruit_on_db.getProvided());
							}else if (fruit_on_db == null && fruit_on_xml!=null) {
								txtID.setText(fruit_on_xml.getId());
								txtName.setText(fruit_on_xml.getName());
								txtNum.setText(fruit_on_xml.getNum()+"");
								txtSRC.setText(fruit_on_xml.getSrc());
								txtPrice.setText(fruit_on_xml.getPrice()+"");
								txtprovided.setText(fruit_on_xml.getProvided());
							}else {
								JOptionPane.showMessageDialog( pnManager,"Sai Thông Tin Hoặc Đối Tượng Không Tồn Tại Trong Danh Sách");
							}
						} catch (Exception exeption) {
							// TODO: handle exception
							exeption.printStackTrace();
						}

					} else if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(pnManager, "Đối Tượng Không Tồn Tại Trong Danh Sách");
					} else if (table.getSelectedRowCount() != 1) {
						JOptionPane.showMessageDialog(pnManager, "Hãy chọn 1 Và Chỉ 1 Đối Tượng");
					}
				} catch (Exception E) {
//					e1.printStackTrace();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		scrollPane.setViewportView(table);
		
		txtID = new JTextField();
		txtID.setBounds(93, 27, 86, 20);
		pnManager.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 29, 46, 17);
		pnManager.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(93, 58, 171, 20);
		pnManager.add(txtName);
		
		JLabel lblTn = new JLabel("Tên");
		lblTn.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTn.setBounds(10, 60, 46, 17);
		pnManager.add(lblTn);
		
		txtNum = new JTextField();
		txtNum.setColumns(10);
		txtNum.setBounds(93, 89, 114, 20);
		pnManager.add(txtNum);
		
		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSLng.setBounds(10, 91, 73, 17);
		pnManager.add(lblSLng);
		
		txtSRC = new JTextField();
		txtSRC.setColumns(10);
		txtSRC.setBounds(93, 126, 240, 20);
		pnManager.add(txtSRC);
		
		JLabel lblXutX = new JLabel("Xuất Xứ");
		lblXutX.setFont(new Font("Arial", Font.PLAIN, 12));
		lblXutX.setBounds(10, 128, 46, 17);
		pnManager.add(lblXutX);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(93, 169, 152, 20);
		pnManager.add(txtPrice);
		
		JLabel lblNhCungCp = new JLabel("Cung Cấp");
		lblNhCungCp.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNhCungCp.setBounds(10, 212, 82, 17);
		pnManager.add(lblNhCungCp);
		
		txtprovided = new JTextField();
		txtprovided.setColumns(10);
		txtprovided.setBounds(93, 210, 213, 20);
		pnManager.add(txtprovided);
		
		JLabel lblGi = new JLabel("Giá");
		lblGi.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGi.setBounds(10, 171, 46, 17);
		pnManager.add(lblGi);
		
		pnRoot.add(pnManager);
		contentPane.add(pnRoot);
	}
	public void viewmanager() {
		FromView(view_main.pnManager);
	}
	public void viewcontact() {
		FromView(vct);
	}
	public void FromView(JPanel pnnew) {
		pnRoot.removeAll();
		pnRoot.add(pnnew);
		pnnew.setBounds(0, 0, 800, 290);
		pnRoot.validate();
		pnRoot.repaint();
	}
	public void setRowsorter() {
		rowsorter = new TableRowSorter<>(table.getModel());
		
		table.setRowSorter(rowsorter);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String text = txtSearch.getText();
				if (text.trim().length() == 0) {
					rowsorter.setRowFilter(null);
				} else {
					rowsorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String text = txtSearch.getText();
				if (text.trim().length() == 0) {
					rowsorter.setRowFilter(null);
				} else {
					rowsorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

		});

		table.getTableHeader().setFont(new Font("Arrial", Font.PLAIN, 12));
		table.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table.setRowHeight(25);
	}
	public void initData() {
		try {
			list = new fruitDAO().getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initTable() {
		model = new DefaultTableModel();
		model.setRowCount(0);
		model.setColumnIdentifiers(columnHeader);
		int stt = 1;
		for (fruit item : list) {
			model.addRow(new Object[] { stt++, item.getId(), item.getName(), item.getNum(), item.getSrc(),
					item.getPrice(), item.getProvided()});
		}
		model.fireTableDataChanged();
		table.setModel(model);
		setRowsorter();
	}
	public void refresh() {
		txtID.setText("");
		txtName.setText("");
		txtNum.setText("");
		txtPrice.setText("");
		txtprovided.setText("");
		txtSRC.setText("");
		txtSearch.setText("search");
		initData();
		setRowsorter();
		initTable();
	}
	public void readFile() {
		try {
			list= work_with_XML.readXML();
//			initData();
			txtSearch.setText("search");
			setRowsorter();
			initTable();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void addData() {
		try {
			StringBuilder error = new StringBuilder();
			if (txtID.getText().equals("") || txtName.getText().equals("") || txtNum.equals("")
					|| txtPrice.getText().equals("")||txtprovided.getText().equalsIgnoreCase("")||txtSRC.getText().equalsIgnoreCase("") ) {
				error.append("Hãy Nhập Đầy Đủ Thông Tin\n");
			}
			if (!error.isEmpty()) {
				JOptionPane.showMessageDialog(this, error.toString());
				return;
			} else {
				fruit fruit=new fruit();
				fruit.setId(txtID.getText());
				fruit.setName(txtName.getText());
				fruit.setNum(Integer.parseInt(txtNum.getText()));
				fruit.setPrice(Integer.parseInt(txtPrice.getText()));
				fruit.setProvided(txtprovided.getText());
				fruit.setSrc(txtSRC.getText());
				int output = JOptionPane.showConfirmDialog(rootPane, "Bạn Có Muốn Thêm Đối Tượng Này Vào Danh Sách Quản Lý Không?", "Thông Báo",
						JOptionPane.YES_NO_OPTION);
				if (output == JOptionPane.YES_OPTION) {

					boolean check=rmi.insert(fruit);
					if(check) {
						JOptionPane.showMessageDialog(this, "Thành Công !");
						refresh();
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
	public void upDatePRD() {
		try {
			String id=txtID.getText();
			StringBuilder error = new StringBuilder();
			if (txtID.getText().equals("") || txtName.getText().equals("") || txtNum.equals("")
					|| txtPrice.getText().equals("")||txtprovided.getText().equalsIgnoreCase("")||txtSRC.getText().equalsIgnoreCase("") ) {
				error.append("Hãy Nhập Đầy Đủ Thông Tin\n");
			} else {
				fruit fruit=new fruit();
				fruit.setId(txtID.getText());
				fruit.setName(txtName.getText());
				fruit.setNum(Integer.parseInt(txtNum.getText()));
				fruit.setPrice(Integer.parseInt(txtPrice.getText()));
				fruit.setProvided(txtprovided.getText());
				fruit.setSrc(txtSRC.getText());

				boolean check=rmi.update(fruit);
				if(check) {
					JOptionPane.showMessageDialog(this, "Hoàn Tất !");
					refresh();
				}else {
					JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
		}
	}
	public void deletePRD() {
		try {
			String id=txtID.getText();
			StringBuilder error = new StringBuilder();
			if (id.equals("")) {
				error.append("ID Không Được Để Trống");
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
						JOptionPane.showMessageDialog(this, "Đã Xoá !");
						refresh();
					}else {
						JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Đã Xảy Ra Lỗi !");
		}
	}
}
