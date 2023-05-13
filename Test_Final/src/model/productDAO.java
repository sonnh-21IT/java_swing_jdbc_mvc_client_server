package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class productDAO extends UnicastRemoteObject implements productRMI{
		public productDAO()throws RemoteException{
			super();
		}
		public boolean insert(product product)throws Exception {
		String sql="insert into product(id,name,type,src,producer,price) values(?,?,?,?,?,?)";
		try(Connection conn=DBConnection.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(1,product.getId());
			ps.setString(2,product.getName());
			ps.setString(3,product.getType());
			ps.setString(4,product.getSrc());
			ps.setString(5,product.getProducer());
			ps.setInt(6,product.getPrice());
			
			return ps.executeUpdate()>0;
		}
	}
	public boolean update(product product) throws Exception{
		String sql="update product set name=?,type=?,src=?,producer=?,price=? where id=?";
		try(Connection conn=DBConnection.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(6,product.getId());
			ps.setString(1,product.getName());
			ps.setString(2,product.getType());
			ps.setString(3,product.getSrc());
			ps.setString(4,product.getProducer());
			ps.setInt(5,product.getPrice());
			
			return ps.executeUpdate()>0;
		}
	}
	public boolean delete(String id) throws Exception{
		String sql="delete from product where id=?";
		try(Connection conn=DBConnection.openConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				){
			ps.setString(1,id);
			
			return ps.executeUpdate()>0;
		}
	}
	public List<product> getData(){
		List<product> list=new ArrayList<>();
		try {
			Connection conn=DBConnection.openConnection();
			String sql="select * from product";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				product product=new product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setSrc(rs.getString("src"));
				product.setProducer(rs.getString("producer"));
				product.setPrice(rs.getInt("price"));
				
				list.add(product);
				
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
	public product Search(String search) throws Exception {
		String sql = "select * from product where id=?";
		try (Connection conn = DBConnection.openConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product product = new product();
				product.setId(rs.getString("ID"));
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setSrc(rs.getString("src"));
				product.setProducer(rs.getString("producer"));
				product.setPrice(rs.getInt("price"));
				rs.close();
				return product;
			} else {
				rs.close();
				return null;
			}
		}
	}
	public int CountType() {
		String sql = "SELECT type,COUNT(DISTINCT type) AS 'tacgia' FROM dbo.product GROUP BY type";
		int count = 0;
		try (Connection conn = DBConnection.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		}
		return count;
	}

	public int CountSrc() {
		String sql = "SELECT src,COUNT(DISTINCT src) AS 'src_chagne' FROM dbo.product GROUP BY src";
		int count = 0;
		try (Connection conn = DBConnection.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		}
		return count;
	}
}
