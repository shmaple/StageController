package ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dto.SerialParameters;

public class ConfigDialog extends Dialog implements ActionListener{

	public ConfigDialog(Frame frame, String title,SerialParameters sp) {
		super(frame, title, true);
		this.setSize(300, 200);
		PortConfigPanel pcp=new PortConfigPanel(sp); 
		this.add(pcp, BorderLayout.CENTER);
		JButton jb=new JButton("È·¶¨");
		jb.addActionListener(this);
		this.add(jb,BorderLayout.SOUTH);
		//setLocation(frame.getLocationOnScreen().x + 30, 
		//	    frame.getLocationOnScreen().y + 30);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		dispose();
		
	}

}
