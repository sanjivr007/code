package tlvprocessor;

import java.util.ArrayList;
import java.util.List;

public class Parser {

	public List<Input> parse(String eachLine) {
		List<Input> inputs = parsingTheString(eachLine);

		return inputs;

	}
	// We should be having various validations here;

	// Assuming data is always in format
	public List<Input> parsingTheString(String value) {

		String[] instructionList = value.split("-");
		List<Input> inputs = new ArrayList<>();
		int first = 0;

		if ((instructionList.length - 1) % 2 == 0) {

			int i = -100;

			for (Commands com : Commands.values()) {
				i = value.indexOf(com.name());
				if (i >= 0)
					break;

			}
			// if first command is wrong
			if (first == 0 && (i > 12 || i < 0)) {
				int j = 0;
				Input input = new Input();
				String type = value.substring(j, j + 6);

				input.setType(type);
				j = j + 7;

				int length = Integer.parseInt(value.substring(j, j + 4));
				input.setLength(length);
				j = j + 5;

				String newvalue = value.substring(j, value.length());
				input.setValue(newvalue);

				inputs.add(input);

			}

			while (i < value.length()-12 && i != 100 && i != -1) {
				Input input = new Input();
				for (int j = 0; j < 3; j++) {
					String type = value.substring(i, i + 6);

					input.setType(type);
					i = i + 7;
					j++;
					int length = Integer.parseInt(value.substring(i, i + 4));
					input.setLength(length);
					i = i + 5;
					j++;
					String newvalue = value.substring(i, value.length());
					input.setValue(newvalue);
					String newsubstring = value.substring(i, value.length());
					int k=0;
					for (Commands com : Commands.values()) {
						k = newsubstring.indexOf(com.name());
						if ( k >= 0)
							break;

					}
					i += k;
					j++;
					
					

				}
				inputs.add(input);
			}

		}
		return inputs;

	}

}
