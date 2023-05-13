package model;

import java.rmi.Remote;
import java.util.List;

public interface productRMI extends Remote{
	boolean insert(product product)throws Exception;
	boolean update(product product) throws Exception;
	boolean delete(String id) throws Exception;
//	List<product> getData();
	product Search(String search) throws Exception;
	int CountSrc()throws Exception;
	int CountType() throws Exception;
}
