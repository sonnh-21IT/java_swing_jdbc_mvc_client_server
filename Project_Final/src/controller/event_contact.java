package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_contact;

public class event_contact implements ActionListener{
	private view_contact vct;
	
	public event_contact(view_contact vct) {
		this.vct = vct;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==vct.getBtnSendAdmin()) {
			String sms=vct.getTxtMessageAdmin().getText();
			if(!sms.equals("")) {
				vct.Send("Admin : "+sms);
				vct.getTxtMessageAdmin().setText("");
			}
		}else if(e.getSource()==vct.getBtnSendStaff()) {
			String sms=vct.getTxtMessageStaff().getText();
			if(!sms.equals("")) {
				vct.Send("Staff : "+sms);
				vct.getTxtMessageStaff().setText("");
			}
		}
	}

}
