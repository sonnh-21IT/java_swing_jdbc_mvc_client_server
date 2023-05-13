package model;

import java.rmi.Remote;

public interface accountRMI extends Remote{
	account checkData(account acc) throws Exception;
	boolean addAccount(account acc) throws Exception;
}
