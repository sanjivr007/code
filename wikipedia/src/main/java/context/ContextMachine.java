package context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utility.Utils;

public class ContextMachine {
	final int NoOfQuestions = 5;

	String[] paragraphLines;
	String[] questions;
	String[] answers;
	List<HashMap<String, Integer>> paragraphScore;
	List<HashMap<String, Integer>> questionScore;
	List<HashMap<String, Integer>> answerScore;

	public ContextMachine(String[] paragraphLines, String[] questions, String[] answers) {

		this.paragraphLines = paragraphLines;
		this.questions = questions;
		this.answers = answers;

		paragraphScore = new ArrayList<>();
		questionScore = new ArrayList<>();
		answerScore = new ArrayList<>();

	}

	public ContextMachine generateFrequencyMatrix() {
		Utils.getFrequencyMatrix(paragraphLines, paragraphScore);
		Utils.getFrequencyMatrix(questions, questionScore);
		Utils.getFrequencyMatrix(answers, answerScore);
		return this;
	}

	public void execute() {
		boolean visited[] = new boolean[NoOfQuestions];
		for (HashMap<String, Integer> map : questionScore) {
			HashMap<String, Integer> matchingtext = null;
			matchingtext = getMatchingMap(map);

			int answersIndex = getAnswerIndex(matchingtext, visited);
			System.out.println(answers[answersIndex]);

		}

	}

	public HashMap<String, Integer> getMatchingMap(HashMap<String, Integer> map) {

		int score = 0;
		HashMap<String, Integer> matchingtext = null;

		for (HashMap<String, Integer> eachLineScore : paragraphScore) {

			int currentScore = 0;
			// Score Calculation logic
			for (String questionKey : map.keySet()) {
				if (eachLineScore.containsKey(questionKey)) {
					currentScore += questionKey.length();
				} else {
					currentScore -= questionKey.length() / 2;
				}

			}
			if (score < currentScore) {
				score = currentScore;
				matchingtext = eachLineScore;
			}

		}
		return matchingtext;
	}

	public int getAnswerIndex(HashMap<String, Integer> matchingtext, boolean visited[]) {
		int ansScore = 0;
		int answerCurrentIndex = 0;
		int answersIndex = 0;
		for (HashMap<String, Integer> ans : answerScore) {
			if (visited[answerCurrentIndex]) {
				answerCurrentIndex++;
				continue;
			}
			int currentScore = 0;
			// Score Calculation logic
			for (String ansKey : ans.keySet()) {
				if ((null != matchingtext) && (matchingtext.containsKey(ansKey))) {
					currentScore += ansKey.length();
				} else {
					currentScore -= ansKey.length() / 2;
				}

			}
			if (currentScore > ansScore) {
				ansScore = currentScore;
				answersIndex = answerCurrentIndex;
			}
			answerCurrentIndex++;
		}
		visited[answersIndex] = true;
		return answersIndex;
	}

}
