package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_signup;

public class event_signup implements ActionListener{
	private view_signup vsu;
	
	public event_signup(view_signup vsu) {
		this.vsu = vsu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src=e.getActionCommand();
		if(src.equalsIgnoreCase("Đăng Nhập")) {
			vsu.login();
		}else if(src.equalsIgnoreCase("Đăng Ký")) {
			vsu.signup();
		}
	}

}
