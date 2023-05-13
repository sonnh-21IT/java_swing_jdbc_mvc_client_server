package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_manager;

public class event_viewmanager implements ActionListener{
	private view_manager vmn;
	
	public event_viewmanager(view_manager vmn) {
		this.vmn = vmn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src=e.getActionCommand();
		if(src.equalsIgnoreCase("Màn Hình Chính")) {
			vmn.closeChat();
			vmn.viewHome();
		}else if(src.equalsIgnoreCase("Quản Lý Sản Phẩm")) {
			vmn.closeChat();
			vmn.viewManeger();
		}else if(src.equalsIgnoreCase("Đóng")) {
			vmn.closeChat();
			vmn.viewLogin();
		}else if(src.equalsIgnoreCase("Liên Hệ Admin")) {
			vmn.closeChat();
			vmn.viewContatc();
		}
	}
	
}
