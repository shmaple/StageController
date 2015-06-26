package layers;

import javax.swing.JComboBox;

public class AxisSelector extends JComboBox<Item>{
	public AxisSelector()
	{
		addItem(new Item("Both Axis","W"));
		addItem(new Item("First Axis","1"));
		addItem(new Item("Second Axis","2"));
	}

}
