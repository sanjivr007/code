package allPossibleWords;

import java.util.Scanner;

public class Max {
	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);

		String no = s.nextLine();
		int swapcount = s.nextInt();

		int maxno = getMaxNo(no, swapcount);

	  System.out.println(maxno);

	}

	public static boolean docompare(String s, int max) {
		int a = Integer.parseInt(s);
		if (a >= max)
			return true;
		else
			return false;
	}

	public static int getMaxNo(String no, int swapcount) {
		int max = Integer.parseInt(no);
		int n = no.length();
		if (swapcount == 0)
			return 0;

		for (int i = 0; i < n - 1; i++) {

			for (int j = i + 1; j < n; j++) {
				if (no.charAt(i) < no.charAt(j)) {

					swap(no.charAt(i), no.charAt(j));

					if (docompare(no, max))
						max = Integer.parseInt(no);
					

					getMaxNo(no, swapcount - 1);

					swap(no.charAt(i), no.charAt(j));
				}

			}

		}
		return max;

	}

	public static void swap(char charAt, char charAt2) {
		// TODO Auto-generated method stub

		char temp = charAt;
		charAt = charAt2;
		charAt2 = temp;

	}

}
