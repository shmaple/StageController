/*
 * @(#)SerialPortDisplay.java	1.16 98/10/20 SMI
 *
 * Author: Tom Corson
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
package BlackBox;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.awt.Panel;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.TooManyListenersException;

import gnu.io.*;

public class SerialPortDisplay extends Panel implements
		SerialPortEventListener, MouseListener, CommPortOwnershipListener {
	/**
	 * 串口管理类
	 */
	protected CommPortIdentifier portID;
	/**
	 * 串口实例
	 */
	protected SerialPort port = null;
	/**
	 * 串口输入流
	 */
	protected InputStream in;
	/**
	 * 串口输出流
	 */
	protected OutputStream out;
	/**
	 * 串口输出流Out 临时存储
	 */
	protected OutputStream outSave;
	protected boolean lineMonitor, silentReceive;
	/**
	 * 调制解调器模式
	 */
	protected boolean	modemMode;
	/**
	 * 串口是否打开
	 */
	protected boolean open = false;
	protected int numDataBits = 1;

	private int rcvDelay;
	/**
	 * 接收线程
	 */
	private Thread rcvThread = null;
	/**
	 * 父窗口引用
	 */
	private BlackBox owner;
	/**
	 * 是否使用多线程接收数据
	 */
	private boolean threadRcv; 
	private boolean friendly;
	private boolean waiting = false;

	Panel display, statePanel, textPanel;
	/**
	 * 串口名称标签
	 */
	Label portName = null;
	BaudRate baudRate;
	DataBits dataBits;
	Parity parity;
	StopBits stopBits;
	CtlSigDisplay ctlSigs;
	FlowCtlDisplay flowCtl;
	Receiver receiver;
	Transmitter transmitter;
	/**
	 * 串口面板构造函数
	 * @param portID 串口管理类
	 * @param threadRcv
	 * @param friendly
	 * @param silentReceive
	 * @param modemMode
	 * @param rcvDelay
	 * @param owner 父窗口引用
	 * @throws PortInUseException
	 */
	public SerialPortDisplay(CommPortIdentifier portID, boolean threadRcv,
			boolean friendly, boolean silentReceive, boolean modemMode,
			int rcvDelay, BlackBox owner) throws PortInUseException {
		super();

		this.setLayout(new BorderLayout());

		this.owner = owner;

		this.lineMonitor = false;

		this.outSave = null;

		this.portID = portID;

		this.threadRcv = threadRcv;

		this.friendly = friendly;

		this.rcvDelay = rcvDelay;

		this.silentReceive = silentReceive;

		this.modemMode = modemMode;

		//打开串口
		this.openBBPort();
	}

	/**
	 * @return
	 * @throws PortInUseException
	 */
	private boolean openBBPort() throws PortInUseException {
		/*
		 * If we are already open, do a close first.
		 */

		if (this.open) {
			this.closeBBPort();
		}

		/*
		 * Register an ownership listener so we can manage the port.
		 */

		this.portID.addPortOwnershipListener(this);

		/*
		 * Try to open the port
		 */

		try {
			//打开串口，制定owner为BLACKBOX，超时制定2s
			port = (SerialPort) portID.open("BlackBox", 2000);

			if (port == null) {
				System.out.println("Error opening port " + portID.getName());

				return false;
			}

			else {
				this.open = true;
				this.waiting = false;

				if (portName != null) {
					portName.setForeground(Color.green);
				}
			}

			/*
			 * Get the input stream
			 */

			try {
				//获取串口输入流
				in = this.port.getInputStream();
			}

			catch (IOException e) {
				System.out.println("Cannot open input stream");
			}

			/*
			 * Get the output stream
			 */
			//获取输出流
			try {
				if (modemMode) {
					out = new ConvertedOutputStream(port.getOutputStream());
				}

				else {
					out = port.getOutputStream();
				}
			}

			catch (IOException e) {
				System.out.println("Cannot open output stream");
			}

			/*
			 * Create the panel (if needed)
			 */

			this.createPanel();

			this.showValues();

			/*
			 * Setup an event listener for the port
			 */
			try {
				port.addEventListener(this);
			} catch (TooManyListenersException tmle) {
				tmle.printStackTrace();
			}
			/*
			 * These are the events we want to know about
			 */

			port.notifyOnCTS(true);
			port.notifyOnDSR(true);
			port.notifyOnRingIndicator(true);
			port.notifyOnCarrierDetect(true);
			port.notifyOnOverrunError(true);
			port.notifyOnParityError(true);
			port.notifyOnFramingError(true);
			port.notifyOnBreakInterrupt(true);
			port.notifyOnDataAvailable(true);
			port.notifyOnOutputEmpty(true);

			/*
			 * Create the receiver thread
			 */

			if (this.threadRcv && (rcvThread == null)) {
				//创建接收线程并命名为PORT.GETNAME.
				rcvThread = new Thread(this.receiver, "Rcv " + port.getName());
				//启动接收线程
				rcvThread.start();
			}

			else {
				rcvThread = null;
			}
		}

		catch (PortInUseException e) {
			System.out.println("Queueing open for " + portID.getName()
					+ ": port in use by " + e.currentOwner);

			if (portName != null) {
				portName.setForeground(Color.yellow);
			}

			this.waiting = true;

			// throw(e);
		}

		return true;
	}
	/**
	 * 关闭串口
	 */
	public void closeBBPort() {
		if (this.open) {
			System.out.println("Closing " + this.port.getName());

			this.portName.setForeground(Color.red);

			this.open = false;

			/*
			 * Stop transmitting
			 */

			this.transmitter.stopTransmit();

			/*
			 * Stop receiving
			 */

			if (this.rcvThread != null) {
				//线程中断
				this.rcvThread.interrupt();

				this.rcvThread = null;
			}

			/*
			 * Remove the event listener for the port
			 */

			this.port.removeEventListener();

			/*
			 * Remove the ownership event listener
			 */

			this.portID.removePortOwnershipListener(this);

			/*
			 * Close the port
			 */

			this.port.close();

			this.port = null;

			ctlSigs.setPort(this.port);

			flowCtl.setPort(this.port);

			receiver.setPort(this.port);

			transmitter.setPort(this.port);

			transmitter.clearValues();
			receiver.clearValues();
			flowCtl.clearValues();
			ctlSigs.clearValues();
			ctlSigs.clearErrorValues();

			this.showValues();
		}
	}
	/**
	 * 获取serial port对象
	 * @return SerialPort
	 */
	public SerialPort getPort() {
		return (this.open ? this.port : null);
	}
    /**
     * 获取串口输出流
     * @return OutputStream
     */
	private OutputStream getOutputStream() {
		return (this.out);
	}
	/**
	 * 设置串口输出流
	 * @param newout
	 */
	private void setOutputStream(OutputStream newout) {
		this.outSave = getOutputStream();

		this.out = newout;
	}
	/**
	 * 设置线路监视
	 * @param other 另一个串口
	 * @param value
	 */
	public void setLineMonitor(SerialPortDisplay other, boolean value) {
		/*
		 * To make a line monitor, we simply take two ports and interchange
		 * their output streams!
		 */

		this.lineMonitor = value;
		other.lineMonitor = value;

		if (this.lineMonitor) {
			this.setOutputStream(other.getOutputStream());

			other.setOutputStream(this.outSave);
		}

		else {
			other.setOutputStream(this.getOutputStream());

			this.setOutputStream(this.outSave);
		}
	}

	private void createPanel() {
		/*
		 * If this labels exists, we hav already been created, so just reset the
		 * correct port.
		 */

		if (portName != null) {
			ctlSigs.setPort(this.port);

			flowCtl.setPort(this.port);

			receiver.setPort(this.port);

			transmitter.setPort(this.port);
		}

		else {
			/*
			 * Create the User Interface objects
			 */

			display = new Panel();
			display.setLayout(new FlowLayout());

			portName = new Label(portID.getName());

			if (this.open) {
				portName.setForeground(Color.green);
			}

			else if (this.waiting) {
				portName.setForeground(Color.yellow);
			}

			else {
				portName.setForeground(Color.red);
			}

			portName.addMouseListener(this);
			display.add(portName);

			baudRate = new BaudRate(this);
			display.add(baudRate);

			dataBits = new DataBits(this);
			display.add(dataBits);

			stopBits = new StopBits(this);
			display.add(stopBits);

			parity = new Parity(this);
			display.add(parity);

			this.add("North", display);

			statePanel = new Panel();
			statePanel.setLayout(new BorderLayout());

			ctlSigs = new CtlSigDisplay(port);
			statePanel.add("North", ctlSigs);

			flowCtl = new FlowCtlDisplay(port);
			statePanel.add("South", flowCtl);

			this.add("South", statePanel);

			textPanel = new Panel();
			textPanel.setLayout(new BorderLayout());

			receiver = new Receiver(this, 6, 40, this.rcvDelay,
					this.silentReceive);

			textPanel.add("East", receiver);

			transmitter = new Transmitter(this, 6, 40, this.modemMode);

			textPanel.add("West", transmitter);

			this.add("Center", textPanel);

			owner.addPanel(this);
		}
	}

	protected void showValues() {
		baudRate.showValue();
		dataBits.showValue();
		stopBits.showValue();
		parity.showValue();

		transmitter.setBitsPerCharacter(numDataBits);
		receiver.setBitsPerCharacter(numDataBits);

		transmitter.showValues();
		receiver.showValues();
		flowCtl.showValues();
		ctlSigs.showValues();
		ctlSigs.showErrorValues();
	}

	/*
	 * Handler for all serial port events
	 */

	public void serialEvent(SerialPortEvent ev) {
		if (this.port == null) {
			System.out.println(port.getName()
					+ "got serial event on a closed port");

			return;
		}

		switch (ev.getEventType()) {
		case SerialPortEvent.BI:

			this.ctlSigs.BILabel.setState(ev.getNewValue());
			break;

		case SerialPortEvent.OE:

			this.ctlSigs.OELabel.setState(ev.getNewValue());
			break;

		case SerialPortEvent.FE:

			this.ctlSigs.FELabel.setState(ev.getNewValue());
			break;

		case SerialPortEvent.PE:

			this.ctlSigs.PELabel.setState(ev.getNewValue());
			break;

		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:

			this.ctlSigs.showValues();
			break;

		case SerialPortEvent.DATA_AVAILABLE:

			this.ctlSigs.DA = true;
			this.ctlSigs.showErrorValues();

			if (rcvThread != null) {
				synchronized (receiver) {
					receiver.notify();
				}
			}

			else if (threadRcv) {
				System.out.println(port.getName() + "Receive thread has died!");

				rcvThread = new Thread(this.receiver, "Rcv " + port.getName());

				rcvThread.start();
			}

			else {
				this.receiver.readData();
			}

			break;

		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:

			this.ctlSigs.BE = true;
			this.ctlSigs.showErrorValues();

			break;
		}
	}

	/*
	 * Handler to open/close port
	 */

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// this.showValues();

		if (this.open) {
			this.closeBBPort();
		}

		else {
			try {
				openBBPort();
			}

			catch (PortInUseException ex) {
				System.out.println(portID.getName() + " is in use by "
						+ ex.currentOwner);

			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	/*
	 * Handler for port ownership events.
	 */

	public void ownershipChange(int type) {
		switch (type) {
		case CommPortOwnershipListener.PORT_UNOWNED:

			System.out.println(portID.getName() + ": PORT_UNOWNED");

			if (this.waiting) {
				/*
				 * Try to open the port
				 */

				try {
					openBBPort();
				}

				catch (PortInUseException e) {
					System.out.println(portID.getName()
							+ " s/b free but is in use by " + e.currentOwner);
				}
			}

			break;

		case CommPortOwnershipListener.PORT_OWNED:

			System.out.println(portID.getName() + ": PORT_OWNED");

			break;

		case CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED:

			System.out.println(portID.getName() + ": PORT_OWNERSHIP_REQUESTED");

			if (this.friendly && this.open) {
				/*
				 * Give up the port
				 */

				this.closeBBPort();
			}

			break;

		}
	}
}
