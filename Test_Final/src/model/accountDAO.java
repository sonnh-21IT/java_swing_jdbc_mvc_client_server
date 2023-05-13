package model;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class accountDAO extends UnicastRemoteObject implements accountRMI{
	public accountDAO() throws RemoteException {
		super();
	}

	public account checkData(account acc) throws Exception {
		String sql = "SELECT * FROM account WHERE username=? AND  password=?";
		try (Connection conn = DBConnection.openConnection(); PreparedStatement ptsm = conn.prepareStatement(sql);) {
			ptsm.setString(1, acc.getUser());
			ptsm.setString(2, acc.getPass());
			ResultSet rs = ptsm.executeQuery();
			if (rs.next()) {
				account accreal = new account();
				accreal.setUser(rs.getString("username"));
				accreal.setPass(rs.getString("password"));
				accreal.setName(rs.getString("name"));
				
				return accreal;
			}
			return null;
		}
	}

	public boolean addAccount(account acc) throws Exception {
		String sql = "insert into account(username,password,name) values (?,?,?)";
		try (Connection conn = DBConnection.openConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)
						) {
			ps.setString(1, acc.getUser());
			ps.setString(2,acc.getPass());
			ps.setString(3,acc.getName());
			
			return ps.executeUpdate()>0;
		}
	}
}
