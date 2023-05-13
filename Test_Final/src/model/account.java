package model;

import java.io.Serializable;

public class account implements Serializable{
	private String user,pass,name;

	public account(String user, String pass, String name) {
		this.user = user;
		this.pass = pass;
		this.name = name;
	}

	public account(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public account() {
	}
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
