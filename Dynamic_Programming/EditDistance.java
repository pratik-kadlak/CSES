package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EditDistance {
    
    public static int editDistance(String s1, String s2){
		int m = s1.length();
		int n = s2.length();

		int[][] dp = new int[m+1][n+1];
		for(int i = 0; i <= m; i++) dp[i][0] = i;
		for(int j = 0; j <= n; j++) dp[0][j] = j;

		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				// characters match
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1];
					continue;
				}

				// if characters don't match
				int insert = dp[i][j-1];
				int replace = dp[i-1][j-1];
				int delete = dp[i-1][j];

				dp[i][j] = 1 + Math.min(insert, Math.min(replace, delete));

			}
		}

		return dp[m][n];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s1 = br.readLine();
		String s2 = br.readLine();

		bw.write(Integer.toString(editDistance(s1, s2)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
