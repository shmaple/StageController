package main;

import comm.SerialOperator;
import control.CommControl;
import control.UserControl;
import dto.SerialData;
import dto.SerialParameters;
import ui.ControlFrame;
import ui.ControlPanel;

public class Main {
	public static void main(String[] args) {
					SerialParameters serialParameters=new SerialParameters();
					SerialData serialData=new SerialData();
					SerialOperator serialOperator=new SerialOperator(serialParameters,serialData);
					
					CommControl commContrl=new CommControl(serialOperator);
					
					ControlPanel controlPanel=new ControlPanel(serialData);
					UserControl userControl=new UserControl(commContrl,controlPanel);
					controlPanel.setUserControl(userControl);
							
					
					ControlFrame frame = new ControlFrame(controlPanel);
					frame.setVisible(true);
					frame.setTitle("Stage Controller System");
				
			}


}
