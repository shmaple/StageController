package layers;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class ControlFrame extends JFrame {
	public ControlFrame()
	{
		
		Layer lay=new OrginLayer(10,10,380,70,"Mechanlcal Origin");
		Layer travelLayer=new TravelLayer(10,80,380,100,"Travel Layer");
		Layer jogLayer=new JogLayer(10,180,380,100,"JOG Layer");
		Layer speedSettingLayer=new SpeedSettingLayer(10,280,380,130,"Speed Setting");
		Layer commandLayer=new CommandLayer(10,410,380,100,"Command");
		CardLayout cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		JTabbedPane tabbedPane = new JTabbedPane();
	      // tabbedPane.setLayout(null);
	        tabbedPane.addTab("≤‚ ‘“ª", null, lay);
	       
	        tabbedPane.addTab("≤‚ ‘∂˛", null, travelLayer);
	        tabbedPane.addTab("≤‚ ‘»˝", null, jogLayer);
	        tabbedPane.addTab("≤‚ ‘∂˛", null, speedSettingLayer);
	        tabbedPane.addTab("≤‚ ‘∂˛", null, commandLayer);
	        add(tabbedPane);
		//add(lay);
		//add(travelLayer);
		//add(jogLayer);
		//add(speedSettingLayer);
		//add(commandLayer);
		//this.setLayout(null);
		
	}

	public static void main(String[] args) {
		JFrame frame=new ControlFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		frame.setVisible(true);
	}

}
