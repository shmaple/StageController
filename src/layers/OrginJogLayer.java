package layers;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OrginJogLayer extends Layer implements ItemListener, ChangeListener {

	private AxisSelector axisSelector;
	private DirectSelector firstDirectSelector;
	private DirectSelector SecondDirectSelector;
	private JButton setButton;
	private JToggleButton jogButton;
	private JButton stop;
	private JButton emergencyStop;
	private JButton logicalOriginSet;
	private JToggleButton hold;
	public OrginJogLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title);
		
	}

	public void initComponent()
	{
		String commandTip="This command is used to detect the mechanical origin for a stage"
				+ "and set that position as the origin. Once the mechanical origin "
				+ "has been detected, the value displayed will be 0. Each axis moves "
				+ "at the following constant conditions: Minimum speed (S): 500PPS, "
				+ "Maximum speed (F): 5000PPS, Acceleration/Deceleration time (R): "
				+ "200mS. Axes to home are depending on the DIP Switch settings";
		String commandTip1=
				"This command drives stages continuously (at a constant speed) at the starting "
				+ "speed(S). This command must always be followed by a drive (G) command. The stage "
				+ "will stop by an L command.";
		//定义轴选择器及方向选择器
		axisSelector=new AxisSelector();
		firstDirectSelector=new DirectSelector();
		SecondDirectSelector=new DirectSelector();
		axisSelector.addItemListener(this);
		//定义命令按钮
		setButton =new JButton("Return Origin");
		setButton.setToolTipText(formatTip(commandTip));
		setButton.addActionListener(this);
		jogButton=new JToggleButton("JOG Start");
		jogButton.setToolTipText(formatTip(commandTip1));
		jogButton.addChangeListener(this);
		
		String stopTip="When this command is executed, the stage decelerates "
				+ "and stops. but Stage does not stop even if at the time "
				+ "H:W.";
		String emergencyTip="This command stops all stages immediately, "
				+ "whatever the conditions.";
		String logicalOriginTip="This command is used to set electronic "
				+ "(logical) origin to the current position of each axis.";
		String holdTip="This command is used to excite the motor or to turn "
				+ "excitation off, making it possibleto move (rotate) stages "
				+ "manually.";
			
		
		stop=new JButton("Stop");
		stop.setToolTipText(formatTip(stopTip));
		
		emergencyStop=new JButton("Emergency Stop");
		emergencyStop.setToolTipText(formatTip(emergencyTip));
		
		logicalOriginSet=new JButton("Set logical origin");
		logicalOriginSet.setToolTipText(formatTip(logicalOriginTip));
		
		hold=new JToggleButton("Hold Motor");
		hold.setToolTipText(formatTip(holdTip));
		hold.addChangeListener(this);
		
		stop.addActionListener(this);
		emergencyStop.addActionListener(this);
		logicalOriginSet.addActionListener(this);
		hold.addActionListener(this);
		
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
		this.add(new JLabel("Second Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=3;
		this.add(setButton,gbc);
		gbc.gridy=4;
		this.add(logicalOriginSet,gbc);
		gbc.gridy=5;
		this.add(emergencyStop,gbc);
		
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(axisSelector,gbc);
		gbc.gridy=1;
		this.add(firstDirectSelector,gbc);
		gbc.gridy=2;
		this.add(SecondDirectSelector,gbc);
		gbc.gridy=3;
		this.add(jogButton,gbc);
		gbc.gridy=4;
		
		this.add(hold,gbc);
		gbc.gridy=5;
		this.add(stop,gbc);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
				this.firstDirectSelector.setEnabled(true);
			}
			if(chosedValue.equals("2"))
			{
				this.firstDirectSelector.setEnabled(false);
				this.SecondDirectSelector.setEnabled(true);
			}
			if(chosedValue.equals("W"))
			{
				this.firstDirectSelector.setEnabled(true);
				this.SecondDirectSelector.setEnabled(true);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JToggleButton jtb=(JToggleButton)e.getSource();
		if(jtb==jogButton)
			jtb.setText(jtb.isSelected()?"Jog Stop":"Jog Start");
		else
			jtb.setText(jtb.isSelected()?"Free Motor":"Hold Motor");
		
	}
}
