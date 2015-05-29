package layers;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public abstract class Layer extends JPanel {
	private int x;
	private int y;
	private int w;
	private int h;
	private String title;
	private static final int PADDING=32;
	
	public Layer(int x,int y,int w,int h,String title,String tip)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.title=title;
		setBounds(x, y, w, h);
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setToolTipText(tip);
		 initComponent();
		 addComponent();
		
	}
	public abstract void initComponent() ;
	public abstract void addComponent();


}
