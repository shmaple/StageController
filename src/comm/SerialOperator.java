package comm;
/* @(#)SerialConnection.java	1.6 98/07/17 SMI
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license
 * to use, modify and redistribute this software in source and binary
 * code form, provided that i) this copyright notice and license appear
 * on all copies of the software; and ii) Licensee does not utilize the
 * software in a manner which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind.
 * ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THE
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS
 * BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING
 * OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control
 * of aircraft, air traffic, aircraft navigation or aircraft
 * communications; or in the design, construction, operation or
 * maintenance of any nuclear facility. Licensee represents and
 * warrants that it will not use or redistribute the Software for such
 * purposes.
 */

import gnu.io.*;

import java.io.*;
import java.util.TooManyListenersException;

import dto.SerialData;
import dto.SerialParameters;

/**
*���ڲ����࣬�����ڳ��ò�������
*/
public class SerialOperator implements SerialPortEventListener 
 {
   // private SerialDemo parent;
    /**
     * �����豸���ص���Ӧ��Ϣ
     */
    private String response;
    /**
     * ���ڲ���������
     */
    private SerialParameters parameters;
    /**
     * ���������
     */    
    private OutputStream os;
    /**
     * ����������
     */  
    private InputStream is;
    /**
     * �����豸������
     */  
    private CommPortIdentifier portId;
    /**
     * ����
     */  
    private SerialPort sPort;
    /**
     * ���ڴ򿪹ر�״̬ 
     */  
    private boolean open;
    /**
     * ����������
     */  
	private String Command;
	
	private SerialData serialData;


    public void setCommand(String command) {
		Command = command;
	}

	/**
    *���캯��������һ�����ڲ����࣬����ʼ����ز���
    *@param parameters ����������
    */
    public SerialOperator(SerialParameters parameters,SerialData sd) 
    {
		//this.parent = parent;
		this.parameters = parameters;
		open = false;
		this.serialData=sd;
   }

   /**
   *ʹ�� parameters�����õĲ�����ָ���Ĵ���ͨ���豸�������ó�ʱΪ30s������޷����򷵻�
   *  <code>SerialConnectionException</code>���˳�.
   */
   public void commOpen() throws SerialConnectionException 
   {
		// ��ȡ CommPortIdentifier ��������ȡָ���Ĵ���.
		try 
		{
		    portId = 
			 CommPortIdentifier.getPortIdentifier(parameters.getPortName());
		} 
		catch (NoSuchPortException e) 
		{
		    throw new SerialConnectionException(e.getMessage());
		}
	
		//��ȡ���ڶ��󣬲����Դ�
		try 
		{
		    sPort = (SerialPort)portId.open("SerialDemo", 30000);
		} 
		catch (PortInUseException e) 
		{
		    throw new SerialConnectionException(e.getMessage());
		}
	
		// ���ô��ڲ������������ʧ�ܣ��رմ��ڣ����׳��쳣
		try {
		    setConnectionParameters();
		} catch (SerialConnectionException e) {	
		    sPort.close();
		    throw e;
		}
	
		// ��ȡ���ڵ�������������������ȡʧ�ܣ��رմ��ڣ����׳��쳣
		try {
		    os = sPort.getOutputStream();
		    is = sPort.getInputStream();
		} catch (IOException e) {
		    sPort.close();
		    throw new SerialConnectionException("Error opening i/o streams");
		}
	
		// Create a new KeyHandler to respond to key strokes in the 
		// messageAreaOut. Add the KeyHandler as a keyListener to the 
		// messageAreaOut.
		//keyHandler = new KeyHandler(os);
	//	command.addKeyListener(keyHandler);
	
		// �����¼��������������¼���notifyONXX����
		try {
		    sPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		    sPort.close();
		    throw new SerialConnectionException("too many listeners added");
		}
	
		// �����¼��������������¼���notifyOnDataAvailable�����ݵ����¼�
		sPort.notifyOnDataAvailable(true);
	
		// Set notifyOnBreakInterrup to allow event driven break handling.
		sPort.notifyOnBreakInterrupt(true);
	
		// Set receive timeout to allow breaking out of polling loop during
		// input handling.
		try {
		    sPort.enableReceiveTimeout(30);
		} catch (UnsupportedCommOperationException e) {
		}
		open = true;
		serialData.setOpen(true);
    }

    /**
    Sets the connection parameters to the setting in the parameters object.
    If set fails return the parameters object to origional settings and
    throw exception.
    */
    public void setConnectionParameters() throws SerialConnectionException 
    {
	// ���ô��ڲ������������ʧ�ܣ��ظ�֮ǰ������
	try {
	    sPort.setSerialPortParams(parameters.getBaudRate(),
				      parameters.getDatabits(),
				      parameters.getStopbits(),
				      parameters.getParity());
	} catch (UnsupportedCommOperationException e) {
		
	    throw new SerialConnectionException("Unsupported parameter");
	}

	// ����������ģʽ
	try {
	    sPort.setFlowControlMode(parameters.getFlowControlIn() 
			           | parameters.getFlowControlOut());
	} catch (UnsupportedCommOperationException e) {
	    throw new SerialConnectionException("Unsupported flow control");
	}
    }

    /**
    *�رմ�������
    */
    public void commClose() 
    {
		// If port is alread closed just return.
		if (!open)  return;
		// Remove the key listener.
		//	command.removeKeyListener(keyHandler);
		// Check to make sure sPort has reference to avoid a NPE.
		if (sPort != null) 
		{
		    try {
			// close the i/o streams.
		    	os.close();
		    	is.close();
		    } catch (IOException e) {
			System.err.println(e);
		    }
		    // Close the port.
		    sPort.close();

		}
		open = false;
		serialData.setOpen(false);
    }

    /**
    Reports the open status of the port.
    @return true if port is open, false if port is closed.
    */
    public boolean isOpen() {
	return open;
    }

    /**
    Handles SerialPortEvents. The two types of SerialPortEvents that this
    program is registered to listen for are DATA_AVAILABLE and BI. During 
    DATA_AVAILABLE the port buffer is read until it is drained, when no more
    data is availble and 30ms has passed the method returns. When a BI
    event occurs the words BREAK RECEIVED are written to the messageAreaIn.
    */

    public void serialEvent(SerialPortEvent e) {
 	// Determine type of event.
	switch (e.getEventType()) {

	    // Read data until -1 is returned. If \r is received substitute
	    // \n for correct newline handling.
	    case SerialPortEvent.DATA_AVAILABLE:
	    	int count=0;
	    	while (count==0)
	    	{
	    		try {
					count=is.available();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    	byte[] msg=new byte[count];
	    	try {
	    		is.read(msg);
	    		}
	    	catch (IOException e1) {
			// TODO Auto-generated catch block
	    		e1.printStackTrace();
	    		}
	       String str=new String(msg);
	       if(str.endsWith(parameters.getDelimiter())){
	    	this.response=str;
	    	serialData.setRespose(str);
	       }
		break;

	    // If break event append BREAK RECEIVED message.
	    case SerialPortEvent.BI:
		response="\n--- BREAK RECEIVED ---\n";
	}

    }   

    /**
     * ���ʹ�����������ӽ�����
     * @param cmd �����ַ���
     */
    public void commSend()
    {
    	String cmd=serialData.getCommand()+parameters.getDelimiter();
    	byte[] sendCMD=cmd.getBytes();
    	try {
			os.write(sendCMD);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("OutputStream write error: " + e);
		}
    }
    /**
     * ��ȡ������Ӧ��Ϣ
     * @return String
     */
	public String getResponse() {
		return response;
	}
}
   