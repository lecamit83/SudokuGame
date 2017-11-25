package sudoku;

import model.SudokuGenerator;

public class Program {

	private static int size = 2;
	
	public static void main(String[] args) {
		
		
		int[][] Sudoku = (new SudokuGenerator(size)).generate();
		showSudoku(Sudoku);
	}
	
	private static void showSudoku(int[][] Sudoku) {
		int n = sqr(size);
		
		for( int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(Sudoku[i][j] + "|");
			}
			
			System.out.println();
		}
	}
	
	private static int sqr(int n) {
		return n*n;
	}

}
