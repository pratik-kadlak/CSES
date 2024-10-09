package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CountingTowers {
    
    static long[][] dp;
	static int MOD = (int) 1e9 + 7;

	public static void countingTowers(int n){
		dp[0][0] = 1;
		dp[0][1] = 1;

		for(int i = 1; i < n; i++){
			dp[i][0] = (((4 * dp[i-1][0]) % MOD) + (dp[i-1][1] % MOD)) % MOD;
			dp[i][1] = (((2 * dp[i-1][1]) % MOD) + (dp[i-1][0] % MOD)) % MOD;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(br.readLine());

		dp = new long[1000000][2];
		countingTowers(1000000);
		
		while (t > 0) {
			int n = Integer.parseInt(br.readLine());
			bw.write((dp[n-1][0] + dp[n-1][1]) % MOD  + "\n");
			t--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}
}
