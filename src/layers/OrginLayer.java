package layers;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class OrginLayer extends Layer implements ItemListener {

	private AxisSelector axisSelector;
	private DirectSelector firstDirectSelector;
	private DirectSelector SecondDirectSelector;
	private JButton setButton;
	
	private static final String tip=
			"<html><body>This command is used to detect the mechanical origin for a stage <br>"
			+ "and set that position as the origin. Once the mechanical origin <br>"
			+ "has been detected, the value displayed will be 0. Each axis moves <br>"
			+ "at the following constant conditions: Minimum speed (S):500PPS, <br>"
			+ "Maximum speed (F): 5000PPS, Acceleration/ Deceleration time (R): <br>"
			+ "200mS.Axes to home are depending on the DIP Switch settings</body></html>";
	
	public OrginLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title,tip);
		
	}

	public void initComponent()
	{
		String commandTip="This command is used to detect the mechanical origin for a stage"
				+ "and set that position as the origin. Once the mechanical origin "
				+ "has been detected, the value displayed will be 0. Each axis moves "
				+ "at the following constant conditions: Minimum speed (S): 500PPS, "
				+ "Maximum speed (F): 5000PPS, Acceleration/Deceleration time (R): "
				+ "200mS. Axes to home are depending on the DIP Switch settings";
		//定义轴选择器及方向选择器
		axisSelector=new AxisSelector();
		firstDirectSelector=new DirectSelector();
		SecondDirectSelector=new DirectSelector();
		axisSelector.addItemListener(this);
		//定义命令按钮
		setButton =new JButton("Return Origin");
		setButton.setToolTipText(formatTip(commandTip));
		setButton.addActionListener(this);
	}
	@Override
	public void addComponent() {
		this.add(new JLabel("Selet Axis:"));		
		this.add(axisSelector);
	
		this.add(new JLabel("First Axis Direction:"));
		this.add(firstDirectSelector);
	
		this.add(new JLabel("Second Axis Direction:"));
		this.add(SecondDirectSelector);
		this.add(new JLabel());
		this.add(setButton);
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
}
