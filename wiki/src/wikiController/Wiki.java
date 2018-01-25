package wikiController;


import java.util.Scanner;
import context.ContextMachine;


public class Wiki {

	public static void main(String[] args) {
		int NoOfQuestions = 5;
		Scanner scInput = new Scanner(System.in);

		String inputParagraph = scInput.nextLine();
		String paragraphLines[] = inputParagraph.split("\\.", -1);
		String[] questions = new String[NoOfQuestions];
		for (int i = 0; i < 5; i++) {
			questions[i] = scInput.nextLine();
		}
		String[] answers = scInput.nextLine().split(";");
		
		ContextMachine contextMachine = new ContextMachine(paragraphLines,questions,answers); 

		contextMachine.generateFrequencyMatrix().execute();
		
		

	}

}