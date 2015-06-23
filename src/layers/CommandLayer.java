package layers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class CommandLayer extends Layer{
	private static final String tip=
			"<html><body style=\"width:200px;text-align:justify;text-justify:inter-ideograph\">"
			+ "This command drives stages continuously (at a constant speed) at the starting "
			+ "speed(S). This command must always be followed by a drive (G) command. The stage "
			+ "will stop by an L command.</html></body>";
	private JComboBox<Item> axisSelector;
	private JButton stop;
	private JButton emergencyStop;
	private JButton logicalOriginSet;
	private JButton hold;

	public CommandLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title, tip);
		this.gridLayout.setRows(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() {
		axisSelector=new JComboBox<Item>();	
		axisSelector.addItem(new Item("First Axis","1"));
		axisSelector.addItem(new Item("Second Axis","2"));
		axisSelector.addItem(new Item("Both Axis","W"));
		stop=new JButton("Stop");
		emergencyStop=new JButton("Emergency Stop");
		emergencyStop.setToolTipText("Emergency stop all axies");
		logicalOriginSet=new JButton("Set logical origin");
		logicalOriginSet.setToolTipText("Set electronic (logical) origin to the current position of selected axis");
		hold=new JButton("Hold/Free Motro");
		hold.setToolTipText("Hold or free selected axis");
		stop.addActionListener(this);
		emergencyStop.addActionListener(this);
		logicalOriginSet.addActionListener(this);
		hold.addActionListener(this);
		
	}

	@Override
	public void addComponent() {
		this.add(new JLabel("First Axis Direction:"));
		this.add(axisSelector);
		this.add(logicalOriginSet);
		this.add(hold);
		this.add(stop);
		this.add(emergencyStop);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.userControl.sendCMD(tip);
	}

}
