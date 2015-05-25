package control;

import comm.SerialConnectionException;
import comm.SerialOperator;

public class CommControl {
    private SerialOperator serialOperator;
	public CommControl(SerialOperator serialOperator) {
		super();
		this.serialOperator = serialOperator;
	}

	public void open() {
		// TODO Auto-generated method stub
		try {
			this.serialOperator.commOpen();
			System.out.println("hello");
		} catch (SerialConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		this.serialOperator.commClose();
	}

	public SerialOperator getSerialOperator() {
		return serialOperator;
	}

	public void sent() {
		// TODO Auto-generated method stub
		
		this.serialOperator.commSend();
	}

	public void showCfg() {
		// TODO Auto-generated method stub
		
	}

}
