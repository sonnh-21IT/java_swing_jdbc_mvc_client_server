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
	public static List<product> readXML() throws Exception{
		List<product> productList=new ArrayList<>();
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		Document document=builder.parse(new File("product_in.xml"));
		
		Element rootNode=document.getDocumentElement();
		System.out.println(rootNode.getNodeName());
		
		NodeList list=rootNode.getChildNodes();
		for(int i=0;i<list.getLength();i++) {
			Node item=list.item(i);
			if(item.getNodeName().equalsIgnoreCase("product")) {
				product product=new product();
				NodeList sublist=item.getChildNodes();
				for(int j=0;j<sublist.getLength();j++) {
					Node subitem=sublist.item(j);
					if(subitem.getNodeName().equalsIgnoreCase("id")) {
//						System.out.println(subitem.getTextContent());
						String id=subitem.getTextContent();
						product.setId(id);
					}
					if(subitem.getNodeName().equalsIgnoreCase("name")) {
//						System.out.println(subitem.getTextContent());
						String name=subitem.getTextContent();
						product.setName(name);
					}
					if(subitem.getNodeName().equalsIgnoreCase("type")) {
//						System.out.println(subitem.getTextContent());
						String type=subitem.getTextContent();
						product.setType(type);
					}
					if(subitem.getNodeName().equalsIgnoreCase("src")) {
//						System.out.println(subitem.getTextContent());
						String src=subitem.getTextContent();
						product.setSrc(src);
					}
					if(subitem.getNodeName().equalsIgnoreCase("producer")) {
//						System.out.println(subitem.getTextContent());
						String producer=subitem.getTextContent();
						product.setProducer(producer);
					}
					if(subitem.getNodeName().equalsIgnoreCase("price")) {
//						System.out.println(subitem.getTextContent());
						String price=subitem.getTextContent();
						product.setPrice(Integer.parseInt(price));
					}
				}
				productList.add(product);
			}
		}
		return productList;
	}
	public static void writeonDB() throws Exception{
		List<product> list= work_with_XML.readXML();
		productDAO dao=new productDAO();
		for(product item:list) {
			if(dao.Search(item.getId()+"")!=null) {
				continue;
			}
			dao.insert(item);
		}
	}
	public static product search(String id) throws Exception {
		List<product> list= work_with_XML.readXML();
		for(product item:list) {
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
