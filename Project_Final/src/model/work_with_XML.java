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
	public static List<fruit> readXML() throws Exception{
		List<fruit> fruitList=new ArrayList<>();
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		Document document=builder.parse(new File("fruit_in.xml"));
		
		Element rootNode=document.getDocumentElement();
		System.out.println(rootNode.getNodeName());
		
		NodeList list=rootNode.getChildNodes();
		for(int i=0;i<list.getLength();i++) {
			Node item=list.item(i);
			if(item.getNodeName().equalsIgnoreCase("product")) {
				fruit fruit=new fruit();
				NodeList sublist=item.getChildNodes();
				for(int j=0;j<sublist.getLength();j++) {
					Node subitem=sublist.item(j);
					if(subitem.getNodeName().equalsIgnoreCase("id")) {
//						System.out.println(subitem.getTextContent());
						String id=subitem.getTextContent();
						fruit.setId(id);
					}
					if(subitem.getNodeName().equalsIgnoreCase("name")) {
//						System.out.println(subitem.getTextContent());
						String name=subitem.getTextContent();
						fruit.setName(name);
					}
					if(subitem.getNodeName().equalsIgnoreCase("num")) {
//						System.out.println(subitem.getTextContent());
						int num=Integer.parseInt(subitem.getTextContent());
						fruit.setNum(num);
					}
					if(subitem.getNodeName().equalsIgnoreCase("src")) {
//						System.out.println(subitem.getTextContent());
						String src=subitem.getTextContent();
						fruit.setSrc(src);
					}
					if(subitem.getNodeName().equalsIgnoreCase("provided")) {
//						System.out.println(subitem.getTextContent());
						String provided=subitem.getTextContent();
						fruit.setProvided(provided);
					}
					if(subitem.getNodeName().equalsIgnoreCase("price")) {
//						System.out.println(subitem.getTextContent());
						String price=subitem.getTextContent();
						fruit.setPrice(Integer.parseInt(price));
					}
				}
				fruitList.add(fruit);
			}
		}
		return fruitList;
	}
//	public static void writeonDB() throws Exception{
//		List<fruit> list= work_with_XML.readXML();
//		fruitDAO dao=new fruitDAO();
//		for(fruit item:list) {
//			dao.insert(item);
//		}
//	}
	public static fruit search(String id) throws Exception {
		List<fruit> list= work_with_XML.readXML();
		for(fruit item:list) {
			if(item.getId().equalsIgnoreCase(id)) {
				return item;
			}
		}
		return null;
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
