package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RectangleCutting {
    
    public static int rectangleCutting(int m, int n){
		if(m > n) return rectangleCutting(n, m);

		int[][] dp = new int[m+1][n+1];
		for(int i = 1; i <= m; i++) dp[i][1] = i-1;
		for(int j = 1; j <= n; j++) dp[1][j] = j-1;
		for(int i = 1; i <= m; i++) dp[i][i] = 0;

		for(int i = 2; i <= m; i++){
			for(int j = 2; j <= n; j++){
				if(i == j) continue;
				dp[i][j] = (int) 1e9;

				for(int k = 1; k < i; k++){
					dp[i][j] = Math.min(dp[i][j], 1 + dp[i-k][j] + dp[k][j]);
				}	

				for(int k = 1; k < j; k++){
					dp[i][j] = Math.min(dp[i][j], 1 + dp[i][k] + dp[i][j-k]);
				}
			}
		}

		return dp[m][n];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] f = br.readLine().split(" ");
		int m = Integer.parseInt(f[0]);
		int n = Integer.parseInt(f[1]);

		bw.write(Integer.toString(rectangleCutting(m, n)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
