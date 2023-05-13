package model;

import java.io.Serializable;

public class bike implements Serializable{
	private String id,nguoimuon,lienhe,thoigian,thuoctinh;
	private int tuoi;
	public bike(String id, String nguoimuon, String lienhe, String thoigian, String thuoctinh, int tuoi) {
		this.id = id;
		this.nguoimuon = nguoimuon;
		this.lienhe = lienhe;
		this.thoigian = thoigian;
		this.thuoctinh = thuoctinh;
		this.tuoi = tuoi;
	}
	public bike() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNguoimuon() {
		return nguoimuon;
	}
	public void setNguoimuon(String nguoimuon) {
		this.nguoimuon = nguoimuon;
	}
	public String getLienhe() {
		return lienhe;
	}
	public void setLienhe(String lienhe) {
		this.lienhe = lienhe;
	}
	public String getThoigian() {
		return thoigian;
	}
	public void setThoigian(String thoigian) {
		this.thoigian = thoigian;
	}
	public String getThuoctinh() {
		return thuoctinh;
	}
	public void setThuoctinh(String thuoctinh) {
		this.thuoctinh = thuoctinh;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	
}
