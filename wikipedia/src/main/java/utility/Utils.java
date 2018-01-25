package utility;

import java.util.HashMap;
import java.util.List;

import stringprocessor.Stemmer;
import stringprocessor.StopWord;

public class Utils {

	public static void getFrequencyMatrix(String[] lineList, List<HashMap<String, Integer>> FrequencyMatrix) {
		for (String question : lineList) {
			StopWord stopWord = new StopWord();
			List<String> resultAfterRemovingStopWord = stopWord.removeStopWords(question);
			FrequencyMatrix.add(new HashMap<>());
			resultAfterRemovingStopWord.stream().forEach(x -> {
				x = x.toLowerCase();
				Stemmer stemmer = new Stemmer();
				for (int i = 0; i < x.length(); i++) {
					stemmer.add(x.charAt(i));
				}
				stemmer.stem();
				if (FrequencyMatrix.get(FrequencyMatrix.size() - 1).containsKey(stemmer.toString())) {
					FrequencyMatrix.get(FrequencyMatrix.size() - 1).put(stemmer.toString(),
							FrequencyMatrix.get(FrequencyMatrix.size() - 1).get(stemmer.toString()) + 1);
				} else {
					FrequencyMatrix.get(FrequencyMatrix.size() - 1).put(stemmer.toString(), 1);
				}
			});
		}
	}

}
