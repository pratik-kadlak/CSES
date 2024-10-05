package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MinimizingCoins {
    public static int mimimizingCoins(int x, int[] coins){
		int n = coins.length;
		int[][] dp = new int[n+1][x+1];
		dp[0][0] = 0;
		for(int i = 1; i <= x; i++) 
			dp[0][i] = (int) 1e9;

		for(int idx = 1; idx <= n; idx++){
			for(int sum = 0; sum <= x; sum++){
				int take = Integer.MAX_VALUE;
				if(sum >= coins[idx-1]){
					take = 1 + dp[idx][sum-coins[idx-1]];
				}
				int nottake = dp[idx-1][sum];

				dp[idx][sum] = Math.min(take, nottake);
			}
		}

		if(dp[n][x] == (int) 1e9) return -1;
		return dp[n][x];
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

		bw.write(Integer.toString(mimimizingCoins(x, coins)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
