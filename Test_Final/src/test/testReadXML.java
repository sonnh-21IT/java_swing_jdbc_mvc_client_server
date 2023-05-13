package test;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.product;

public class testReadXML {
	public static void main(String[] args) throws Exception {
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
				System.out.println(product.toString());
			}
		}
	}
}
