package utility;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
	String[] paragraphLines = { "Their stripes come in different patterns, unique to each individual" };
	List<HashMap<String, Integer>> score = new ArrayList<>();
	HashMap<String, Integer> entry = new HashMap<>();

	@Test
	public void testAddParkingLot() {
		entry.put("uniqu", 1);
		entry.put("stripe", 1);
		entry.put("pattern", 1);
		entry.put("individu", 1);
		entry.put("come", 1);
		entry.put("differ", 1);
		Utils.getFrequencyMatrix(paragraphLines, score);

		HashMap<String, Integer> a = score.get(0);
		assertEquals(entry, a);

	}

}
