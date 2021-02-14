package com.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestUtils {
	public static Properties prop;
	public InputStream dataio;
	
	public static void loadProp() {
		try {

			String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";
			FileInputStream io = new FileInputStream(path);
			prop = new Properties();
			prop.load(io);

		} catch (Exception e) {
			System.out.println("exception Occured while loading Properties " + e);
		}
	}

	public   JSONObject loadInputdata(String file) throws IOException {
		JSONObject json = null;
		try {
			String datafile =  file;
			dataio = getClass().getClassLoader().getResourceAsStream(datafile);
			JSONTokener jsontoken = new JSONTokener(dataio);
			json= new  JSONObject(jsontoken);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (dataio != null) {
				dataio.close();
			}

		}
		return json;

	}

	public HashMap<String, String> parseStringXML() throws Exception {
		String path = "string/staticdata.xml";
		InputStream file = null;
		HashMap<String, String> stringMap = new HashMap<String, String>();
		try {
			file = getClass().getClassLoader().getResourceAsStream(path);
			// Get DOM Builder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Build Document
			Document document = builder.parse(file);

			// Normalize the XML Structure;
			document.getDocumentElement().normalize();

			// root node
			Element root = document.getDocumentElement();
			// System.out.println(root.getNodeName());

			// Get all elements
			NodeList nList = document.getElementsByTagName("string");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (file != null) {
				file.close();
			}
		}

		return stringMap;
	}
	
	public String getDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}

}
