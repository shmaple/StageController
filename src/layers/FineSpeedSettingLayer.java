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

public class FineSpeedSettingLayer extends Layer implements ItemListener {
	
	private AxisSelector axisSelector;
	private JTextField firstAxisSpeed;
	private JTextField secondAxisSpeed;
	private JTextField thirdAxisSpeed;
	private JButton setButton;
	private JTextField firstAxisVotage;
	private JTextField secondAxisVotage;
	private JTextField thirdAxisVotage;
	private JButton checkVotageButton;
	private JRadioButton trackModeButton;
	private JRadioButton lockModeButton;
	private ButtonGroup closedMode;
	private JButton modeSetButton;
	private JButton hysteresisButton;
	public FineSpeedSettingLayer(int x, int y, int w, int h, String title
			) {
		super(x, y, w, h, title);
	//	this.gridLayout.setRows(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() {
		String commandTip=
						 "The speed number of \"SPEED SEL\" setting in the memory switch is set to "
						 + "initial value at power-on.This command allows you to change these "
						 + "initial settin.";
		String checkVotageButtonTip=
				 		 "It is a command which responds supplied voltage ( -10000 ~ +150000mV ).<BR/>"
				 		 + "( Value below 1mV is omitted )";
		String modeSetButtonTip=
		 		 "Change the tracking mode and the lock mode at closed loop control";
		String hysteresisButtonTip=
		 		 "This command read the displacement value by digital sensor and acquires hysteresis "
		 		 + "characteristic. 0mV is displayed at open loop control";
		axisSelector=new AxisSelector(3);
		axisSelector.addItemListener(this);
		firstAxisSpeed=new JTextField("1");
		secondAxisSpeed=new JTextField("1");
		thirdAxisSpeed=new JTextField("1");
		
		firstAxisVotage=new JTextField(7);
		secondAxisVotage=new JTextField(7);
		thirdAxisVotage=new JTextField(7);
		
		firstAxisVotage.setEditable(false);
		secondAxisVotage.setEditable(false);
		thirdAxisVotage.setEditable(false);
		setButton=new JButton("Setting");
		setButton.setToolTipText(formatTip(commandTip));
		setButton.addActionListener(this);
		checkVotageButton=new JButton("Check Votage");
		checkVotageButton.setToolTipText(formatTip(checkVotageButtonTip));
		checkVotageButton.addActionListener(this);
		
		closedMode=new ButtonGroup();
		trackModeButton=new JRadioButton("Track");
		trackModeButton.setToolTipText("Mode which follows the in-position according to the instructed value.");
		lockModeButton=new JRadioButton("Lock");
		lockModeButton.setToolTipText("Mode which does not follow the in-position according to the instructed value.");
	
		closedMode.add(trackModeButton);
		closedMode.add(lockModeButton);
		closedMode.setSelected(trackModeButton.getModel(), true);
		
		modeSetButton=new JButton("Set Mode");
		modeSetButton.setToolTipText(formatTip(modeSetButtonTip));
		modeSetButton.addActionListener(this);
		hysteresisButton=new JButton("Displacement Hysteresis");
		hysteresisButton.setToolTipText(formatTip(hysteresisButtonTip));
		hysteresisButton.addActionListener(this);
	}

	@Override
	public void addComponent() {
		gbc.insets=new Insets(5,3,4,5);
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(new JLabel("Selet Axis:",JLabel.RIGHT),gbc);
		gbc.gridwidth=1;
		gbc.gridx=1;
		gbc.gridy=1;
		this.add(new JLabel("Step Speed",JLabel.CENTER),gbc);


		gbc.gridx=2;
		gbc.gridy=1;
		this.add(new JLabel("Supply Votage",JLabel.CENTER),gbc);

		gbc.gridx=0;
		gbc.gridy=1;
		this.add(new JLabel("Axis",JLabel.RIGHT),gbc);	
		
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(new JLabel("First",JLabel.RIGHT),gbc);
		gbc.gridy=3;
		this.add(new JLabel("Second",JLabel.RIGHT),gbc);
		gbc.gridy=4;
		this.add(new JLabel("Third",JLabel.RIGHT),gbc);

		
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(axisSelector,gbc);
		gbc.gridwidth=1;
		gbc.gridx=1;
		gbc.gridy=2;
		this.add(firstAxisSpeed,gbc);
		gbc.gridy=3;
		this.add(secondAxisSpeed,gbc);
		gbc.gridy=4;
		this.add(thirdAxisSpeed,gbc);
		
		gbc.gridy=5;
		this.add(setButton,gbc);
		
		gbc.gridx=2;
		gbc.gridy=2;
		this.add(firstAxisVotage,gbc);
		gbc.gridy=3;
		this.add(secondAxisVotage,gbc);
		gbc.gridy=4;
		this.add(thirdAxisVotage,gbc);
		gbc.gridy=5;
		this.add(checkVotageButton,gbc);
		gbc.gridx=0;
		gbc.gridy=6;
		this.add(trackModeButton,gbc);
		gbc.gridx=1;
		this.add(lockModeButton,gbc);
		gbc.gridx=2;
		this.add(modeSetButton,gbc);
		gbc.gridwidth=3;
		gbc.gridx=0;
		gbc.gridy=7;
		this.add(hysteresisButton,gbc);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//this.userControl.sendCMD(tip);
		//System.out.print("hell");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED)
		{
			Item  chosedItem=(Item)e.getItem();
			String chosedValue=chosedItem.getValue();
			if(chosedValue.equals("1"))
			{
				this.firstAxisSpeed.setEnabled(true);
				this.secondAxisSpeed.setEnabled(false);
				this.thirdAxisSpeed.setEnabled(false);
			}
			if(chosedValue.equals("2"))
			{
				this.firstAxisSpeed.setEnabled(false);
				this.secondAxisSpeed.setEnabled(true);
				this.thirdAxisSpeed.setEnabled(false);
			}
			if(chosedValue.equals("3"))
			{
				this.firstAxisSpeed.setEnabled(false);
				this.secondAxisSpeed.setEnabled(false);
				this.thirdAxisSpeed.setEnabled(true);
			}
			if(chosedValue.equals("W"))
			{
				this.firstAxisSpeed.setEnabled(true);
				this.secondAxisSpeed.setEnabled(true);
				this.thirdAxisSpeed.setEnabled(true);
			}
		}
	}
		
	

}
