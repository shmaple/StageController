package layers;

import javax.swing.JComboBox;

public class AxisSelector extends JComboBox<Item>{
	final static String[] axisName={"Fist Axis","Second Axis","Third Axis"};
	public AxisSelector(int axisNumber)
	{
		addItem(new Item("All Axis","W"));
		for(int i=0;i<axisNumber;i++)
		addItem(new Item(axisName[i],Integer.toString(i+1)));
	}

}
