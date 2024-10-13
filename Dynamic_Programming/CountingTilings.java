package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class CountingTilings {


    static long[][] dp = new long[1001][1<<10];
    static int n, m;
    static int MOD = (int) 1e9 + 7;
 
    public static void fill_cols(int col, int idx, int curr_mask, int next_mask){
        if(idx == n){
            dp[col+1][next_mask] = (dp[col+1][next_mask] + dp[col][curr_mask]) % MOD;
            return;
        }

        if((curr_mask & (1<<idx)) != 0){
            fill_cols(col, idx+1, curr_mask, next_mask);
        } else {    
            fill_cols(col, idx+1, curr_mask, next_mask|(1<<idx));
            if(idx+1 < n && (curr_mask & (1<<(idx+1))) == 0){
                fill_cols(col, idx+2, curr_mask, next_mask);
            }
        }
    }

    public static long countTilings(){
        dp[0][0] = 1;
        for(int col = 0; col < m; col++){
            for(int mask = 0; mask < (1<<n); mask++){
                fill_cols(col, 0, mask, 0);
            }
        }
        return dp[m][0];
    }

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
        n = Integer.parseInt(f[0]); // rows
        m = Integer.parseInt(f[1]); // cols
   
        bw.write(Long.toString(countTilings()));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
