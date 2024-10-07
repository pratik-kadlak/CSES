package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class CoinCombinationsII {
    public static long coinCombinationsII(int x, int[] coins){
	 	int MOD = (int) 1e9 + 7;
		int n = coins.length;
		long[] dp = new long[x+1];
		dp[0] = 1;

		for(int idx = 1; idx <= n; idx++){
			long[] curr = new long[x+1];
			curr[0] = 1;
			for(int trg = 0; trg <= x; trg++){
				long take = 0;
				if(trg >= coins[idx-1])
					take = curr[trg - coins[idx-1]];

				long nottake = dp[trg];

				curr[trg] = (take + nottake) % MOD;
			}
			dp = curr;
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
		bw.write(Long.toString(coinCombinationsII(x, coins)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
