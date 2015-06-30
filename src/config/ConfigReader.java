package config;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigReader {
	public static void readConfig() throws DocumentException
	{
		SAXReader reader=new SAXReader();
		Document doc=reader.read("config/cfg.xml");
		Element game=doc.getRootElement();
		Element frame=game.element("frame");
		//frame.attributeValue("width");
		List<Element> layers=frame.elements("layer");
		for(Element layer:layers)
		{
			System.out.println(layer.attributeValue("className"));
		}
	}
	public static void main(String[] args) throws DocumentException
	{
		readConfig();
	}
}
