package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_main;

public class event_viewmain implements ActionListener{
	private view_main vm;
	
	public event_viewmain(view_main vm) {
		this.vm = vm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src=e.getActionCommand();
		if(src.equalsIgnoreCase("Thêm Mới")) {
			vm.addData();
		}else if(src.equalsIgnoreCase("Làm Mới")) {
			vm.refresh();
		}else if(src.equalsIgnoreCase("Đọc File")) {
			vm.readFile();
		}else if(src.equalsIgnoreCase("Ghi Vào CSDL")) {
			vm.writeOnDB();
		}else if(src.equalsIgnoreCase("Xuất File Excel")) {
			vm.printDocument();
		}
	}
	
}
