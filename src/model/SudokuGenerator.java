package model;

import java.util.Random;

public class SudokuGenerator {

	private int size = 0;
	private Random rand;
	
	public SudokuGenerator(int n) {
		this.size = n;
		rand = new Random();
	}
	
	public int[][] generate() {
		int n = sqr(size);
		int sqrt_n = this.size;
		int[][] Sudoku = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				Sudoku[i][j] = (i * sqrt_n + i / sqrt_n + j) % n + 1;
			}
		}
		
		for( int times = 0; times < 30; times++ ) {
			int valueOne = rand.nextInt(n) + 1;
			int valueTwo = rand.nextInt(n) + 1;
			
			swap(Sudoku, valueOne, valueTwo);
		}
		
		return Sudoku;
	}
	
	
	private int sqr(int n) {
		return n*n;
	}
	
	private void swap( int[][] Sudoku, final int valueOne, final int valueTwo ) {
		int n = sqr(size);
		int sqrt_n = this.size;
		
		int xst = -1, yst = -1, xnd = -1, ynd = -1;
		
		for(int row = 0 ; row < n; row += sqrt_n ) {
			for(int col = 0 ; col < n; col += sqrt_n ) {
				// in One BigCell
				
				for( int i = 0; i < sqrt_n; i++ ) {
					for(int j = 0; j < sqrt_n; j++ ) {
						if( Sudoku[row + i][col + j] == valueOne ) {
							xst = row + i;
							yst = col + j;
						}
						
						if( Sudoku[row + i][col + j] == valueTwo ) {
							xnd = row + i;
							ynd = col + j;
						}
					}
				}
				
				Sudoku[xnd][ynd] = valueOne;
				Sudoku[xst][yst] = valueTwo;
			}
		}
	}
	
}
