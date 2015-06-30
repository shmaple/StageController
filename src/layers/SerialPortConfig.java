package layers;

import gnu.io.CommPortIdentifier;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.SerialParameters;

public class SerialPortConfig extends JPanel implements ActionListener 
{
	private JComboBox<String> portName;
	private JComboBox<String> boudRate;
	private JComboBox<String> dataBit;
	private JComboBox<String> parity;
	private JComboBox<String> flowControl;
	private SerialParameters serialParameters;
	private JComboBox<String> delimit;
	private JComboBox<String> stopBit;
	private JButton autoButton;
	private JButton setButton;
	private JButton saveButton;
	public SerialPortConfig(SerialParameters serialParameters)
	{
		
		initComponent();
		this.serialParameters=serialParameters;
		addComponent();
	}
	
	public void initComponent()
	{
		portName=new JComboBox<String>();
		listPortChoices();
		boudRate=new JComboBox<String>(new String[]{"2400","4800","9600","19200","38400"});
		boudRate.setSelectedIndex(2);
		dataBit=new JComboBox<String>(new String[]{"6","7","8"});
		dataBit.setSelectedIndex(2);
		parity=new JComboBox<String>(new String[]{"None","Even","Odd"});
		parity.setSelectedIndex(0);
		flowControl=new JComboBox<String>(new String[]{"None","RTS/CTS","Xon/Xoff"});
		flowControl.setSelectedIndex(0);
		delimit=new JComboBox<String>(new String[]{"CR","LF","CR+LF"});
		delimit.setSelectedIndex(2);
		stopBit=new JComboBox<String>(new String[]{"1","1.5","2"});
		stopBit.setSelectedIndex(2);
		autoButton=new JButton("Auto Detect");
		setButton=new JButton("Setting");
		saveButton=new JButton("Save");
		serialParameters=new SerialParameters();
		autoButton.addActionListener(this);
		setButton.addActionListener(this);
		saveButton.addActionListener(this);
	}
	public void addComponent()
	{
		 GridBagLayout gridBagLayout= new GridBagLayout();
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.fill=GridBagConstraints.BOTH;
		 gbc.insets=new Insets(5,5,5,5);
		setLayout(gridBagLayout);
		setBorder(BorderFactory.createTitledBorder("Serial port confige"));
		//gbc.gridwidth=1;
		this.fixComponent(new JLabel("Port Name:",JLabel.RIGHT), gbc, 0, 0, 1, 1);
		this.fixComponent(new JLabel("BoudRate:",JLabel.RIGHT), gbc, 2, 0, 1, 1);
		this.fixComponent(new JLabel("DataBit:",JLabel.RIGHT), gbc, 0, 1, 1, 1);
		this.fixComponent(new JLabel("Parity:",JLabel.RIGHT), gbc, 2, 1, 1, 1);
		this.fixComponent(new JLabel("stopBit:",JLabel.RIGHT), gbc, 0, 2, 1, 1);
		this.fixComponent(new JLabel("Flow:",JLabel.RIGHT), gbc, 2, 2, 1, 1);
		this.fixComponent(new JLabel("Delimit:",JLabel.RIGHT), gbc, 0, 3, 1, 1);
		this.fixComponent(portName, gbc, 1, 0, 1, 1);
		this.fixComponent(boudRate, gbc, 3, 0, 1, 1);
		this.fixComponent(dataBit, gbc, 1, 1, 1, 1);
		this.fixComponent(parity, gbc, 3, 1, 1, 1);
		this.fixComponent(stopBit, gbc, 1, 2, 1, 1);
		this.fixComponent(delimit, gbc, 1, 3, 1, 1);
		this.fixComponent(flowControl, gbc, 3, 2, 1, 1);
		this.fixComponent(autoButton, gbc, 2, 3, 2, 1);
		this.fixComponent(setButton, gbc, 0, 4, 2, 1);
		this.fixComponent(saveButton, gbc, 2, 4, 2, 1);
	}
    public void fixComponent(Component comp, GridBagConstraints gbc, int gridX, int gridY,
			int gridWidth, int gridHeight) 
    {
		gbc.gridx=gridX;
		gbc.gridy=gridY;
		gbc.gridheight=gridHeight;
		gbc.gridwidth=gridWidth;
		this.add(comp,gbc);
	
	
	}

	void listPortChoices() {
        CommPortIdentifier portId;
        Enumeration en = CommPortIdentifier .getPortIdentifiers();
        // iterate through the ports.
        while (en.hasMoreElements()) {
            portId = (CommPortIdentifier) en.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            	portName.addItem(portId.getName());
            	System.out.println(portId.getName());
            }
        }
    }
	public static void main(String[] args)
	{
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		SerialParameters sp=new SerialParameters();
		SerialPortConfig spc=new SerialPortConfig(sp);
		frame.add(spc);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton cmdButton=(JButton) e.getSource();
		if(cmdButton==setButton)
		{
			this.serialParameters.setPortName(portName.getSelectedItem().toString());
			this.serialParameters.setBaudRate(boudRate.getSelectedItem().toString());
			this.serialParameters.setDatabits(dataBit.getSelectedItem().toString());
			this.serialParameters.setStopbits(stopBit.getSelectedItem().toString());
			this.serialParameters.setDelimiter(delimit.getSelectedItem().toString());
			this.serialParameters.setFlowControl(flowControl.getSelectedItem().toString());
			this.serialParameters.setParity(parity.getSelectedItem().toString());
		}
		
	}
}
