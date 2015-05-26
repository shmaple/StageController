package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import control.UserControl;
import control.TestUserControl;
import dto.SerialData;
import dto.SerialParameters;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ControlPanel extends JPanel  {
	private UserControl userControl;
	private JTextArea msgOut;
	private JTextArea msgIn;
	private SerialData	serialData;
	private JFrame frame;
	/**
	 * Create the panel.
	 */
	public ControlPanel(SerialData	sd ,UserControl uc) {
		
		this.serialData=sd;
		this.userControl=uc;
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GridLayout(3, 1, 2, 2));
		
		 msgOut = new JTextArea();
		add(msgOut);
		
		 msgIn = new JTextArea();
		add(msgIn);
		
		JPanel commandPanel = new JPanel();
		add(commandPanel);
		
		JButton Open = new JButton("\u6253\u5F00");
		Open.setActionCommand("open");
		commandPanel.add(Open);
		Open.addActionListener(userControl);
		
		
		
		JButton Close = new JButton("\u5173\u95ED");
		Close.setActionCommand("close");
		commandPanel.add(Close);
		
		Close.addActionListener(userControl);
		
		JButton Sent = new JButton("\u53D1\u9001");
		Sent.setActionCommand("sent");
		commandPanel.add(Sent);
		Sent.addActionListener(userControl);
		//Sent.addActionListener(new ActionListener());
		
		JButton Cfg = new JButton("\u914D\u7F6E");
		Cfg.setActionCommand("config");
		commandPanel.add(Cfg);
		
		
	}
	
	
	public void setUc(UserControl uc) {
		this.userControl = uc;
	}


//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//	 
//		ConfigDialog cfd=new ConfigDialog(null,"≤Œ ˝≈‰÷√", new SerialParameters());
//	}
	public void  generateCommand()
	{
		this.serialData.setCommand(this.msgOut.getText());
	}

	public void paint(Graphics g)
	{
		 super.paint(g);
		if(this.serialData.getRespose()!=null)
		{
			this.msgIn.setText(this.serialData.getRespose());
			//repaint();
		}
	}
}
