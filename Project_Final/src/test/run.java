package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.accountDAO;
import model.fruitDAO;
import view.view_contact;
import view.view_login;
import view.view_main;

public class run {
	public static void main(String[] args) {
		serverFruitRun();
		serverRun();
		new view_login().setVisible(true);
//		new view_main().setVisible(true);
	}
	public static void serverFruitRun() {
		try {
			Registry reg=LocateRegistry.createRegistry(2407);
			reg.rebind("serverTC",new fruitDAO());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void serverRun() {
		try {
			Registry reg=LocateRegistry.createRegistry(2706);
			reg.rebind("server",new accountDAO());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
