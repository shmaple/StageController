package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.CommControl;
import control.UserControl;
import dto.SerialParameters;

public class ControlFrame extends JFrame {

	

	/**
	 * Create the frame.
	 */
	public ControlFrame(ControlPanel ControlPanel) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 332);
		//contentPane = new ControlPanel(new UserControl(new CommControl()));
		 
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ControlPanel);
		
		 
	}

}
