package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.ControlPanel;

public class UserControl{

	private CommControl commControl;
	private ControlPanel controlPanel;
	
	public UserControl()
	{
		super();
	}
	
	public UserControl(CommControl commControl) {
			this.commControl = commControl;
			
			
	}
	public void setControlPanel(ControlPanel cp)
	{
		this.controlPanel=cp;
	}

	

	public void sendCMD(String Cmd)
	{
		this.commControl.sent(Cmd);
	}
}
