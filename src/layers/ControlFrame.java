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
		
		
		//�������ݱ������
		SerialData fineSerialData=new SerialData();
		//���崮�����ö���
		SerialParameters FineSerialParameters=new SerialParameters();
		//���崮�ڲ������󣬲�װ�ش������ö������ݱ������
		SerialOperator FineSerialOperator=new SerialOperator(FineSerialParameters,fineSerialData);
		
		//�����û����ƶ��󣬲�װ�ش��ڲ�������
		UserControl fineUserControl=new UserControl(FineSerialOperator);
		//���������壬��װ���û����ƶ���
		FineControlPanel fineControlPanel=new FineControlPanel(fineUserControl,fineSerialData);
		serialOperator.addObserver(controlPanel);
		controlPanel.setSerialData(fineSerialData);
		
		//���崰�ڣ������ؿ������
		JFrame frame=new ControlFrame(controlPanel,fineControlPanel);
		//�򿪴���
		//serialOperator.commOpen();
		

	}

}
