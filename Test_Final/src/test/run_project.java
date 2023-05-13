package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.accountDAO;
import model.productDAO;
import view.view_contact;
import view.view_login;
import view.view_staff;

public class run_project {
	public static void main(String[] args ){
		run();
	}
	public static void serverRun() {
		try {
			Registry reg=LocateRegistry.createRegistry(2706);
			reg.rebind("QLSANPHAM_Product",new productDAO());
			reg.rebind("QLSANPHAM_Account",new accountDAO());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void run() {
		serverRun();
		view_staff vst=new view_staff();
		view_contact vct=new view_contact(vst);
		vct.listen();
		vst.connect();
		new view_login(vct,vst).setVisible(true);
	}
}
