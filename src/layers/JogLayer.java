package layers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class JogLayer extends Layer {
	private JComboBox<Item> axisSelector;
	private JComboBox<Item> firstDirectSelector;
	private JComboBox<Item> SecondDirectSelector;
	private JButton start;
	private JButton stop;
	public JogLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title);

		
	}


	@Override
	public void initComponent() {
		axisSelector=new JComboBox<Item>();
		firstDirectSelector=new JComboBox<Item>();
		SecondDirectSelector=new JComboBox<Item>();
		start=new JButton("Start");
		stop=new JButton("Stop");
		axisSelector.addItem(new Item("First Axis","1"));
		axisSelector.addItem(new Item("Second Axis","2"));
		axisSelector.addItem(new Item("Both Axis","W"));
		firstDirectSelector.addItem(new Item("Postive","+"));
		firstDirectSelector.addItem(new Item("Negative","-"));
		
		SecondDirectSelector.addItem(new Item("Postive","+"));
		SecondDirectSelector.addItem(new Item("Negative","-"));
		
		start.addActionListener(this);
		stop.addActionListener(this);
		
	}
	@Override
	public void addComponent() {
		this.add(new JLabel("Selet Axis:"));
		this.add(axisSelector);
		this.add(new JLabel("First Axis Direction:",JLabel.RIGHT));
		this.add(firstDirectSelector);
		this.add(new JLabel("Second Axis Direction:",JLabel.RIGHT));
		this.add(SecondDirectSelector);
		this.add(start);
		this.add(stop);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//this.userControl.sendCMD(tip);
	}
}
