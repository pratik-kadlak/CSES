package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GridPaths {
    
    public static long gridPaths(int[][] grid){
		if(grid[0][0] == 0) return 0;
		int MOD = (int) 1e9 + 7;
		int n = grid.length;
		long[][] dp = new long[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j]==0) dp[i][j] = 0;
				else if(i == 0 && j == 0) dp[i][j] = 1;
				else if(i == 0) dp[i][j] = dp[i][j-1];
				else if(j == 0) dp[i][j] = dp[i-1][j];
				else dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % MOD;
			}
		}

		return dp[n-1][n-1];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		int n = Integer.parseInt(br.readLine());
		char[][] mat = new char[n][n];
		for(int i = 0; i < n; i++){
			mat[i] = br.readLine().toCharArray();
		}

		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(mat[i][j] == '.') grid[i][j] = 1;
				else grid[i][j] = 0;
			}
		}

		bw.write(Long.toString(gridPaths(grid)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
