package model;

import java.rmi.Remote;
import java.rmi.server.RemoteObject;

public interface fruitRMI extends Remote{
	boolean insert(fruit product)throws Exception;
	boolean update(fruit product) throws Exception;
	boolean delete(String id) throws Exception;
//	List<product> getData();
	fruit Search(String search) throws Exception;
}
