package context;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import utility.Utils;

public class ContextMachineTest {
	String[] paragraphLines = "Zebras are several species of African equids (horse family) united by their distinctive black and white stripes. Their stripes come in different patterns, unique to each individual. They are generally social animals that live in small harems to large herds. Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated. There are three species of zebras: the plains zebra, the Grévy's zebra and the mountain zebra. The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's zebra is the sole species of subgenus Dolichohippus. The latter resembles an ass, to which it is closely related, while the former two are more horse-like. All three belong to the genus Equus, along with other living equids. The unique stripes of zebras make them one of the animals most familiar to people. They occur in a variety of habitats, such as grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills. However, various anthropogenic factors have had a severe impact on zebra populations, in particular hunting for skins and habitat destruction. Grévy's zebra and the mountain zebra are endangered. While plains zebras are much more plentiful, one subspecies, the quagga, became extinct in the late 19th century – though there is currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar to the quagga in a process called breeding back."
			.split("\\.", -1);
	String[] questions = { "Which Zebras are endangered?", "What is the aim of the Quagga Project?",
			"Which animals are some of their closest relatives?", "Which are the three species of zebras?",
			"Which subgenus do the plains zebra and the mountain zebra belong to?" };
	String[] answers = { "subgenus Hippotigris", "the plains zebra, the Grévy's zebra and the mountain zebra",
			"horses and donkeys", "aims to breed zebras that are phenotypically similar to the quagga",
			"Grévy's zebra and the mountain zebra" };
	List<HashMap<String, Integer>> score = new ArrayList<>();
	HashMap<String, Integer> entry = new HashMap<>();
	ContextMachine contextMachine;

	@Before
	public void setUp() throws Exception {
		contextMachine = new ContextMachine(paragraphLines, questions, answers);
		contextMachine.generateFrequencyMatrix();
	}

	@Test
	public void getMatchingMap() {

		boolean visited[] = new boolean[5];
		HashMap<String, Integer> map = new HashMap<>();
		map.put("zebra", 1);
		map.put("endang", 1);
		HashMap<String, Integer> matchedString = new HashMap<>();
		// matchedString.put("zebra", 1);
		matchedString.put("endang", 1);
		matchedString.put("zebra", 2);
		matchedString.put("mountain", 1);
		matchedString.put("grévy", 1);
		matchedString.put("s", 1);

		assertEquals(matchedString, contextMachine.getMatchingMap(map));
		//System.out.println(contextMachine.getAnswerIndex(matchedString, visited));
		assertEquals(4, contextMachine.getAnswerIndex(matchedString, visited));

		/* map.forEach( (k,v) -> System.out.println(k+" "+v+" ---") ); */

	}

}
