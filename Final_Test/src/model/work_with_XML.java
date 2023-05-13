package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class work_with_XML {
	public static List<bike> readXML() throws Exception{
		List<bike> bikeList=new ArrayList<>();
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		Document document=builder.parse(new File("bike_in.xml"));
		
		Element rootNode=document.getDocumentElement();
		System.out.println(rootNode.getNodeName());
		
		NodeList list=rootNode.getChildNodes();
		for(int i=0;i<list.getLength();i++) {
			Node item=list.item(i);
			if(item.getNodeName().equalsIgnoreCase("bike")) {
				bike bike=new bike();
				NodeList sublist=item.getChildNodes();
				for(int j=0;j<sublist.getLength();j++) {
					Node subitem=sublist.item(j);
					if(subitem.getNodeName().equalsIgnoreCase("id")) {
//						System.out.println(subitem.getTextContent());
						String id=subitem.getTextContent();
						bike.setId(id);
					}
					if(subitem.getNodeName().equalsIgnoreCase("nguoimuon")) {
//						System.out.println(subitem.getTextContent());
						String nguoimuon=subitem.getTextContent();
						bike.setNguoimuon(nguoimuon);
					}
					if(subitem.getNodeName().equalsIgnoreCase("tuoi")) {
//						System.out.println(subitem.getTextContent());
						int tuoi=Integer.parseInt(subitem.getTextContent());
						bike.setTuoi(tuoi);
					}
					if(subitem.getNodeName().equalsIgnoreCase("lienhe")) {
//						System.out.println(subitem.getTextContent());
						String lienhe=subitem.getTextContent();
						bike.setLienhe(lienhe);
					}
					if(subitem.getNodeName().equalsIgnoreCase("thoigian")) {
//						System.out.println(subitem.getTextContent());
						String thoigian=subitem.getTextContent();
						bike.setThoigian(thoigian);
					}
					if(subitem.getNodeName().equalsIgnoreCase("thuoctinh")) {
//						System.out.println(subitem.getTextContent());
						String thuoctinh=subitem.getTextContent();
						bike.setThuoctinh(thuoctinh);
					}
				}
				bikeList.add(bike);
			}
		}
		return bikeList;
	}
//	public static void writeonDB() throws Exception{
//		List<fruit> list= work_with_XML.readXML();
//		fruitDAO dao=new fruitDAO();
//		for(fruit item:list) {
//			dao.insert(item);
//		}
//	}
	public static bike search(String id) throws Exception {
		List<bike> list= work_with_XML.readXML();
		for(bike item:list) {
			if(item.getId().equalsIgnoreCase(id)) {
				return item;
			}
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		work_with_XML.readXML();
	}
//	public static int countsrc() throws Exception{
//		List<product> list=work_with_XML.readXML();
//		int count=0;
//		for(product item:list) {
//			if(item.getSrc().equalsIgnoreCase("Trong Nước")||item.getSrc().equalsIgnoreCase("Nước Ngoài")) {
//				count++;
//			}
//		}
//		return count;
//	}
//	public static int counttype() throws Exception{
//		List<product> list=work_with_XML.readXML();
//		int count=0;
//		for(product item:list) {
//			if(item.getType().equalsIgnoreCase("Nội Thất")||item.getType().equalsIgnoreCase("Đồ Dùng Học Tập")||item.getType().equalsIgnoreCase("Dụng Cụ Sửa Chữa")) {
//				count++;
//			}
//		}
//		return count;
//	}
}
