package com.ctvit.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

//需要jdom.jar
public class Java2XML {

	public void BuildXMLDoc() throws IOException, JDOMException {
		// 创建根节点 并设置它的属性 ;
		Element root = new Element("books").setAttribute("count", "4");
		root.setAttribute("xmlnsod", "urn:schemas-microsoft-com:officedata");
		root.setAttribute("generated", new Date() + "");
		// 将根节点添加到文档中；
		Document Doc = new Document(root);

		for (int i = 0; i < 4; i++) {
			// 创建节点 book;
			Element elements = new Element("book");
			// 给 book 节点添加子节点并赋值；
			elements.addContent(new Element("id").setText(i + ""));
			elements.addContent(new Element("name").setText(i + "234"));
			//
			root.addContent(elements);
		}
		// 输出 books.xml 文件；
		// 使xml文件 缩进效果
		Format format = Format.getPrettyFormat();
		XMLOutputter XMLOut = new XMLOutputter(format);
		XMLOut.output(Doc, new FileOutputStream("d:/books.xml"));
	}

	public static void main(String[] args) {
		try {
			Java2XML j2x = new Java2XML();
			System.out.println("正在生成 books.xml 文件...");
			j2x.BuildXMLDoc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("c:/books.xml 文件已生成");
	}
}
