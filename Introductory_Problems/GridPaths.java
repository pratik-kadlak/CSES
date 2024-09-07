package Introductory_Problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GridPaths {
    
    public static int countGridPaths(int index, int row, int col, char[] ch, boolean[][] vis){
		if(index == 48){
			if(row == 6 && col == 0) return 1;
			return 0;
		}
		
		if(row == 6 && col == 0) return 0;
		if(vis[row][col]) return 0;
		if(col>0 && col<6 && !vis[row][col-1] && !vis[row][col+1] && ((row==0 && vis[row+1][col]) || (row==6 && vis[row-1][col]))) return 0;
		if(row>0 && row<6 && !vis[row-1][col] && !vis[row-1][col] && ((col==0 && vis[row][col+1]) || (col==6 && vis[row][col-1]))) return 0;
		if(row>0 && row<6 && col>0 && col<6 && vis[row+1][col] && vis[row-1][col] && !vis[row][col-1] && !vis[row][col+1]) return 0;
		if(row>0 && row<6 && col>0 && col<6 && vis[row][col+1] && vis[row][col-1] && !vis[row-1][col] && !vis[row+1][col]) return 0;

		vis[row][col] = true;

		int totalPaths = 0;
		if(ch[index] != '?'){
			if(ch[index]=='U' && row != 0) totalPaths += countGridPaths(index+1, row-1, col, ch, vis);
			if(ch[index]=='R' && col != 6) totalPaths += countGridPaths(index+1, row, col+1, ch, vis);
			if(ch[index]=='D' && row != 6) totalPaths +=countGridPaths(index+1, row+1, col, ch, vis);
			if(ch[index]=='L' && col != 0) totalPaths += countGridPaths(index+1, row, col-1, ch, vis);
		} else {
			if(row != 0) totalPaths += countGridPaths(index+1, row-1, col, ch, vis);
			if(col != 6) totalPaths += countGridPaths(index+1, row, col+1, ch, vis);
			if(row != 6) totalPaths += countGridPaths(index+1, row+1, col, ch, vis);
			if(col != 0) totalPaths += countGridPaths(index+1, row, col-1, ch, vis);
		}
		vis[row][col] = false;
		return totalPaths;
	}

	public static int gridPaths(String path){
		char[] ch = path.toCharArray();
		boolean[][] vis = new boolean[7][7];
		return countGridPaths(0, 0, 0, ch, vis);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String path = br.readLine();
		System.out.println(gridPaths(path));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
