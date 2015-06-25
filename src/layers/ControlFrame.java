package layers;

import javax.swing.JFrame;

import comm.SerialConnectionException;
import comm.SerialOperator;
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
		//�������ݱ������
		SerialData serialData=new SerialData();
		//���崮�����ö���
		SerialParameters serialParameters=new SerialParameters();
		//���崮�ڲ������󣬲�װ�ش������ö������ݱ������
		SerialOperator serialOperator=new SerialOperator(serialParameters,serialData);
		
		//�����û����ƶ��󣬲�װ�ش��ڲ�������
		UserControl userControl=new UserControl(serialOperator);
		//���������壬��װ���û����ƶ���
		ControlPanel controlPanel=new ControlPanel(userControl,serialData);
		serialOperator.addObserver(controlPanel);
		controlPanel.setSerialData(serialData);
		//���崰�ڣ������ؿ������
		JFrame frame=new ControlFrame(controlPanel);
		//�򿪴���
		serialOperator.commOpen();

	}

}
