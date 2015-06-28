package layers;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.UserControl;
import dto.SerialData;

public class FineControlPanel extends JPanel implements Observer {
	private UserControl userControl;
	private SerialData serialData;
	private FineControlPanel cp=null;
	private JTextArea textArea=null;
	public FineControlPanel(UserControl userControl,SerialData serialData)
	{
		this.userControl=userControl;
		this.serialData=serialData;
		Layer originJogLayer=new FineOrginJogLayer(10,10,260,292,"Fine Origin and Jog");//250
		Layer fineTravelLayer=new FineTravelLayer(10,80,250,292,"FineTravel");//238
		Layer speedSettingLayer=new SpeedSettingLayer(10,280,280,292,"Speed Setting");
		
		cp=this;
		originJogLayer.setUserControl(userControl);
		fineTravelLayer.setUserControl(userControl);
		speedSettingLayer.setUserControl(userControl);
		add(speedSettingLayer);
		add(originJogLayer);
		
		add(fineTravelLayer);
		textArea=new JTextArea(10,100);
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
	@Override
	public void update(Observable o, Object arg) {
		this.textArea.append(arg.toString());
		
	}

}