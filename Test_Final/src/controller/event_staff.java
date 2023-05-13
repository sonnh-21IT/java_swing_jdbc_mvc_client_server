package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.view_staff;

public class event_staff implements ActionListener{
	private view_staff vs;
	
	public event_staff(view_staff vs) {
		this.vs = vs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==vs.getBtnSendStaff()) {
			String sms=vs.getTxtMessagerStaff().getText();
			if(!sms.equals("")) {
				vs.Send("Staff : "+sms);
				vs.getTxtMessagerStaff().setText("");
			}
		}
	}

}
