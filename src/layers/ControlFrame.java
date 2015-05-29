package layers;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ControlFrame extends JFrame {
	public ControlFrame()
	{
		
		Layer lay=new OrginLayer(10,10,200,200,"Mechanlcal Origin");
		Layer travelLayer=new TravelLayer(10,220,200,200,"Travel Layer");
		Layer jogLayer=new JogLayer(10,220,200,200,"JOG Layer");
		Layer speedSettingLayer=new SpeedSettingLayer(10,220,200,200,"Speed Setting");
		add(lay);
		add(travelLayer);
		add(jogLayer);
		add(speedSettingLayer);
		this.setLayout(new FlowLayout(0));
		
	}

	public static void main(String[] args) {
		JFrame frame=new ControlFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		frame.setVisible(true);
	}

}
