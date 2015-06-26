package layers;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SpeedSettingLayer extends Layer {
	private JComboBox<Item> speedRangeSelector;
	private JTextField spd1First;
	private JTextField spd2First;
	private JTextField spd3First;
	private JTextField spd1Second;
	private JTextField spd2Second;
	private JTextField spd3Second;
	private JButton setButton;

	public SpeedSettingLayer(int x, int y, int w, int h, String title
			) {
		super(x, y, w, h, title);
	//	this.gridLayout.setRows(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() {
		String commandTip=
						 "The minimum speed (S), maximum speed (F), and acceleration/deceleration time (R) "
						+ "are set according to the initialize settings when the power is turned on. This "
						+ "commandallows you to change these initial settings. The initialize setting is "
						+ "(S): 500PPS, (F):5000PPS, (R): 200mS.";
		speedRangeSelector=new JComboBox<Item>();
		speedRangeSelector.addItem(new Item("High Speed","2"));
		speedRangeSelector.addItem(new Item("Low Speed","1"));
		spd1First=new JTextField("500");
		spd2First=new JTextField("5000");
		spd3First=new JTextField("200");
		spd1Second=new JTextField("500");
		spd2Second=new JTextField("5000");
		spd3Second=new JTextField("200");
		setButton=new JButton("Setting");
		setButton.setToolTipText(formatTip(commandTip));
		setButton.addActionListener(this);
	}

	@Override
	public void addComponent() {
		
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(new JLabel("Select Speed Ranges:",JLabel.RIGHT),gbc);
		gbc.gridy=1;
		this.add(new JLabel("First Axis Min.Speed:",JLabel.RIGHT),gbc);
		gbc.gridy=2;
		this.add(new JLabel("First Axis Max.Speed:",JLabel.RIGHT),gbc);
		gbc.gridy=3;
		this.add(new JLabel("First Axis ACC/DEC Time:",JLabel.RIGHT),gbc);
		gbc.gridy=4;
		this.add(new JLabel("Second Axis Min.Speed:",JLabel.RIGHT),gbc);
		gbc.gridy=5;
		this.add(new JLabel("Second Axis Max.Speed:",JLabel.RIGHT),gbc);
		gbc.gridy=6;
		this.add(new JLabel("Second Axis ACC/DEC Time:",JLabel.RIGHT),gbc);
		gbc.gridy=7;
		this.add(new JLabel());
		
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(speedRangeSelector,gbc);
		gbc.gridy=1;
		this.add(spd1First,gbc);
		gbc.gridy=2;
		this.add(spd2First,gbc);
		gbc.gridy=3;
		this.add(spd3First,gbc);
		gbc.gridy=4;
		this.add(spd1Second,gbc);
		gbc.gridy=5;
		this.add(spd2Second,gbc);
		gbc.gridy=6;
		this.add(spd3Second,gbc);
		gbc.gridy=7;
		this.add(setButton,gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//this.userControl.sendCMD(tip);
		//System.out.print("hell");
	}

}
