package config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class AppConfig  {
	private String[] serialportParameterName=new String[]{
			"Portname","Baudrate","Databit","Parity","Stopbit","FlowControl","Delimit"};
	private String[] serialportParameterDefaultValue=new String[]{
			"COM1","9600","8","None","1","None","CR+LF"};
	
	
	private void createConfig(String fileName)
	{
		Document doc=DocumentHelper.createDocument();
		Element root=doc.addElement("StageConfig");
		Element serialCfg=root.addElement("SerialPort");
		serialCfg.addComment("Serial port config");
		Element gscCfg=serialCfg.addElement("GSC-02");
		Element fineCfg=serialCfg.addElement("Fine-502");
		for(int i=0;i<serialportParameterName.length;i++)
		{
			gscCfg.addAttribute(serialportParameterName[i], serialportParameterDefaultValue[i]);
			fineCfg.addAttribute(serialportParameterName[i], serialportParameterDefaultValue[i]);
		}
		fineCfg.attributeValue("Portname", "COM2");
		
		
		try {
			
			OutputFormat format =OutputFormat.createPrettyPrint();// �����͸�ʽ
			format.setEncoding("UTF-8");// �����ļ��ڲ����ֵı���
			format.setLineSeparator(String.valueOf(new char[]{13,10}));
			// format.setExpandEmptyElements(false);
			// format.setTrimText(false);
			 format.setIndent(false); // �����Ƿ�����
			 format.setIndent("\t"); // �Կո�ʽʵ������
			 format.setNewlines(true); // �����Ƿ���
 			String encoding = "UTF-8";// �����ļ��ı��룡����format����һ����
			OutputStreamWriter outstream = new OutputStreamWriter(
					new FileOutputStream(fileName), encoding);
			XMLWriter writer = new XMLWriter(outstream, format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args)
	{
		AppConfig ac=new AppConfig();
		
		ac.createConfig("config/test.xml");
		
	}

}
