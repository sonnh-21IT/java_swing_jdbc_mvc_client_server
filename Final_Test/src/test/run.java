package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.accountDAO;
import model.bikeDAO;
import view.view_admin;
import view.view_login;
import view.view_staff;

public class run {
	public static void main(String[] args) {
		serverRun();
		view_admin vad=new view_admin();
		view_staff vst=new view_staff();
		vad.listen();
		vst.connect();
		new view_login(vad,vst).setVisible(true);
	}
	public static void serverRun() {
		try {
			Registry reg=LocateRegistry.createRegistry(2609);
			reg.rebind("serverLogin",new accountDAO());
			reg.rebind("serverBike",new bikeDAO());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
