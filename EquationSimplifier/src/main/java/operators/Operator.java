package operators;

public class Operator {

	public int sum(int first, int second) {
		return first + second;
	}

	public int multiply(int first, int second) {
		return first * second;
	}

	public int divide(int first, int second) {
		if (second != 0)
			return first / second;
		else return 100000000;
	}

	public int subtract(int first,int second){
		return first-second;
	}
}
