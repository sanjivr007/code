package tlvprocessor;

public class UppercaseCommand implements Command {

	String command;
	
	public UppercaseCommand(String command){
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

		return inputData.data.toUpperCase();
	}

}
