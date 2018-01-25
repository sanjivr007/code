package tlvprocessor;

public class ReplaceCommand implements Command {

	String command;
	
	public ReplaceCommand(String command){
		this.command=command;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String executeCommand(Values inputData) {
		
		return "THIS STRING";
	}

}
