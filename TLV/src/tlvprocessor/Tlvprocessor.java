package tlvprocessor;

import java.util.List;
import java.util.Scanner;

public class Tlvprocessor {

	public static void main(String[] args) {
		Scanner inputString = new Scanner(System.in);

		try {
			while (inputString.hasNextLine()) {
				String inputline = inputString.nextLine();
				// check for empty lines
				if (inputline != null || !inputline.trim().isEmpty()) {
				}
				// each line
				parseInput(inputline);
			}
		} finally {
			inputString.close();
		}
	}

	public static void parseInput(String input) {
		Parser parser = new Parser();
		List<Input> inputs = parser.parse(input);
		processor(inputs);

	}

	public static void processor(List<Input> inputs) {

		for (Input input : inputs) {
			String output = null;
			// System.out.println(input.getLength()+" "+input.getType()+"
			// "+input.getValue());
			Command command = CommandGenerator.getCommand(input.getType());

			if (command != null) {
				output = command.executeCommand(new Values(input.getValue().substring(0, input.getLength())));
				System.out.println(input.getType() + "-" + output);
			} else
				System.out.println("Type not valid");

		}

	}

}
