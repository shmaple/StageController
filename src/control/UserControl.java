package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.ControlPanel;

public class UserControl  implements ActionListener{

	private CommControl commControl;
	private ControlPanel controlPanel;
	
	
	public UserControl(CommControl commControl) {
			this.commControl = commControl;
			
			
	}
	public void setControlPanel(ControlPanel cp)
	{
		this.controlPanel=cp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("save")){System.out.println(cmd);}
		if(cmd.equals("load")){System.out.println(cmd);}
		if(cmd.equals("GSC")){System.out.println(cmd);}
		if(cmd.equals("FINE")){System.out.println(cmd);}
		if(cmd.equals("config")){System.out.println(cmd);}
		if(cmd.equals("help")){System.out.println(cmd);}
		if(cmd.equals("about")){System.out.println(cmd);}
		if(cmd.equals("exit"))
			{
			System.exit(0);
			}
		if(cmd.equals("sent")) {
			controlPanel.generateCommand();
			//commControl.sent();
		}
	
		//if(cmd.equals("config")) commControl.showCfg();
		

		
	}

}
