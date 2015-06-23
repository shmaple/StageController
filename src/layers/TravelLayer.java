package layers;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TravelLayer extends Layer {
	
	private static final String tip=
			"<html><body style=\"width:200px;text-align:justify;text-justify:inter-ideograph\">This command is to specify the axis of travel, direction, "
			+ "and the travel (number ofpulses). This command must always be followed "
			+ "by a drive (G) command. Travel is bymeans of acceleration/deceleration "
			+ "driving.</html></body>";
	private JComboBox<Item> axisSelector;
	private JComboBox<Item> firstDirectSelector;
	private JComboBox<Item> SecondDirectSelector;
	private JTextField firstAxisPulse;
	private JTextField secondAxisPulse;
	private JButton setButton;


	public TravelLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title, tip);
		this.gridLayout.setRows(6);
		this.gridLayout.setColumns(2);
		//this.setLayout(gridLayout);
	}

	@Override
	public void initComponent() {
		
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
		this.firstAxisPulse=new JTextField();
		
		this.firstAxisPulse.setPreferredSize(SecondDirectSelector.getPreferredSize());
		this.secondAxisPulse=new JTextField();
		this.secondAxisPulse.setPreferredSize(SecondDirectSelector.getPreferredSize());
		setButton =new JButton("GO");
		setButton.addActionListener(this);
		//setButton.setPreferredSize(SecondDirectSelector.getPreferredSize());
		
	}

	@Override
	public void addComponent() {
		this.add(new JLabel("Selet Axis:"));
		this.add(axisSelector);
		this.add(new JLabel("First Axis Direction:"));
		this.add(firstDirectSelector);
		this.add(new JLabel("First Axis Pulse:"));
		this.add(firstAxisPulse);
		this.add(new JLabel("Second Axis Direction:"));
		this.add(SecondDirectSelector);
		this.add(new JLabel("Second Axis Pulse:"));
		this.add(secondAxisPulse);
		this.add(new JLabel());
		this.add(setButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.userControl.sendCMD(tip);
	}

}
