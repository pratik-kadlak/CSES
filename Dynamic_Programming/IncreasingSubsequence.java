package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class IncreasingSubsequence {
    
    static int[] dp;
	static int INF = Integer.MAX_VALUE;

	public static int lowerBound(int x){
		int low = 0, high = dp.length-1;

		while(low <= high){
			int mid = (low + high) / 2;
			if(dp[mid] >= x) high = mid-1;
			else low = mid+1;
		}

		return low;
	}

	public static long LIS(int[] nums){
		int n = nums.length;
		dp = new int[n];
		Arrays.fill(dp, INF);

		for(int i = 0; i < n; i++){
			int idx = lowerBound(nums[i]);
			dp[idx] = nums[i];
		}

		long lis = 0;
		for(int i = 0; i < n; i++){
			if(dp[i] != INF) lis++;
			else break;
		}

		return lis;
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

		bw.write(Long.toString(LIS(nums)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
