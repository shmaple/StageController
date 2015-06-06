package layers;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SpeedSettingLayer extends Layer {
	private static final String tip=
			"<html><body style=\"width:200px;text-align:justify;text-justify:inter-ideograph\">"
			+ "The minimum speed (S), maximum speed (F), and acceleration/deceleration time (R) "
			+ "are set according to the initialize settings when the power is turned on. This "
			+ "commandallows you to change these initial settings. The initialize setting is "
			+ "(S): 500PPS, (F):5000PPS, (R): 200mS.</html></body>";
	private JComboBox<Item> speedRangeSelector;

	private JTextField spd1First;
	private JTextField spd2First;
	private JTextField spd3First;
	
	private JTextField spd1Second;
	private JTextField spd2Second;
	private JTextField spd3Second;
	private JButton setting;

	public SpeedSettingLayer(int x, int y, int w, int h, String title
			) {
		super(x, y, w, h, title, tip);
		this.gridLayout.setRows(8);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() {
		speedRangeSelector=new JComboBox<Item>();
		setting=new JButton("Setting");
		speedRangeSelector.addItem(new Item("Low Speed","1"));
		speedRangeSelector.addItem(new Item("High Speed","2"));
		spd1First=new JTextField(10);
		spd2First=new JTextField(10);
		spd3First=new JTextField(10);
		spd1Second=new JTextField(10);
		spd2Second=new JTextField(10);
		spd3Second=new JTextField(10);


	}

	@Override
	public void addComponent() {
		this.add(new JLabel("Select Speed Range:"));
		this.add(speedRangeSelector);
		this.add(new JLabel("First Axis Min.Speed:"));
		this.add(spd1First);
		this.add(new JLabel("1st Axis Max.Speed:"));
		this.add(spd2First);
		this.add(new JLabel("1st Axis ACC/DEC Time:"));
		this.add(spd3First);
		this.add(new JLabel("2se Axis Min.Speed:"));
		this.add(spd1Second);
		this.add(new JLabel("2se Axis Max.Speed:"));
		this.add(spd2Second);
		this.add(new JLabel("2se Axis ACC/DEC Timel:"));
		this.add(spd3Second);
		this.add(new JLabel());
		this.add(setting);
	}

}
