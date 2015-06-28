package layers;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FineOrginJogLayer extends Layer implements ItemListener, ChangeListener {

	private AxisSelector axisSelector;
	private DirectSelector firstDirectSelector;
	private DirectSelector secondDirectSelector;
	private DirectSelector thirdDirectSelector;
	private JButton mechanicalButton;
	private JToggleButton jogButton;
	private JButton stopButton;
	private JButton emergencyStop;
	private JButton logicalButton;
	private JButton clearButton;
	public FineOrginJogLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title);
		
	}

	public void initComponent()
	{
		String mechanicalButtonTip=
				"It is a command to return the stage to the mechanical origin."
				+ " It returns to 0 step point （0mV output point） at open loop control, "
				+ "and 0nm point at closed loop control.";
		String jogButtonTip=
				"This command drives the stage continuously. The amount of driving steps depend on "
				+ "the speed on the speed number selected by the memory switch No.1. Drive command "
				+ "“G” is needed after this command is executed.";
		//定义轴选择器及方向选择器
		axisSelector=new AxisSelector(3);
		firstDirectSelector=new DirectSelector();
		secondDirectSelector=new DirectSelector();
		thirdDirectSelector=new DirectSelector();
		axisSelector.addItemListener(this);
		//定义命令按钮
		mechanicalButton =new JButton("Return mechanical Origin");
		mechanicalButton.setToolTipText(formatTip(mechanicalButtonTip));
		mechanicalButton.addActionListener(this);
		jogButton=new JToggleButton("JOG Start");
		jogButton.setToolTipText(formatTip(jogButtonTip));
		jogButton.addChangeListener(this);
		
		String stopTip="It is a command to stop the specified stage.";
		String emergencyTip="Stop the stage immediately and return to the mechanical origin ";
		String logicalButtonTip="It is a command to return the stage to the logical origin point."
				+ "(logical) origin to the current position of each axis.";
		String clearButtonTip="This command is used to excite the motor or to turn "
				+ "excitation off, making it possibleto move (rotate) stages "
				+ "manually.";
			
		
		stopButton=new JButton("Stop");
		stopButton.setToolTipText(formatTip(stopTip));
		
		emergencyStop=new JButton("Emergency Stop");
		emergencyStop.setToolTipText(formatTip(emergencyTip));
		
		logicalButton=new JButton("Return logical origin");
		logicalButton.setToolTipText(formatTip(logicalButtonTip));
		
		clearButton=new JButton("Clear coordinate");
		clearButton.setToolTipText(formatTip(clearButtonTip));
		clearButton.addChangeListener(this);
		
		stopButton.addActionListener(this);
		emergencyStop.addActionListener(this);
		logicalButton.addActionListener(this);
		clearButton.addActionListener(this);
		
	}
	@Override
	public void addComponent() {
		gbc.insets=new Insets(5,5,5,5);
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(new JLabel("Selet Axis:",JLabel.RIGHT),gbc);	
		gbc.gridy=1;
		this.add(new JLabel("First Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=2;
		this.add(new JLabel("Second Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=3;
		this.add(new JLabel("Third Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=4;
		this.add(mechanicalButton,gbc);
		gbc.gridy=5;
		this.add(logicalButton,gbc);
		//gbc.gridwidth=3;
		gbc.gridy=6;
		this.add(emergencyStop,gbc);
		
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(axisSelector,gbc);
		gbc.gridy=1;
		this.add(firstDirectSelector,gbc);
		gbc.gridy=2;
		this.add(secondDirectSelector,gbc);
		gbc.gridy=3;
		this.add(thirdDirectSelector,gbc);
		gbc.gridy=4;
		this.add(jogButton,gbc);
		gbc.gridy=5;
		this.add(clearButton);
		gbc.gridy=6;
		this.add(stopButton,gbc);
		
		
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
				this.firstDirectSelector.setEnabled(true);
				this.secondDirectSelector.setEnabled(false);
				this.thirdDirectSelector.setEnabled(false);
			}
			if(chosedValue.equals("2"))
			{
				this.firstDirectSelector.setEnabled(false);
				this.secondDirectSelector.setEnabled(true);
				this.thirdDirectSelector.setEnabled(false);
			}
			if(chosedValue.equals("3"))
			{
				this.firstDirectSelector.setEnabled(false);
				this.secondDirectSelector.setEnabled(false);
				this.thirdDirectSelector.setEnabled(true);
			}
			if(chosedValue.equals("W"))
			{
				this.firstDirectSelector.setEnabled(true);
				this.secondDirectSelector.setEnabled(true);
				this.thirdDirectSelector.setEnabled(true);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JToggleButton jtb=(JToggleButton)e.getSource();
		if(jtb==jogButton)
			jtb.setText(jtb.isSelected()?"Jog Stop":"Jog Start");
		
	}
}
