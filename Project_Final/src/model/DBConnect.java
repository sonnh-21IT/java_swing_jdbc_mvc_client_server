package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection openConnection() throws Exception {
		Connection connection=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://localhost:1433;databaseName=QLSANPHAM";
			String user="sa";
			String pass="123456";
			connection=DriverManager.getConnection(url, user, pass);
			if(connection!=null) {
				System.out.println("Thành công");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) throws Exception{
		DBConnect.openConnection();
	}
}
