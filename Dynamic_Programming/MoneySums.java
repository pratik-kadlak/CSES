package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MoneySums {
    
    static boolean[][] dp;
    // dp[i][j] = by using coins from index 0 to i can we make sum j

	public static void moneySums(int n, int[] coins){
		int sum = 0;
		for(int i = 0; i < n; i++)
			sum += coins[i];

		dp = new boolean[n][sum+1];
		for(int i = 0; i < n; i++) dp[i][0] = true;
		dp[0][coins[0]] = true;

		for(int i = 1; i < n; i++){
			for(int j = 1; j <= sum; j++){
				boolean take = false;
				if(j >= coins[i]) // check if we can take the curr coin and by taking it can we make sum j
					take = dp[i-1][j - coins[i]];

				boolean nottake = dp[i-1][j]; // check if by not taking can we make sum j

				dp[i][j] = take || nottake;
			}
		}
	}	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		
		String[] f = br.readLine().split(" ");
		int[] coins = new int[n];
		for(int i = 0; i < n; i++){
			coins[i] = Integer.parseInt(f[i]);
		}

		moneySums(n, coins);

		int cnt = 0;
		StringBuilder str = new StringBuilder();
		for(int i = 1; i < dp[0].length; i++){
			if(dp[n-1][i]){
				cnt++;
				str.append(i + " ");
			}
		}

		bw.write(cnt + "\n");
		bw.write(str.toString());

		bw.flush();
		br.close();
		bw.close();
 
	}

}
