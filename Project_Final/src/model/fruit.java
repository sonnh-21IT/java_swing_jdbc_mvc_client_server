package model;

import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;

public class fruit implements Serializable{
	private String id,name,src,provided;
	private int num,price;
	public fruit(String id, String name, String src, String provided, int num, int price) {
		this.id = id;
		this.name = name;
		this.src = src;
		this.provided = provided;
		this.num = num;
		this.price = price;
	}
	public fruit() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getProvided() {
		return provided;
	}
	public void setProvided(String provided) {
		this.provided = provided;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
