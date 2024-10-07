package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class CoinCombinationsI {
    public static long coinCombinationsI(int x, int[] coins){
	 	int MOD = (int) 1e9 + 7;
		int n = coins.length;
		long[] dp = new long[x+1];
		dp[0] = 1;
		for(int i = 1; i <= x; i++){
			for(int j = 0; j < n; j++){
				if(i >= coins[j]){
					dp[i] = (dp[i] + dp[i - coins[j]]) % MOD;
				} else break;
			}
		}

		return dp[x];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int x = Integer.parseInt(f[1]);

		String[] s = br.readLine().split(" ");
		int[] coins = new int[n];
		for(int i = 0; i < n; i++){
			coins[i] = Integer.parseInt(s[i]);
		}
		
		Arrays.sort(coins);
		bw.write(Long.toString(coinCombinationsI(x, coins)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
