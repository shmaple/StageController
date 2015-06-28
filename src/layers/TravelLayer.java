package layers;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TravelLayer extends Layer implements ItemListener {
	private AxisSelector axisSelector;
	private DirectSelector firstDirectSelector;
	private DirectSelector SecondDirectSelector;
	private JTextField firstAxisPulse;
	private JTextField secondAxisPulse;
	private JButton setButton;

	public TravelLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title);

	}

	@Override
	public void initComponent() {
		String commandTip="This command is to specify the axis of travel, direction, "
				+ "and the travel (number ofpulses). This command must always be followed "
				+ "by a drive (G) command. Travel is by means of acceleration/deceleration "
				+ "driving.";
		axisSelector=new AxisSelector(2);
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
		gbc.insets=new Insets(8,5,8,5);
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(new JLabel("Selet Axis:",JLabel.RIGHT),gbc);
		gbc.gridy=1;
		this.add(new JLabel("First Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=2;		
		this.add(new JLabel("First Axis Pulse:",JLabel.RIGHT),gbc);
		gbc.gridy=3;
		this.add(new JLabel("Second Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=4;
		this.add(new JLabel("Second Axis Pulse:",JLabel.RIGHT),gbc);
		gbc.gridy=5;
		this.add(new JLabel(),gbc);
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(axisSelector,gbc);
		gbc.gridy=1;	
		this.add(firstDirectSelector,gbc);
		gbc.gridy=2;
		this.add(firstAxisPulse,gbc);
		gbc.gridy=3;
		
		this.add(SecondDirectSelector,gbc);
		gbc.gridy=4;
		this.add(secondAxisPulse,gbc);
		gbc.gridy=5;
		this.add(setButton,gbc);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//this.userControl.sendCMD(tip);
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
