package view;

import java.awt.Color;
import java.awt.Font;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.accountDAO;
import model.productDAO;
import model.productRMI;


public class view_home extends JPanel {
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
	 * Create the panel.
	 */
	public view_home() {
		//client
		clientRun();
		
		setBorder(new LineBorder(new Color(67, 97, 238)));
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(100, 111, 212));
		panel_3.setBounds(10, 11, 713, 150);
		add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\outline_ballot_white_24dp.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(0, 0, 100, 150);
		panel_3.add(lblNewLabel_2);

		JLabel lblNewLabel_1_2;
		try {
			lblNewLabel_1_2 = new JLabel(new productDAO().CountType()+"");
			lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_2.setForeground(Color.WHITE);
			lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1_2.setBounds(100, 0, 100, 75);
			panel_3.add(lblNewLabel_1_2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblNewLabel_1_1_1 = new JLabel("Chủng Loại");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(100, 75, 100, 75);
		panel_3.add(lblNewLabel_1_1_1);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(new Color(255, 146, 139));
		panel_3_1.setBounds(10, 178, 713, 150);
		add(panel_3_1);
		panel_3_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\84984\\Documents\\CODE__Java\\Test_Final\\icon_image\\approval_FILL0_wght300_GRAD0_opsz40.png"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(513, 0, 100, 150);
		panel_3_1.add(lblNewLabel_3);

		JLabel lblNewLabel_1_2_1;
		try {
			lblNewLabel_1_2_1 = new JLabel(rmi.CountSrc()+"");
			lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_2_1.setForeground(Color.BLACK);
			lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1_2_1.setBounds(613, 0, 100, 75);
			panel_3_1.add(lblNewLabel_1_2_1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nguồn Gốc");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(613, 75, 100, 75);
		panel_3_1.add(lblNewLabel_1_1_1_1);
	}

}
