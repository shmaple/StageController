package layers;

import javax.swing.JFrame;

import comm.SerialConnectionException;
import comm.SerialOperator;
import control.CommControl;
import control.UserControl;
import dto.SerialData;
import dto.SerialParameters;

public class ControlFrame extends JFrame {
		public ControlFrame(ControlPanel controlPanel)
		{
			this.setContentPane(controlPanel);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setSize(800, 800);
			this.setVisible(true);
		}
	 

	public static void main(String[] args) throws SerialConnectionException {
		//定义数据保存对象
		SerialData serialData=new SerialData();
		//定义串口配置对象
		SerialParameters serialParameters=new SerialParameters();
		//定义串口操作对象，并装载串口配置对象及数据保存对象
		SerialOperator serialOperator=new SerialOperator(serialParameters,serialData);
		
		//定义串口控制对象，并装载串口操作对象
		CommControl commControl=new CommControl(serialOperator);
		//定义用户控制对象，并装载串口控制对象
		UserControl userControl=new UserControl(commControl);
		//定义控制面板，并装载用户控制对象
		ControlPanel controlPanel=new ControlPanel(userControl,serialData);
		controlPanel.setSerialData(serialData);
		//定义窗口，并加载控制面板
		JFrame frame=new ControlFrame(controlPanel);
		//打开串口
		serialOperator.commOpen();

	}

}
