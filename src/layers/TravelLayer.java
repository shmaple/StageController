package layers;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TravelLayer extends Layer implements ItemListener {
	
	private static final String tip=
			"This command is to specify the axis of travel, direction, "
			+ "and the travel (number ofpulses). This command must always be followed "
			+ "by a drive (G) command. Travel is bymeans of acceleration/deceleration "
			+ "driving.";
	private AxisSelector axisSelector;
	private DirectSelector firstDirectSelector;
	private DirectSelector SecondDirectSelector;
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
		String commandTip="This command is to specify the axis of travel, direction, "
				+ "and the travel (number ofpulses). This command must always be followed "
				+ "by a drive (G) command. Travel is by means of acceleration/deceleration "
				+ "driving.";
		axisSelector=new AxisSelector();
		axisSelector.addItemListener(this);
		firstDirectSelector=new DirectSelector();
		SecondDirectSelector=new DirectSelector();
		
		this.firstAxisPulse=new JTextField();
		this.secondAxisPulse=new JTextField();
		setButton =new JButton("GO");
		setButton.setToolTipText(formatTip(commandTip));
		setButton.addActionListener(this);
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

	@Override
	/**
	 * ComboBox回调函数，依据选择的轴禁用相应的方向选择器
	 */
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED)
		{
			Item  chosedItem=(Item)e.getItem();
			String chosedValue=chosedItem.getValue();
			if(chosedValue.equals("1"))
			{
				this.SecondDirectSelector.setEnabled(false);
				this.secondAxisPulse.setEditable(false);
				this.firstDirectSelector.setEnabled(true);
				this.firstAxisPulse.setEditable(true);
			}
			if(chosedValue.equals("2"))
			{
				this.firstDirectSelector.setEnabled(false);
				this.firstAxisPulse.setEditable(false);
				this.SecondDirectSelector.setEnabled(true);
				this.secondAxisPulse.setEditable(true);
			}
			if(chosedValue.equals("W"))
			{
				this.firstDirectSelector.setEnabled(true);
				this.firstAxisPulse.setEditable(true);
				this.SecondDirectSelector.setEnabled(true);
				this.secondAxisPulse.setEditable(true);
			}
		}
	}
}
