package model;

import java.io.Serializable;

public class product implements Serializable{
	private String id,name,type,src,producer;
	private int price;
	
	public product(String id,String name, String type, String src, String producer, int price) {
		this.id=id;
		this.name = name;
		this.type = type;
		this.src = src;
		this.producer = producer;
		this.price = price;
	}

	public product() {

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "product [id=" + id + ", name=" + name + ", type=" + type + ", src=" + src + ", producer=" + producer
				+ ", price=" + price + "]";
	}
	
}
