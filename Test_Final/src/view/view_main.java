package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controller.event_viewmain;
import model.product;
import model.productDAO;
import model.productRMI;
import model.work_with_XML;

public class view_main extends JPanel {
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel model;
	private String[] columnHeader = { "SỐ THỨ TỰ", "ID", "TÊN SẢN PHẨM", "LOẠI", "NGUỒN", "NHÀ SẢN XUẤT",
			"GIÁ"};
	private TableRowSorter<TableModel> rowsorter = null;
	private List<product> list;
	/**
	 * Create the panel.
	 */
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
	public view_main() {
		//client
		clientRun();
		
		setBackground(Color.WHITE);
		event_viewmain ev=new event_viewmain(this);
		setLayout(null);
		setBorder(new LineBorder(new Color(67, 97, 238)));
		txtSearch = new JTextField();
		txtSearch.setText("search");
		txtSearch.setBorder(getBorder());
		txtSearch.setBounds(10, 11, 185, 20);
		add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnAdd = new JButton("Thêm Mới");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.addActionListener(ev);
		btnAdd.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_playlist_add_black_24dp.png"));
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(138, 201, 38));
		btnAdd.setBounds(10, 520, 125, 35);
		add(btnAdd);
		JTabbedPane pnData = new JTabbedPane(JTabbedPane.TOP);
		pnData.setBounds(10, 42, 713, 475);
		add(pnData);

		table = new JTable();
		initData();
		setRowsorter();
		initTable();
		table.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRowCount() == 1) {
						int row = table.getSelectedRow();
						String id = table.getValueAt(row, 1) + "";
						view_product vpd=new view_product();
						vpd.OpenViewPRD(id);

					} else if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(pnData, "Đối Tượng Không Tồn Tại Trong Danh Sách");
					} else if (table.getSelectedRowCount() != 1) {
						JOptionPane.showMessageDialog(pnData, "Hãy chọn 1 Và Chỉ 1 Đối Tượng");
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 195, 0));
		pnData.addTab("Danh Sách Sản Phẩm", null, scrollPane, null);
		scrollPane.setViewportView(table);

		JButton btnRefresh = new JButton("Làm Mới");
		btnRefresh.setForeground(new Color(0, 0, 0));
		btnRefresh.addActionListener(ev);
		btnRefresh.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_autorenew_black_18dp.png"));
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRefresh.setBackground(new Color(138, 201, 38));
		btnRefresh.setBounds(598, 4, 125, 35);
		add(btnRefresh);
		
		JButton btnReadXML = new JButton("Đọc File");
		btnReadXML.addActionListener(ev);
		btnReadXML.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_edit_note_black_18dp.png"));
		btnReadXML.setForeground(Color.BLACK);
		btnReadXML.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReadXML.setBackground(new Color(138, 201, 38));
		btnReadXML.setBounds(210, 520, 125, 35);
		add(btnReadXML);
		
		JButton btnReadOnDB = new JButton("Ghi Vào CSDL");
		btnReadOnDB.addActionListener(ev);
		btnReadOnDB.setForeground(Color.BLACK);
		btnReadOnDB.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReadOnDB.setBackground(new Color(138, 201, 38));
		btnReadOnDB.setBounds(598, 520, 125, 35);
		add(btnReadOnDB);
		
		JButton btnPrint = new JButton("Xuất File Excel");
		btnPrint.addActionListener(ev);
		btnPrint.setForeground(Color.BLACK);
		btnPrint.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPrint.setBackground(new Color(138, 201, 38));
		btnPrint.setBounds(407, 520, 125, 35);
		add(btnPrint);
	}
	public void initData() {
		try {
			list = new productDAO().getData();
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
		for (product item : list) {
			model.addRow(new Object[] { stt++, item.getId(), item.getName(), item.getType(), item.getSrc(),
					item.getProducer(), item.getPrice()});
		}
		model.fireTableDataChanged();
		table.setModel(model);
		setRowsorter();
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

	public void refresh() {
		initData();
		txtSearch.setText("search");
		setRowsorter();
		initTable();
	}

	public void addData() {
		new view_addproduct().setVisible(true);
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
	public void writeOnDB() {
		try {
			work_with_XML.writeonDB();
			JOptionPane.showMessageDialog(this,"Đã Ghi !");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,"Đã Xảy Ra Lỗi !");
		}
	}
	public void printDocument() {
		try {
			XSSFWorkbook Workbook = new XSSFWorkbook();
			XSSFSheet sheet = Workbook.createSheet("Sản Phẩm");

			XSSFRow row = null;
			Cell cell = null;

			row = sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("ID");

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Tên Sản Phẩm");

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Loại");

			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Nguồn Gốc");

			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Nhà Sản Xuất");

			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Giá");

			this.initData();
			List<product> listItem = list;

			if (listItem != null) {
				int s = listItem.size();
				for (int i = 0; i < s; i++) {
					product product = listItem.get(i);

					row = sheet.createRow(4 + i);

					cell = row.createCell(0, CellType.NUMERIC);
					cell.setCellValue(i + 1);

					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue(product.getId());

					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue(product.getName());

					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue(product.getType());

					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(product.getSrc());

					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue(product.getProducer());

					cell = row.createCell(6, CellType.NUMERIC);
					cell.setCellValue(product.getPrice());


				}
				JFileChooser jf = new JFileChooser();

				int chose = jf.showSaveDialog(this);
				if (chose == JFileChooser.APPROVE_OPTION) {
					File file = jf.getSelectedFile();
					FileOutputStream fileStream = new FileOutputStream(file + ".xlsx");
					Workbook.write(fileStream);
					fileStream.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		}
	}
}
