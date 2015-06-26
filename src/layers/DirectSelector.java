package layers;

import javax.swing.JComboBox;

public class DirectSelector extends JComboBox<Item> {
	public DirectSelector()
	{
		addItem(new Item("Postive","+"));
		addItem(new Item("Negative","-"));
	}

}
