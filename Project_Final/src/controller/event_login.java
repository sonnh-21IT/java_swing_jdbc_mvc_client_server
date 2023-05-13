package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_login;

public class event_login implements ActionListener{
	private view_login vlg;
	
	public event_login(view_login vlg) {
		this.vlg = vlg;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src=e.getActionCommand();
		if(src.equalsIgnoreCase("Đăng Ký")) {
			vlg.signUp();
		}else if(src.equalsIgnoreCase("Đăng Nhập")) {
			vlg.checkAccount();
		}
	}

}
