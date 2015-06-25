package control;

import comm.SerialOperator;

import ui.ControlPanel;

public class UserControl{

	private SerialOperator serialOperator;
	private ControlPanel controlPanel;
	
	public UserControl()
	{
		super();
	}
	
	public UserControl(SerialOperator serialOperator) {
			this.serialOperator = serialOperator;
			
			
	}
	public void setControlPanel(ControlPanel cp)
	{
		this.controlPanel=cp;
	}

	
 
	public void sendCMD(String Cmd)
	{
		this.serialOperator.commSend(Cmd);
	}
}
