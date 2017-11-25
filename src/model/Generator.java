package model;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	
	
	
	private ArrayList< ArrayList<Integer> > Availble = new ArrayList< ArrayList<Integer> >();
	private Random rand = new Random();
	private int size;

	
	public Generator(int size) {
		this.size = size;
	}
	
	public int[][] generateGrid() {
		int n = size*size;
		int[][] Sudoku = new int[n][n];
		
		clearGrid(Sudoku);
		int currentPos = 0;
		while( currentPos < n ){ 
			
			int xPos = currentPos / n;
			int yPos = currentPos % n;
			
		
				if( Availble.get(currentPos).size() != 0 ) {
					int pos = rand.nextInt(Availble.get(currentPos).size());
					int number = Availble.get(currentPos).get(pos);
					System.out.println(currentPos + " : " + number);
					
					if( !checkConflict(Sudoku, currentPos, number) ) {
						
						Sudoku[xPos][yPos] = number;
						
						currentPos++;
						
					} else {
						Availble.get(currentPos).remove(pos);
					}
					
				} else {
					
					for( int capa = 1; capa <= n; capa++) { 
						Availble.get(currentPos).add(capa);
					}
					currentPos++;
					
				}
			
		
		}
		
		
		return Sudoku;
	}
	
	private boolean checkConflict( final int[][] Sudoku , int currentPos, final int number ) {
		int n = size*size;
		
		int xPos = currentPos / n;
		int yPos = currentPos % n;
		
		
		
		if( checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number) ) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkHorizontalConflict( final int[][] Sudoku, final int xPos, final int yPos, final int number ) {
		
		for( int y = yPos - 1; y >= 0; y-- ) {
			if( Sudoku[xPos][y] == number ) return true;
		}
		
		return false;
	}
	
	private boolean checkVerticalConflict( final int[][] Sudoku, final int xPos, final int yPos, final int number ) {
		
		for( int x = xPos - 1; x >= 0; x--) {
			if( Sudoku[x][yPos] == number ) return true;
		}
		
		return false;
	}
	
	private boolean checkRegionConflict( final int[][] Sudoku, final int xPos, final int yPos, final int number ) {
		int n = size;
		
		int xReg = xPos / n;
		int yReg = yPos / n;
		
		n = (int) Math.sqrt(n);
		
		for( int x = xReg * n ; x < xReg * n + n; x++ ) {
			for( int y = yReg * n; y < yReg * n + n; y++ ) {
				if( Sudoku[x][y] == number ) return true;
			}
		}
		
		return false;
	}
	
	
	private void clearGrid(int[][] Sudoku) {
		int n = size;
		
		Availble.clear();
		
		for( int i = 0; i < n; i++ ) {
			for( int j = 0; j < n; j++ ) {
				Sudoku[i][j] = -1;
			}
		}
		
		ArrayList< Integer > childArray = new ArrayList<Integer>();
		for( int capa = 1; capa <= n; capa++) { 
			childArray.add(capa);
		}
		
		for( int currentPos = 0; currentPos < n*n; currentPos++ ) {
			Availble.add(childArray);
		}
	}
	
}
