package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ArrayDescription {
    static int MOD = (int) 1e9 + 7;
	static long[][] dp;

	public static long arrayDescription(int m, int[] nums){
		int n = nums.length;
		dp = new long[n][m+1];

		dp[0][nums[0]] = 1;
		if(nums[0]==0){
			for(int i = 0; i <= m; i++)
				dp[0][i] = 1;
		}


		for(int i = 1; i < n; i++){
			if(nums[i] != 0){
				if(nums[i] != 0) 
					dp[i][nums[i]] = (dp[i][nums[i]] + dp[i-1][nums[i]-1]) % MOD;
				dp[i][nums[i]] = (dp[i][nums[i]] + dp[i-1][nums[i]]) % MOD;
				if(nums[i] != m)
					dp[i][nums[i]] = (dp[i][nums[i]] + dp[i-1][nums[i]+1]) % MOD;
				continue;
			}

			for(int j = 1; j <= m; j++){
				if(j != 1)
					dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % MOD;
				dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
				if(j != m)
					dp[i][j] =( dp[i][j] + dp[i-1][j+1]) % MOD;
			}
		}

		long maxi = 0;
		for(int j = 1; j <= m; j++){
			maxi = (maxi + dp[n-1][j]) % MOD;
		}

		return maxi;
	}

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1]);

		String[] s = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(s[i]);
		}
		
		bw.write(Long.toString(arrayDescription(m, nums)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
