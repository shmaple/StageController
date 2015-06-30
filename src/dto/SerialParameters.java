package dto;
/* @(#)SerialParameters.java	1.5 98/07/17 SMI
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

/**
A class that stores parameters for serial ports. 
*/
public class SerialParameters {

    private String portName;
    private int baudRate;
    private int flowControl;
    private int databits;
    private int stopbits;
    private int parity;
    private String delimiter;


    /**
    Default constructer. Sets parameters to no port, 9600 baud, no flow 
    control, 8 data bits, 1 stop bit, no parity.
    */
    public SerialParameters () {
	this("COM1", 
	     9600, 
	     SerialPort.FLOWCONTROL_NONE,
	     SerialPort.DATABITS_8,
	     SerialPort.STOPBITS_1,
	     SerialPort.PARITY_NONE );
    }		

    /**
    Paramaterized constructer.

    @param portName The name of the port.
    @param baudRate The baud rate.
    @param flowControlIn Type of flow control for receiving.
    @param flowControlOut Type of flow control for sending.
    @param databits The number of data bits.
    @param stopbits The number of stop bits.
    @param parity The type of parity.
    */
    public SerialParameters(String portName, 
			    int baudRate,
			    int flowControl,
			    int databits,
			    int stopbits,
			    int parity) {

    	this.portName = portName;
    	this.baudRate = baudRate;
    	this.flowControl = flowControl;
    	this.databits = databits;
    	this.stopbits = stopbits;
    	this.parity = parity;
    	this.delimiter=Delimiter.SCRLF;
    }
    /**
    Paramaterized constructer.

    @param portName The name of the port.
    @param baudRate The baud rate.
    @param flowControlIn Type of flow control for receiving.
    @param flowControlOut Type of flow control for sending.
    @param databits The number of data bits.
    @param stopbits The number of stop bits.
    @param parity The type of parity.
    @param delimiter The end char of communication.
    */
    public SerialParameters(String portName, 
			    int baudRate,
			    int flowControl,
			    int databits,
			    int stopbits,
			    int parity,
			    String delimiter) {
    	this(portName,baudRate,flowControl,databits,stopbits,parity);
    	this.delimiter=delimiter;
    }
    public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		if(delimiter=="CR")
		{
			this.delimiter =Delimiter.SCR;
			return;
		}
		if(delimiter=="LF")
		{
			this.delimiter =Delimiter.SLF;
			return;
		}
			this.delimiter =Delimiter.SCRLF;
		}

	/**
    *���ô��ں�
    @param portName ���ڱ��.
    */
    public void setPortName(String portName) {
	this.portName = portName;
    }

    /**
    *��ȡ���ڱ��
    @return ��ǰ���ڱ��.
    */
    public String getPortName() {
	return portName;
    }

    /**
    *���ò�����
    @param baudRate ������.
    */
    public void setBaudRate(int baudRate) {
	this.baudRate = baudRate;
    }

    /**
     *���ò�����
     @param baudRate ������.
     */
    public void setBaudRate(String baudRate) {
	this.baudRate = Integer.parseInt(baudRate);
    }

    /**
    * ��ȡ������.
    @return ��ǰ������
    */
    public int getBaudRate() {
	return baudRate;
    }


    /**
    *���������Ʒ�ʽ
    *@param flowControl �����Ʒ�ʽ.
    *flowcontrol - Can be a bitmask combination of
	*FLOWCONTROL_NONE: no flow control
	*FLOWCONTROL_RTSCTS_IN: RTS/CTS (hardware) flow control for input
	*FLOWCONTROL_RTSCTS_OUT:	RTS/CTS (hardware) flow control for output
	*FLOWCONTROL_XONXOFF_IN:	XON/XOFF (software) flow control for input
	*FLOWCONTROL_XONXOFF_OUT:	XON/XOFF (software) flow control for output
	*Throws:UnsupportedCommOperationException 
	*if any of the flow control mode was not supported by the underline OS, or if input
	*and output flow control are set to different values, i.e. one hardware and one software. 
	*The flow control mode will revert to the value before the call was made.
    */
    public void setFlowControl(int flowControl) {
	this.flowControl = flowControl;
    }
    
    /**
     *���������Ʒ�ʽ
     @param flowControl �����Ʒ�ʽ.
     */
    public void setFlowControl(String flowControl) {
	this.flowControl = stringToFlow(flowControl);
    }

    /** 
    *��ȡ�����Ʒ�ʽ
    @return ��ǰ�����Ʒ�ʽ
    */
    public int getFlowControl() {
	return flowControl;
    }
    
    /** 
    *��������λ
    @param databits ����λ.
    */
    public void setDatabits(int databits) {
	this.databits = databits;
    }

    /** 
    *��������λ
    @param databits ����λ.
    */
    public void setDatabits(String databits) {
	if (databits.equals("5")) {
	    this.databits = SerialPort.DATABITS_5;
	}
	if (databits.equals("6")) {
	    this.databits = SerialPort.DATABITS_6;
	}
	if (databits.equals("7")) {
	    this.databits = SerialPort.DATABITS_7;
	}
	if (databits.equals("8")) {
	    this.databits = SerialPort.DATABITS_8;
	}
    }

    /**
    *��ȡ����λ
    @return ��ǰ����λ
    */
    public int getDatabits() {
	return databits;
    }


    /**
    *����ֹͣλ
    @param stopbits ֹͣλ
    */
    public void setStopbits(int stopbits) {
	this.stopbits = stopbits;
    }

    /**
     *����ֹͣλ
     @param stopbits ֹͣλ
     */
    public void setStopbits(String stopbits) {
	if (stopbits.equals("1")) {
	    this.stopbits = SerialPort.STOPBITS_1;
	}
	if (stopbits.equals("1.5")) {
	    this.stopbits = SerialPort.STOPBITS_1_5;
	}
	if (stopbits.equals("2")) {
	    this.stopbits = SerialPort.STOPBITS_2;
	}
    }

    /**
    *��ȡֹͣλ
    @return ��ǰֹͣλ
    */
    public int getStopbits() {
	return stopbits;
    }

    /**
    *����У��λ.
    @param parity У��λ
    */
    public void setParity(int parity) {
	this.parity = parity;
    }

    /**
    *����У��λ.
    @param parity У��λ
    */
    public void setParity(String parity) {
	if (parity.equals("None")) {
	    this.parity = SerialPort.PARITY_NONE;
	}
	if (parity.equals("Even")) {
	    this.parity = SerialPort.PARITY_EVEN;
	}
	if (parity.equals("Odd")) {
	    this.parity = SerialPort.PARITY_ODD;
	}
    }

    /**
    *��ȡУ��λ
    @return ��ǰУ��λ
    */
    public int getParity() {
	return parity;
    }

   

    /**
    Converts a <code>String</code> describing a flow control type to an
    <code>int</code> type defined in <code>SerialPort</code>.
    @param flowControl A <code>string</code> describing a flow control type.
    @return An <code>int</code> describing a flow control type.
    */
    private int stringToFlow(String flowControl) {
	if (flowControl.equals("None")) {
	    return SerialPort.FLOWCONTROL_NONE;
	}
	if (flowControl.equals("Xon/Xoff")) {
	    return SerialPort.FLOWCONTROL_XONXOFF_IN|SerialPort.FLOWCONTROL_XONXOFF_OUT;
	    
	}
	if (flowControl.equals("RTS/CTS")) {
	    return SerialPort.FLOWCONTROL_RTSCTS_IN|SerialPort.FLOWCONTROL_RTSCTS_OUT;
	}
	return SerialPort.FLOWCONTROL_NONE;
    }
 
}
