package layers;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import comm.SerialConnectionException;
import comm.SerialOperator;
import control.UserControl;
import dto.SerialData;
import dto.SerialParameters;

public class ControlFrame extends JFrame {
		public ControlFrame(ControlPanel controlPanel,FineControlPanel fineControlPanel)
		{
			this.setLayout(new FlowLayout(0));
			//this.setContentPane(controlPanel);
			this.getContentPane().add(controlPanel);
			this.getContentPane().add(fineControlPanel);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setSize(840, 800);
			this.setVisible(true);
		}
	 

	public static void main(String[] args) throws SerialConnectionException {
		//定义数据保存对象
		SerialData serialData=new SerialData();
		//定义串口配置对象
		SerialParameters serialParameters=new SerialParameters();
		//定义串口操作对象，并装载串口配置对象及数据保存对象
		SerialOperator serialOperator=new SerialOperator(serialParameters,serialData);
		
		//定义用户控制对象，并装载串口操作对象
		UserControl userControl=new UserControl(serialOperator);
		//定义控制面板，并装载用户控制对象
		ControlPanel controlPanel=new ControlPanel(userControl,serialData);
		serialOperator.addObserver(controlPanel);
		controlPanel.setSerialData(serialData);
		
		
		//定义数据保存对象
		SerialData fineSerialData=new SerialData();
		//定义串口配置对象
		SerialParameters FineSerialParameters=new SerialParameters();
		//定义串口操作对象，并装载串口配置对象及数据保存对象
		SerialOperator FineSerialOperator=new SerialOperator(FineSerialParameters,fineSerialData);
		
		//定义用户控制对象，并装载串口操作对象
		UserControl fineUserControl=new UserControl(FineSerialOperator);
		//定义控制面板，并装载用户控制对象
		FineControlPanel fineControlPanel=new FineControlPanel(fineUserControl,fineSerialData);
		serialOperator.addObserver(controlPanel);
		controlPanel.setSerialData(fineSerialData);
		
		//定义窗口，并加载控制面板
		JFrame frame=new ControlFrame(controlPanel,fineControlPanel);
		//打开串口
		//serialOperator.commOpen();
		

	}

}
