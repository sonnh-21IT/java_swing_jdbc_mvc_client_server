package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_product;

public class event_viewproduct implements ActionListener{
	private view_product vprd;
	
	public event_viewproduct(view_product vprd) {
		this.vprd = vprd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src=e.getActionCommand();
		if(src.equalsIgnoreCase("Xóa")) {
			vprd.deletePRD();
		}else if(src.equalsIgnoreCase("Đóng")) {
			vprd.closeViewPRD();
		}else if(src.equalsIgnoreCase("Sửa")) {
			vprd.upDatePRD();
		}
	}

}
