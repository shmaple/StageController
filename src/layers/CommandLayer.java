package layers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CommandLayer extends Layer implements ChangeListener{
	private AxisSelector axisSelector;
	private JButton stop;
	private JButton emergencyStop;
	private JButton logicalOriginSet;
	private JToggleButton hold;

	public CommandLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title);
		//this.gridLayout.setRows(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() {
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
		axisSelector=new AxisSelector();	
		
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
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(new JLabel("First Axis Direction:",JLabel.RIGHT),gbc);
		gbc.gridy=1;
		this.add(logicalOriginSet,gbc);
		gbc.gridy=2;
		this.add(emergencyStop,gbc);
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(axisSelector,gbc);
		gbc.gridy=1;
		
		this.add(hold,gbc);
		gbc.gridy=2;
		this.add(stop,gbc);
		
		gbc.gridy=3;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//this.userControl.sendCMD(tip);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JToggleButton jtb=(JToggleButton)e.getSource();
		if(jtb.isSelected())
			jtb.setText("Free Motor");
		else
			jtb.setText("Hold Motor");
		
	}

}
