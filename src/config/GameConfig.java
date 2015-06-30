package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 从xml文档中读取整个游戏配置
 * @author shmaple-work
 *
 */
public class GameConfig {
	/**
	 * 窗口宽度
	 */
	private int width;
	/**
	 * 窗口高度
	 */
	private int height;
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getWindowSize() {
		return windowSize;
	}
	public int getPadding() {
		return padding;
	}
	/**
	 * 边框尺寸
	 */
	private int windowSize;
	/**
	 * 边框内边距
	 */
	private int padding;
	/**
	 *窗口拔高
	 */
	private int windowUp;
	public int getWindowUp() {
		return windowUp;
	}
	private String title;
	public String getTitle()
	{
		return title;
	}
	/**
	 * 图层属性
	 */
	private List<LayerConfig> layersConfig;
	/**
	 * 构造函数，读取XML文件，获取游戏全部配置信息
	 * @throws DocumentException
	 */
	public GameConfig() throws DocumentException
	{
		//xml解析器
		SAXReader reader=new SAXReader();
		//读取xml文档
		Document doc=reader.read("config/cfg.xml");
		//获取xml根节点
		Element game=doc.getRootElement();
		//设置UI参数
		setUiConfig(game.element("frame"));
		//设置系统参数
		//setUiConfig(game.element("system"));
		//设置数据库参数
		//setUiConfig(game.element("data"));
		
	}
	/**
	 * 设置界面参数
	 * @param frame：界面参数根节点
	 */
	private void setUiConfig(Element frame)
	{
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.windowSize=Integer.parseInt(frame.attributeValue("windowsize"));
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		this.title=frame.attributeValue("title");
		List<Element> layers=frame.elements("layer");
		layersConfig=new ArrayList<LayerConfig>();
		for(Element layer:layers)
		{
			LayerConfig lc=new LayerConfig(
			layer.attributeValue("className"),
			Integer.parseInt(layer.attributeValue("x")),
			Integer.parseInt(layer.attributeValue("y")),
			Integer.parseInt(layer.attributeValue("w")),
			Integer.parseInt(layer.attributeValue("h")));
			layersConfig.add(lc);
		}
	}
	public List<LayerConfig> getLayersConfig()
	{
		return layersConfig;
	}
	private void setSystemConfig(Element frame)
	{
		
	}
	private void setDataConfig(Element frame)
	{
		
	}

}
