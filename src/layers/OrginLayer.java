package layers;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class OrginLayer extends Layer {

	private JComboBox<Item> axisSelector;
	private JComboBox<Item> firstDirectSelector;
	private JComboBox<Item> SecondDirectSelector;
	private JButton setButton;
	private static final String tip=
			"<html><body>This command is used to detect the mechanical origin for a stage <br>"
			+ "and set that position as the origin. Once the mechanical origin <br>"
			+ "has been detected, the value displayed will be 0. Each axis moves <br>"
			+ "at the following constant conditions: Minimum speed (S):500PPS, <br>"
			+ "Maximum speed (F): 5000PPS, Acceleration/ Deceleration time (R): <br>"
			+ "200mS.Axes to home are depending on the DIP Switch settings</body></html>";
	
	public OrginLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title,tip);
		 
	}

	public void initComponent()
	{
		
		axisSelector=new JComboBox<Item>();
		
		firstDirectSelector=new JComboBox<Item>();
		SecondDirectSelector=new JComboBox<Item>();
		
		axisSelector.addItem(new Item("First Axis","1"));
		axisSelector.addItem(new Item("Second Axis","2"));
		axisSelector.addItem(new Item("Both Axis","W"));
		firstDirectSelector.addItem(new Item("Postive","+"));
		firstDirectSelector.addItem(new Item("Negative","-"));
		SecondDirectSelector.addItem(new Item("Postive","+"));
		SecondDirectSelector.addItem(new Item("Negative","-"));
		
		setButton =new JButton();
		setButton.setText("Setting");

	}
	@Override
	public void addComponent() {
	
		this.add(axisSelector);
		this.add(firstDirectSelector);
		this.add(SecondDirectSelector);
		this.add(setButton);
		
		
	}


}
