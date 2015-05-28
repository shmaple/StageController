package ui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import control.UserControl;

import java.awt.Color;

public class ControlFrame extends JFrame {
	
	private UserControl userControl;
	

	/**
	 * Create the frame.
	 */
	public ControlFrame(ControlPanel ControlPanel,UserControl uc) {
		super();
		this.userControl=uc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 332);
		createMenu();
		createStatusBar();
		getContentPane().add(ControlPanel,BorderLayout.CENTER);
		//setContentPane(ControlPanel);
		
		
		 
	}
	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.setActionCommand("save");
		mntmSave.addActionListener(userControl);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		mntmLoad.setActionCommand("load");
		mntmLoad.addActionListener(userControl);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.setActionCommand("exit");
		mntmExit.addActionListener(userControl);
		
		JMenu mnConnect = new JMenu("Connect");
		menuBar.add(mnConnect);
		
		JMenuItem mntmGsc = new JMenuItem("GSC-02");
		mnConnect.add(mntmGsc);
		mntmGsc.setActionCommand("GSC");
		mntmGsc.addActionListener(userControl);
		
		JMenuItem mntmFine = new JMenuItem("FINE-503");
		mnConnect.add(mntmFine);
		mntmFine.setActionCommand("FINE");
		mntmFine.addActionListener(userControl);
		
		JMenu mnTool = new JMenu("Tool");
		menuBar.add(mnTool);
		
		JMenuItem mntmConfig = new JMenuItem("Config");
		mnTool.add(mntmConfig);
		mntmConfig.setActionCommand("config");
		mntmConfig.addActionListener(userControl);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpContent = new JMenuItem("Help Content");
		mnHelp.add(mntmHelpContent);
		mntmHelpContent.setActionCommand("help");
		mntmHelpContent.addActionListener(userControl);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.setActionCommand("about");
		mntmAbout.addActionListener(userControl);
	}
	private void createStatusBar()
	{
		JToolBar statusBar = new JToolBar();
		statusBar.setFloatable(false);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		JLabel lblComm = new JLabel("Comm\uFF1A");
		statusBar.add(lblComm);
		
		JLabel lblNewLabel = new JLabel("GSC-02");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblNewLabel.setBackground(Color.GREEN);
		statusBar.add(lblNewLabel);
		
		JLabel lblFine = new JLabel("FINE-503");
		lblFine.setOpaque(true);
		lblFine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblFine.setBackground(Color.GREEN);
		statusBar.add(lblFine);
	}

}
