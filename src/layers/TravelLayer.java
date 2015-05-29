package layers;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TravelLayer extends Layer {
	
	private static final String tip=
			"<html><body style=\"width:200px;text-align:justify;text-justify:inter-ideograph\">This command is to specify the axis of travel, direction, "
			+ "and the travel (number ofpulses). This command must always be followed "
			+ "by a drive (G) command. Travel is bymeans of acceleration/deceleration "
			+ "driving.</html></body>";
	private JComboBox<Item> axisSelector;
	private JComboBox<Item> firstDirectSelector;
	private JComboBox<Item> SecondDirectSelector;
	private JTextField firstAxisPulse;
	private JTextField secondAxisPulse;
	private JButton setButton;


	public TravelLayer(int x, int y, int w, int h, String title) {
		super(x, y, w, h, title, tip);
	}

	@Override
	public void initComponent() {
		
		axisSelector=new JComboBox<Item>();
		firstDirectSelector=new JComboBox<Item>();
		SecondDirectSelector=new JComboBox<Item>();
		
		axisSelector.addItem(new Item("First Axis","1"));
		axisSelector.addItem(new Item("Second Axis","2"));
		axisSelector.addItem(new Item("Both Axis","W"));
		firstDirectSelector.addItem(new Item("Postive","+"));
		firstDirectSelector.addItem(new Item("Negative","-"));
		
		SecondDirectSelector.addItem(new Item("Postive","+"));
		SecondDirectSelector.addItem(new Item("Negative","-"));
		this.firstAxisPulse=new JTextField();
		
		this.firstAxisPulse.setPreferredSize(SecondDirectSelector.getPreferredSize());
		this.secondAxisPulse=new JTextField();
		this.secondAxisPulse.setPreferredSize(SecondDirectSelector.getPreferredSize());
		setButton =new JButton("GO");
		//setButton.setPreferredSize(SecondDirectSelector.getPreferredSize());
		
	}

	@Override
	public void addComponent() {
		this.add(axisSelector);
		this.add(firstDirectSelector);
		this.add(firstAxisPulse);
		
		this.add(SecondDirectSelector);
		this.add(secondAxisPulse);
		this.add(setButton);
		
	}

}
