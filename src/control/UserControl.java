package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.ControlPanel;

public class UserControl  implements ActionListener{

	private CommControl commControl;
	//private ControlPanel controlPanel;
	
	
	public UserControl(CommControl commControl,ControlPanel cp) {
			this.commControl = commControl;
			//this.controlPanel=cp;
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("open")) commControl.open();
		if(cmd.equals("close")) System.out.println("close");//commControl.close();
		if(cmd.equals("sent")) {
			//controlPanel.generateCommand();
			commControl.sent();
		}
	
		if(cmd.equals("config")) commControl.showCfg();
		

		
	}

}
