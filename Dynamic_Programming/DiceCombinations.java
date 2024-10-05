package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DiceCombinations {
    static int MOD = (int) 1e9 + 7;
	public static int diceCombinations(int n){
		int[] dp = new int[n+1];

		for(int i = 1; i <= n; i++){
			if(i <= 6) dp[i] = (int) Math.pow(2, i-1);
			else {
                // dp[i] = sum of prev 6 values
				for(int j = i-1; j >= (i-6); j--){
					dp[i] = (dp[i] + dp[j]) % MOD;
				}
			}
		}

		return dp[n];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		int n = Integer.parseInt(br.readLine());

		bw.write(Integer.toString(diceCombinations(n)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
