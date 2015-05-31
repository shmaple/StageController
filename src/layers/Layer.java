package layers;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public abstract class Layer extends JPanel {
	private int x;
	private int y;
	private int w;
	private int h;
	private String title;
	private static final int PADDING=32;
	protected GridLayout gridLayout=new GridLayout(4,2); 
	
	
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
		//gridLayout.setHgap(5);
		//gridLayout.setVgap(5);
		
		//this.setLayout(gridLayout);
		//label.setPreferredSize(new JButton("dddd").getPreferredSize());
		 initComponent();
		 addComponent();
		
	}
	public abstract void initComponent() ;
	public abstract void addComponent();


}
