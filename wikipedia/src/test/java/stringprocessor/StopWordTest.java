package stringprocessor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StopWordTest {

	String testString = "I am going";
	List<String> result = new ArrayList<>();

	@Test
	  public void removeStopWords() {
		result.add("going");
		
		StopWord stopword = new StopWord();
		List<String> a = stopword.removeStopWords(testString);		
		assertEquals(result,stopword.removeStopWords(testString));		
	}

}
