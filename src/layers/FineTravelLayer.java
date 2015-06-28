package layers;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FineTravelLayer extends Layer implements ItemListener {
	private AxisSelector axisSelector;
	private DirectSelector firstDirectSelector;
	private DirectSelector secondDirectSelector;
	private DirectSelector thirdDirectSelector;
	private JTextField firstAxisPulse;
	private JTextField secondAxisPulse;
	private JTextField thirdAxisPulse;
	private JButton setButton;
	private ButtonGroup travelMode;
	private JRadioButton absRaidoButton;
	private JRadioButton relRadioButton;
	public FineTravelLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title);

	}

	@Override
	public void initComponent() {
		String commandTip=
				"It is a command to make an absolute or relative coordinate movement. This command is to "
				+ "specify the axis, direction, and amount of movement. Drive command “G” is needed after "
				+ "this command is executed. The absolute amount of movement is specified in nm unit or the "
				+ "number of steps according to the control method.<br/>"
				+"Open loop control: -8000 ～ 120000 steps(absolute),±128000 steps(relative).<br/>"
				+"Closed loop control：±999999nm.<br/>"
				+"However, it only moves within the range of acquisition data in hysteresis characteristic.";
		axisSelector=new AxisSelector(3);
		axisSelector.addItemListener(this);
		firstDirectSelector=new DirectSelector();
		secondDirectSelector=new DirectSelector();
		thirdDirectSelector=new DirectSelector();
		this.firstAxisPulse=new JTextField();
		this.secondAxisPulse=new JTextField();
		this.thirdAxisPulse=new JTextField();
		travelMode=new ButtonGroup();
		absRaidoButton=new JRadioButton("ABS");
		absRaidoButton.setToolTipText("Absolute movement");
		relRadioButton=new JRadioButton("REL");
		relRadioButton.setToolTipText("Relative movement");
		travelMode.add(absRaidoButton);
		travelMode.add(relRadioButton);
		travelMode.setSelected(absRaidoButton.getModel(), true);
		setButton =new JButton("GO");
		setButton.setToolTipText(formatTip(commandTip));
		setButton.addActionListener(this);
	}

	@Override
	public void addComponent() {
		gbc.insets=new Insets(3,3,3,3);
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
		this.add(new JLabel("Third Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=6;
		this.add(new JLabel("Third Axis Pulse:",JLabel.RIGHT),gbc);
		gbc.gridwidth=1;
		gbc.gridx=0;
		gbc.gridy=7;
		this.add(relRadioButton,gbc);
		gbc.gridx=1;
		this.add(absRaidoButton,gbc);
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(axisSelector,gbc);
		gbc.gridy=1;	
		this.add(firstDirectSelector,gbc);
		gbc.gridy=2;
		this.add(firstAxisPulse,gbc);
		gbc.gridy=3;
		this.add(secondDirectSelector,gbc);
		gbc.gridy=4;
		this.add(secondAxisPulse,gbc);
		gbc.gridy=5;
		this.add(thirdDirectSelector,gbc);
		gbc.gridy=6;
		this.add(thirdAxisPulse,gbc);
		gbc.gridy=7;
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
				this.firstDirectSelector.setEnabled(true);
				this.firstAxisPulse.setEditable(true);
				this.secondDirectSelector.setEnabled(false);
				this.secondAxisPulse.setEditable(false);
				this.thirdDirectSelector.setEnabled(false);
				this.thirdAxisPulse.setEditable(false);
			}
			if(chosedValue.equals("2"))
			{
				this.firstDirectSelector.setEnabled(false);
				this.firstAxisPulse.setEditable(false);
				this.secondDirectSelector.setEnabled(true);
				this.secondAxisPulse.setEditable(true);
				this.thirdDirectSelector.setEnabled(false);
				this.thirdAxisPulse.setEditable(false);
			}
			if(chosedValue.equals("3"))
			{
				this.firstDirectSelector.setEnabled(false);
				this.firstAxisPulse.setEditable(false);
				this.secondDirectSelector.setEnabled(false);
				this.secondAxisPulse.setEditable(false);
				this.thirdDirectSelector.setEnabled(true);
				this.thirdAxisPulse.setEditable(true);
			}
			if(chosedValue.equals("W"))
			{
				this.firstDirectSelector.setEnabled(true);
				this.firstAxisPulse.setEditable(true);
				this.secondDirectSelector.setEnabled(true);
				this.secondAxisPulse.setEditable(true);
				this.thirdDirectSelector.setEnabled(true);
				this.thirdAxisPulse.setEditable(true);
			}
		}
	}
}
