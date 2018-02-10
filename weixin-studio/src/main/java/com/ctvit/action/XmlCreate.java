package com.ctvit.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ctvit.bean.Interact;

public class XmlCreate {
	private Document document;

	public void createXml(List<Interact> interacts, String xmlPath) throws ParserConfigurationException {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();
			Element root = this.document.createElement("dataroot");
			root.setAttribute("xmlns:od", "urn:schemas-microsoft-com:officedata");
			root.setAttribute("generated", time);
			this.document.appendChild(root);
			for (int i = 0; i < interacts.size(); i++) {
				Element low_ticker = this.document.createElement("low_ticker");
				Element id = this.document.createElement("ID");
				id.appendChild(this.document.createTextNode((i + 1) + ""));
				Element nickname = this.document.createElement("Nickname");
				nickname.appendChild(this.document.createTextNode(interacts.get(i).getNickname()));
				Element content = this.document.createElement("Content");
				content.appendChild(this.document.createTextNode(interacts.get(i).getContent()));
				Element insertTime = this.document.createElement("InsertTime");
				insertTime.appendChild(this.document.createTextNode(dateFormat.format(interacts.get(i).getInsertTime())));
				Element sex = this.document.createElement("Sex");
				sex.appendChild(this.document.createTextNode(interacts.get(i).getSex()));
				Element image = this.document.createElement("Image");
				image.appendChild(this.document.createTextNode(interacts.get(i).getImage()));
				Element audio = this.document.createElement("Audio");
				audio.appendChild(this.document.createTextNode(interacts.get(i).getAudio()));
				Element video = this.document.createElement("Video");
				video.appendChild(this.document.createTextNode(interacts.get(i).getVideo()));
				Element city = this.document.createElement("City");
				city.appendChild(this.document.createTextNode(interacts.get(i).getCity()));
				Element headimgurl = this.document.createElement("Headimgurl");
				headimgurl.appendChild(this.document.createTextNode(interacts.get(i).getHeadimgurl()));
				low_ticker.appendChild(id);
				low_ticker.appendChild(nickname);
				low_ticker.appendChild(content);
				low_ticker.appendChild(insertTime);
				low_ticker.appendChild(sex);
				low_ticker.appendChild(image);
				low_ticker.appendChild(audio);
				low_ticker.appendChild(video);
				low_ticker.appendChild(city);
				low_ticker.appendChild(headimgurl);
				root.appendChild(low_ticker);
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			// document.setXmlStandalone(true);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream(xmlPath + "weixin.xml"));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("生成XML文件成功!");
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws ParserConfigurationException {
		XmlCreate dd = new XmlCreate();
		String str = "D:/grade.xml";
		// dd.createXml(str); // 创建xml
		// dd.parserXml(str); // 读取xml
	}
}