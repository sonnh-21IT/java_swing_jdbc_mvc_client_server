package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class bikeDAO extends UnicastRemoteObject implements bikeRMI{

	public bikeDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(bike bike) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into bike(id,nguoimuon,tuoi,lienhe,thoigian,thuoctinh) values(?,?,?,?,?,?)";
		try(Connection conn=DBConnect.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(1,bike.getId());
			ps.setString(2,bike.getNguoimuon());
			ps.setInt(3,bike.getTuoi());
			ps.setString(4,bike.getLienhe());
			ps.setString(5,bike.getThoigian());
			ps.setString(6,bike.getThuoctinh());
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean update(bike bike) throws Exception {
		// TODO Auto-generated method stub
		String sql="update bike set nguoimuon=?,tuoi=?,lienhe=?,thoigian=?,thuoctinh=? where id=?";
		try(Connection conn=DBConnect.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(6,bike.getId());
			ps.setString(1,bike.getNguoimuon());
			ps.setInt(2,bike.getTuoi());
			ps.setString(3,bike.getLienhe());
			ps.setString(4,bike.getThoigian());
			ps.setString(5,bike.getThuoctinh());
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from bike where id=?";
		try(Connection conn=DBConnect.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(1,id);
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public bike Search(String search) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from bike where id=?";
		try (Connection conn = DBConnect.openConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bike bike = new bike();
				bike.setId(rs.getString("id"));
				bike.setNguoimuon(rs.getString("nguoimuon"));
				bike.setTuoi(rs.getInt("tuoi"));
				bike.setThoigian(rs.getString("thoigian"));
				bike.setLienhe(rs.getString("lienhe"));
				bike.setThuoctinh(rs.getString("thuoctinh"));
				return bike;
			} else {
				rs.close();
				return null;
			}
		}
	}
	public List<bike> getData() {
		List<bike> list=new ArrayList<>();
		try {
			Connection conn=DBConnect.openConnection();
			String sql="select * from bike";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				bike bike=new bike();
				bike.setId(rs.getString("id"));
				bike.setNguoimuon(rs.getString("nguoimuon"));
				bike.setTuoi(rs.getInt("tuoi"));
				bike.setThoigian(rs.getString("thoigian"));
				bike.setLienhe(rs.getString("lienhe"));
				bike.setThuoctinh(rs.getString("thuoctinh"));
				
				list.add(bike);
				
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
