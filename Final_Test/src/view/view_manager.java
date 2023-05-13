package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.bike;
import model.bikeDAO;
import model.bikeRMI;
import model.work_with_XML;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class view_manager extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel model;
	private String[] columnHeader = { "SỐ THỨ TỰ", "ID", "NGƯỜI MƯỢN", "TUỔI", "LIÊN HỆ", "THỜI GIAN",
	"THUỘC TÍNH"};
	private TableRowSorter<TableModel> rowsorter = null;
	private List<bike> list;
	
	private Registry reg;
	private bikeRMI rmi;
	private JTextField txtId;
	private JTextField txtNguoimuon;
	private JTextField txtTuoi;
	private JTextField txtLienhe;
	private JTextField txtThoigian;
	private JComboBox cbbThuoctinh;
	private view_admin vad;
	private view_staff vst;
	public void clientRun() {
		try {
			reg=LocateRegistry.getRegistry(2609);
			rmi=(bikeRMI) reg.lookup("serverBike");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					view_manager frame = new view_manager();
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
	public view_manager(view_admin vad,view_staff vst) {
		this.vad=vad;
		this.vst=vst;
		clientRun();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 344, 743, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData();
			}
		});
		btnThem.setBounds(10, 11, 104, 36);
		panel.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnSua.setBounds(149, 11, 104, 36);
		panel.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnXoa.setBounds(297, 11, 104, 36);
		panel.add(btnXoa);
		
		JButton btnDoc = new JButton("Đọc XML");
		btnDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readXML();
			}
		});
		btnDoc.setBounds(457, 11, 104, 36);
		panel.add(btnDoc);
		
		JButton btnLienhe = new JButton("Liên Hệ");
		btnLienhe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vad.setVisible(true);
				vst.setVisible(true);
			}
		});
		btnLienhe.setBounds(629, 11, 104, 36);
		panel.add(btnLienhe);
		
		JPanel pnMain = new JPanel();
		pnMain.setBounds(0, 38, 763, 295);
		contentPane.add(pnMain);
		pnMain.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 0, 464, 295);
		pnMain.add(scrollPane);
		
		txtSearch = new JTextField();
		txtSearch.setText("search");
		txtSearch.setBounds(10, 11, 131, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		table = new JTable();
		initData();
		initTable();
		table.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRowCount() == 1) {
						int row = table.getSelectedRow();
						String id = table.getValueAt(row, 1) + "";
						try {
							bike bike_on_db=rmi.Search(id);
							bike bike_on_xml=work_with_XML.search(id);
							bike bike = null;
							if((bike_on_db!=null&&bike_on_xml==null)||(bike_on_db!=null&&bike_on_xml!=null)) {
								bike=bike_on_db;
							}else if(bike_on_xml!=null&&bike_on_db==null) {
								bike=bike_on_xml;
							}
							if (bike!=null) {
								txtId.setText(bike.getId());
								txtNguoimuon.setText(bike.getNguoimuon());
								txtLienhe.setText(bike.getLienhe());
								txtTuoi.setText(bike.getTuoi()+"");
								txtThoigian.setText(bike.getThoigian());
								String thuoctinh=bike.getThuoctinh();
								if(thuoctinh.equalsIgnoreCase("Khác")) {
									cbbThuoctinh.setSelectedIndex(0);
								}
								if(thuoctinh.equalsIgnoreCase("Xe Ga")) {
									cbbThuoctinh.setSelectedIndex(1);
								}
								if(thuoctinh.equalsIgnoreCase("Xe Số")) {
									cbbThuoctinh.setSelectedIndex(2);
								}
							}else {
								JOptionPane.showMessageDialog(pnMain ,"Sai Thông Tin Hoặc Đối Tượng Không Tồn Tại Trong Danh Sách");
							}
						} catch (Exception exeption) {
							// TODO: handle exception
							exeption.printStackTrace();
						}

					} else if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(pnMain, "Đối Tượng Không Tồn Tại Trong Danh Sách");
					} else if (table.getSelectedRowCount() != 1) {
						JOptionPane.showMessageDialog(pnMain, "Hãy chọn 1 Và Chỉ 1 Đối Tượng");
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
		
		txtId = new JTextField();
		txtId.setBounds(92, 30, 86, 20);
		pnMain.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 30, 53, 20);
		pnMain.add(lblNewLabel);
		
		JLabel lblNgiMn = new JLabel("Người Mượn");
		lblNgiMn.setBounds(10, 72, 72, 20);
		pnMain.add(lblNgiMn);
		
		txtNguoimuon = new JTextField();
		txtNguoimuon.setColumns(10);
		txtNguoimuon.setBounds(92, 72, 197, 20);
		pnMain.add(txtNguoimuon);
		
		JLabel lblTui = new JLabel("Tuổi");
		lblTui.setBounds(10, 110, 72, 20);
		pnMain.add(lblTui);
		
		txtTuoi = new JTextField();
		txtTuoi.setColumns(10);
		txtTuoi.setBounds(92, 110, 119, 20);
		pnMain.add(txtTuoi);
		
		JLabel lblLinH = new JLabel("Liên Hệ");
		lblLinH.setBounds(10, 149, 72, 20);
		pnMain.add(lblLinH);
		
		txtLienhe = new JTextField();
		txtLienhe.setColumns(10);
		txtLienhe.setBounds(92, 149, 147, 20);
		pnMain.add(txtLienhe);
		
		JLabel lblThiGian = new JLabel("Thời Gian");
		lblThiGian.setBounds(10, 191, 72, 20);
		pnMain.add(lblThiGian);
		
		txtThoigian = new JTextField();
		txtThoigian.setColumns(10);
		txtThoigian.setBounds(92, 191, 171, 20);
		pnMain.add(txtThoigian);
		
		JLabel lblThucTnh = new JLabel("Thuộc Tính");
		lblThucTnh.setBounds(10, 232, 72, 20);
		pnMain.add(lblThucTnh);
		
		cbbThuoctinh = new JComboBox();
		cbbThuoctinh.setModel(new DefaultComboBoxModel(new String[] {"Khác", "Xe Ga", "Xe Số"}));
		cbbThuoctinh.setBounds(92, 231, 119, 22);
		pnMain.add(cbbThuoctinh);
		
		setRowsorter();
		JButton btnLammoi = new JButton("Làm Mới");
		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnLammoi.setBounds(664, 10, 89, 23);
		contentPane.add(btnLammoi);
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
			list = new bikeDAO().getData();
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
		for (bike item : list) {
			model.addRow(new Object[] { stt++, item.getId(), item.getNguoimuon(), item.getTuoi(), item.getLienhe(),
					item.getThoigian(), item.getThuoctinh()});
		}
		model.fireTableDataChanged();
		table.setModel(model);
		setRowsorter();
	}
	public void addData() {
		try {
			if(txtId.getText().equals("")||txtLienhe.getText().equals("")||txtNguoimuon.equals("")||txtThoigian.getText().equals("")||txtTuoi.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin");
			}else if(Integer.parseInt(txtTuoi.getText())<18) {
				JOptionPane.showMessageDialog(this,"Tuổi phải từ 18 tuổi trở lên ");
			}else {
				bike bike=new bike();
				bike.setId(txtId.getText());
				bike.setNguoimuon(txtNguoimuon.getText());
				bike.setTuoi(Integer.parseInt(txtTuoi.getText()));
				bike.setLienhe(txtLienhe.getText());
				bike.setThoigian(txtThoigian.getText());
				bike.setThuoctinh((String)cbbThuoctinh.getSelectedItem());
				if(rmi.insert(bike)) {
					JOptionPane.showMessageDialog(this,"Thành Công");
				}else {
					JOptionPane.showMessageDialog(this,"Đã xảy ra lỗi");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void readXML() {
		try {
			list=work_with_XML.readXML();
			initTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update() {
		try {
			if(txtId.getText().equals("")||txtLienhe.getText().equals("")||txtNguoimuon.equals("")||txtThoigian.getText().equals("")||txtTuoi.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin");
			}else if(Integer.parseInt(txtTuoi.getText())<18) {
				JOptionPane.showMessageDialog(this,"Tuổi phải từ 18 tuổi trở lên ");
			}else {
				bike bike=new bike();
				bike.setId(txtId.getText());
				bike.setNguoimuon(txtNguoimuon.getText());
				bike.setTuoi(Integer.parseInt(txtTuoi.getText()));
				bike.setLienhe(txtLienhe.getText());
				bike.setThoigian(txtThoigian.getText());
				bike.setThuoctinh((String)cbbThuoctinh.getSelectedItem());
				if(rmi.update(bike)) {
					JOptionPane.showMessageDialog(this,"Thành Công");
				}else {
					JOptionPane.showMessageDialog(this,"Đã xảy ra lỗi");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void delete() {
		try {
			if(txtId.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"ID không đc để trống");
			}else {
				if(rmi.delete(txtId.getText())) {
					JOptionPane.showMessageDialog(this,"Thành Công");
				}else {
					JOptionPane.showMessageDialog(this,"Đã Xảy ra lỗi");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void refresh() {
		txtSearch.setText("search");
		txtId.setText("");
		txtLienhe.setText("");
		txtNguoimuon.setText("");
		txtThoigian.setText("");
		txtTuoi.setText("");
		cbbThuoctinh.setSelectedIndex(0);
		initData();
		initTable();
		setRowsorter();
	}
}
