package model;

import java.rmi.Remote;

public interface bikeRMI extends Remote{
	boolean insert(bike bike)throws Exception;
	boolean update(bike bike) throws Exception;
	boolean delete(String id) throws Exception;
//	List<product> getData();
	bike Search(String search) throws Exception;
}
