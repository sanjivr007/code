package allPossibleWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Mismatch {

	static final int no_of_friends = 2;

	public static int findvalue(List<Integer> friend2, int tosearchandDelete) {
		int pos = -10;
		int itr = 0;
		int valueTOdelete = 0;
		for (int value : friend2) {
			// System.out.println("Searching in values "+value);
			if (value == tosearchandDelete) {
				pos = itr;
				valueTOdelete = value;
				break;
			}
			itr++;
		}
		return pos;

	}

	public static int abs(int a, int b) {

		if ((a - b) >= 0)
			return a - b;
		else
			return b - a;

	}

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);

		int name = s.nextInt(); // Reading input from STDIN
		// System.out.println("Hi, " + name + ".");
		/*
		 * List<Integer> friend1 = new ArrayList<>(); List<Integer> friend2 =
		 * new ArrayList<>();
		 */
		/*
		 * HashMap<Integer, Integer> friend1 = new HashMap<>(); HashMap<Integer,
		 * Integer> friend2 = new HashMap<>();
		 */
		List<Integer> friend1 = new ArrayList<>();
		List<Integer> friend2 = new ArrayList<>();

		for (int i = 0; i < name; i++) {

			friend1.add(s.nextInt());
		}
		for (int i = 0; i < name; i++) {
			friend2.add(s.nextInt());

		}
		int thispos = 0;
		int mismatchcount = 0;
		for (int value : friend1) {
			// System.out.println("Sending value "+value);
			int pos = findvalue(friend2, value);
			friend2.remove(pos);
			// System.out.println("getting position "+pos);
			mismatchcount = mismatchcount + pos;
			thispos++;
		}

		System.out.println(mismatchcount);

	}

}
