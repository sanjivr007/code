package nQueen;

public class NQueen {

	public int[][] solution;

	public NQueen(int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				solution[i][j] = 0;
	}

	public class Position {
		int row, column;

		public Position(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
	
	
	
	public boolean validateQueen(int row,int clm,Position pos){
		
		
		return false;
		
	}

	public static void main(String[] args) {
		int n = 4;
		NQueen nQ = new NQueen(n);
		Position[] pos = new Position[n*n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				

			}
		}

	}

}
