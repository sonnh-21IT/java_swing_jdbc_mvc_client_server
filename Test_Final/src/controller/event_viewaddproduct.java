package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_addproduct;

public class event_viewaddproduct implements ActionListener{
	private view_addproduct vadd;
	
	public event_viewaddproduct(view_addproduct vadd) {
		this.vadd = vadd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src=e.getActionCommand();
		if(src.equalsIgnoreCase("Thêm")) {
			vadd.addData();
		}else if(src.equalsIgnoreCase("Làm Mới")) {
			vadd.refresh();
		}else if(src.equalsIgnoreCase("Đóng")) {
			vadd.close();
		}
	}

}
