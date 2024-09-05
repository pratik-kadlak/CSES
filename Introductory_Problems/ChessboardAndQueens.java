package Introductory_Problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ChessboardAndQueens {
    
    public static boolean isValid(int row, int col, char[][] board){
		int cRow = row, cCol = col;

		while(col >= 0){
			if(board[row][col] == 'Q') return false;
			col--;
		}		

		col = cCol;
		while(row >= 0 && col >= 0){
			if(board[row][col] == 'Q') return false;
			row--; col--;
		}

		row = cRow; col = cCol;
		while(row < board.length && col >= 0){
			if(board[row][col] == 'Q') return false;
			row++; col--;
		}

		return true;
	}

	public static void placeQueens(int col, char[][] board, int[] ans){
		if(col == board.length){
			ans[0]++;
			return;
		}

		for(int row = 0; row < board.length; row++){
			if(board[row][col] == '*') continue;
			if(isValid(row, col, board)){
				board[row][col] = 'Q';
				placeQueens(col+1, board, ans);
				board[row][col] = '.';
			}
		}	
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		char[][] board = new char[8][8];

		for(int i = 0; i < 8; i++){
			board[i] = br.readLine().toCharArray();
		}

		int[] ans = {0};

		placeQueens(0, board, ans);
		System.out.println(ans[0]);

		bw.flush();
		br.close();
		bw.close();

	}

}
