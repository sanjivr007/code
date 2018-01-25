package tlvprocessor;

public class CommandGenerator {
	
	public static Command getCommand(String command){
		
		if(command.equalsIgnoreCase(Commands.REPLCE.name())){
            return new ReplaceCommand(command);
        }
        else if(command.equalsIgnoreCase(Commands.UPPRCS.name())){
            return new UppercaseCommand(command);
        }
		return null;
		
	}

}
