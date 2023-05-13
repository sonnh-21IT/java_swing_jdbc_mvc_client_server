package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class accountDAO extends UnicastRemoteObject implements accountRMI{

	public accountDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public account checkData(account acc) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM accountTC WHERE username=? AND  password=?";
		try (Connection conn = DBConnect.openConnection(); PreparedStatement ptsm = conn.prepareStatement(sql);) {
			ptsm.setString(1, acc.getUser());
			ptsm.setString(2, acc.getPass());
			ResultSet rs = ptsm.executeQuery();
			if (rs.next()) {
				account accreal = new account();
				accreal.setUser(rs.getString("username"));
				accreal.setPass(rs.getString("password"));
				
				return accreal;
			}
		}
		return null;
	}

	@Override
	public boolean addAccount(account acc) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into accountTC(username,password) values (?,?)";
		try (Connection conn = DBConnect.openConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)
						) {
			ps.setString(1, acc.getUser());
			ps.setString(2,acc.getPass());
			
			return ps.executeUpdate()>0;
		}
	}
}
