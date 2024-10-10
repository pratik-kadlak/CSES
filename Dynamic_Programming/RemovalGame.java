package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RemovalGame {

    // Memoized Soln
    public static long f(int i, int j, int[] nums, long[][] dp){
		if(i > j) return 0;
		if(dp[i][j] != -1) return dp[i][j];
		long takeFirst = nums[i] + Math.min(f(i+2, j, nums, dp), f(i+1, j-1, nums, dp));
		long takeLast = nums[j] + Math.min(f(i, j-2, nums, dp), f(i+1, j-1, nums, dp));

		return dp[i][j] = Math.max(takeFirst, takeLast);
	}

	public static long removalGame(int n, int[] nums){
		long[][] dp = new long[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				dp[i][j] = -1;
			}
		}
		return f(0, n-1, nums, dp);
	}

    // Tabulated Soln
    public static long tabulatedRemovalGame(int n, int[] nums){
		long[][] dp = new long[n][n];

		for(int g = 0; g < n; g++){
			for(int i = 0, j = g; j < n; i++, j++){
				if(g == 0){
				 	dp[i][j] = nums[i];
				} else if(g == 1){
					dp[i][j] = Math.max(nums[i], nums[i+1]);
				} else {
					dp[i][j] = Math.max(nums[i] + Math.min(dp[i+2][j], dp[i+1][j-1]), 
										nums[j] + Math.min(dp[i][j-2], dp[i+1][j-1]));
				}
			}
		}

		return dp[0][n-1];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		
		String[] f = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(f[i]);
		}

		bw.write(Long.toString(removalGame(n, nums)));
        bw.write("\n");
        bw.write(Long.toString(tabulatedRemovalGame(n, nums)));

		bw.flush();
		br.close();
		bw.close();
 
	}
    
}