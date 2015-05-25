package dto;

public class SerialData {
	private boolean isOpen;
	private String respose;
	private String command;
	public SerialData() {
		this.isOpen=false;
		this.respose="";
		this.command="";
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getRespose() {
		return respose;
	}
	public void setRespose(String respose) {
		this.respose = respose;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}

}
