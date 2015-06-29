package layers;

import gnu.io.CommPortIdentifier;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.SerialParameters;

public class SerialPortConfig extends JPanel 
{
	private JComboBox<String> portName;
	private JComboBox<String> boudRate;
	private JComboBox<String> dataBit;
	private JComboBox<String> parity;
	private JComboBox<String> flowControl;
	private SerialParameters serialParameters;
	
	public SerialPortConfig()
	{
		
	}
	
	public void initComponent()
	{
		listPortChoices();
		boudRate=new JComboBox<String>(new String[]{"2400","4800","9600","19200","38400"});
		dataBit=new JComboBox<String>(new String[]{"6","7","8"});
		parity=new JComboBox<String>(new String[]{"None","Even","Odd"});
		flowControl=new JComboBox<String>(new String[]{"None","RTS/CTS","Xon/Xoff"});
	}
	public void addComponent()
	{
		GridBagLayout gridBagLayout= new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBagLayout);
		setBorder(BorderFactory.createTitledBorder("Serial port confige"));
		//gbc.gridwidth=1;
		this.fixComponent(new JLabel("Port Name"), gbc, 0, 0, 1, 1);
	}
    private void fixComponent(JComponent component, GridBagConstraints gbc, int gridX, int gridY,
			int gridWidth, int gridHeight) 
    {
		gbc.gridx=gridX;
		gbc.gridy=gridY;
		gbc.gridheight=gridHeight;
		gbc.gridwidth=gridWidth;
		this.add(component, gbc);
		
	}

	void listPortChoices() {
        CommPortIdentifier portId;
        Enumeration en = CommPortIdentifier .getPortIdentifiers();
        // iterate through the ports.
        while (en.hasMoreElements()) {
            portId = (CommPortIdentifier) en.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            	portName.addItem(portId.getName());
            }
        }
    }
}
