package layers;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.UserControl;
import dto.SerialData;

public class ControlPanel extends JPanel {
	private UserControl userControl;
	private SerialData serialData;
	public ControlPanel(UserControl userControl,SerialData serialData)
	{
		this.userControl=userControl;
		this.serialData=serialData;
		Layer orginLayer=new OrginLayer(10,10,380,70,"Mechanlcal Origin");
		Layer travelLayer=new TravelLayer(10,80,380,100,"Travel Layer");
		Layer jogLayer=new JogLayer(10,180,380,100,"JOG Layer");
		Layer speedSettingLayer=new SpeedSettingLayer(10,280,380,130,"Speed Setting");
		Layer commandLayer=new CommandLayer(10,410,380,100,"Command");
		
		 orginLayer.setUserControl(userControl);
		 travelLayer.setUserControl(userControl);
		 jogLayer.setUserControl(userControl);
		 speedSettingLayer.setUserControl(userControl);
		 commandLayer.setUserControl(userControl);
		
		add(orginLayer);
		add(jogLayer);
		add(speedSettingLayer);
		add(travelLayer);
		add(commandLayer);
		JTextArea textArea=new JTextArea(10,100);
		add(textArea);
		textArea.setText(this.serialData.getRespose());
		this.setLayout(new FlowLayout(0));
		
	}
	public void setUserControl(UserControl uc)
	{
		this.userControl=uc;
	}
	public UserControl getUserControl()
	{
		return this.userControl;
	}
	public void setSerialData(SerialData serialData)
	{
		this.serialData=serialData;
	}


}
