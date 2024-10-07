package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class RemovingDigits {
    public static long removingDigits(int n){
		long[] dp = new long[n+1];
		Arrays.fill(dp, (int) 1e9);
		dp[0] = 0;

		for(int i = 1; i <= n; i++){
			String num = "" + i;
			char[] digits = num.toCharArray();
			for(int j = 0; j < digits.length; j++){
				dp[i] = Math.min(dp[i], 1 + dp[i - Integer.valueOf("" + digits[j])]);
			}
		}

		return dp[n];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		int n = Integer.parseInt(br.readLine());
		bw.write(Long.toString(removingDigits(n)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
