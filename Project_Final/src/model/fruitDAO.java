package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class fruitDAO extends UnicastRemoteObject implements fruitRMI{

	public fruitDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(fruit fruit) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into fruit(id,name,num,src,price,provided) values(?,?,?,?,?,?)";
		try(Connection conn=DBConnect.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(1,fruit.getId());
			ps.setString(2,fruit.getName());
			ps.setInt(3,fruit.getNum());
			ps.setString(4,fruit.getSrc());
			ps.setInt(5,fruit.getPrice());
			ps.setString(6,fruit.getProvided());
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean update(fruit fruit) throws Exception {
		// TODO Auto-generated method stub
		String sql="update fruit set name=?,num=?,src=?,price=?,provided=? where id=?";
		try(Connection conn=DBConnect.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(6,fruit.getId());
			ps.setString(1,fruit.getName());
			ps.setInt(2,fruit.getNum());
			ps.setString(3,fruit.getSrc());
			ps.setInt(4,fruit.getPrice());
			ps.setString(5,fruit.getProvided());
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from fruit where id=?";
		try(Connection conn=DBConnect.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(1,id);
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public fruit Search(String search) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from fruit where id=?";
		try (Connection conn = DBConnect.openConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fruit fruit = new fruit();
				fruit.setId(rs.getString("ID"));
				fruit.setName(rs.getString("name"));
				fruit.setNum(rs.getInt("num"));
				fruit.setSrc(rs.getString("src"));
				fruit.setPrice(rs.getInt("price"));
				fruit.setProvided(rs.getString("provided"));
				rs.close();
				return fruit;
			} else {
				rs.close();
				return null;
			}
		}
	}
	public List<fruit> getData(){
		List<fruit> list=new ArrayList<>();
		try {
			Connection conn=DBConnect.openConnection();
			String sql="select * from fruit";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				fruit fruit=new fruit();
				fruit.setId(rs.getString("id"));
				fruit.setName(rs.getString("name"));
				fruit.setNum(rs.getInt("num"));
				fruit.setSrc(rs.getString("src"));
				fruit.setPrice(rs.getInt("price"));
				fruit.setProvided(rs.getString("provided"));
				
				list.add(fruit);
				
			}
			ps.close();
			rs.close();
			conn.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
