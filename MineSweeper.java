package MineSweeper;

import java.util.Scanner;

import javax.security.auth.x500.X500Principal;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class MineSweeper {
	private int[][] grid; //0-8normal  9:mine
	private boolean[][] show;
	private int numOfBoom;
	private int N;
	private boolean[][] AIShow;
	
	public MineSweeper() {
		N = 10;
		numOfBoom = 10;
		grid = new int[N][N];
		//initialize the map
		init();
		show = new boolean[N][N];
	}
	
	public int getN() {return N;}

	public void init() {
		for (int i = 0; i < numOfBoom; i++) {
			int x = (int)(Math.random()*N);
			int y = (int)(Math.random()*N);
			if (grid[x][y]==9) {i--;}  //对冲
			grid[x][y] = 9;
			
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] != 9) {
					setNumber(i, j);
				} 
			}
		}
	}
	
	public void setNumber(int x, int y) {
		int sum =0;
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if (i==x&&j==y) {}
				if (i>=N||i<0||j>=N||j<0) {}
				else if(grid[i][j]==9) {sum++;}	
			}
		}
		grid[x][y] = sum;
	}
	
	public void display() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void game() {
		Scanner input = new Scanner(System.in);
		boolean isOver = false;
		while(!isOver) {
			displayPlayer();
			System.out.println("Please input x");
			int y = input.nextInt()-1;
			System.out.println("Please input y");
			int x = N- input.nextInt();
			if (x<0||y<0||x>=N||y>=N) {
				System.out.println("Input Error!");
			}else {
				show[x][y] = true;
				if (grid[x][y] == 0) {
					setZero(x, y);
				}
				if (grid[x][y]==9) {
					isOver = true;
					display();
					System.out.println("BOOOOOOOOOM! You die!");
				}
				else if (isWin()) {
					isOver = true;
					System.out.println();
					display();
					System.out.println("You Win!");
				}
			}
			
			}
		}
	


	

	
	public boolean isWin() {
		int sum =0;
		for (int i = 0; i < show.length; i++) {
			for (int j = 0; j < show[0].length; j++) {
				if (show[i][j] == false) {
					sum++;
				}
			}
		}
		if (sum == numOfBoom) {
			return true;
		}else {
			return false;
		}
	}
	
	public void displayPlayer() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (show[i][j]) {
					System.out.print(grid[i][j] + " ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}	
	}
	
	public void setZero(int x, int y) {
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if (i==x&&j==y) {}
				else if (i>=N||i<0||j>=N||j<0) {}
				else {
					
					if ((show[i][j]==false)&&(grid[i][j] == 0)) {
						show[i][j] = true;
						setZero(i, j);
					}else {
						show[i][j] = true;
					}
				}
		}
	}
}

}