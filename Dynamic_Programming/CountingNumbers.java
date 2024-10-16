package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class CountingNumbers {
    
    static long[][][][] dp = new long[20][10][2][2];
    // dp[i][j][k][l] = dp[index][last_digit][leading_zero][tight]

    public static long countingNumbers(String s, int idx, int last_digit, int leading_zeros, int tight){
        if(idx == s.length()) return 1;
        if(dp[idx][last_digit][leading_zeros][tight] != -1)
            return dp[idx][last_digit][leading_zeros][tight];

        int ans = 0;
        int limit = (tight == 1) ? s.charAt(idx)-'0' : 9;
        for(int i = 0; i <= limit; i++){
            if(leading_zeros == 0 && i == last_digit) continue;
            int new_leading_zeros = leading_zeros & ((i == 0) ? 1 : 0); // if we have lz==1 and we have i==0 then nlz = 1 else nlz = 0;
            int new_tight = tight & (i == s.charAt(idx)-'0' ? 1 : 0); // if we have tight==1 and i == s.charAt(idx) then nt = 1 else nt = 0;
            ans += countingNumbers(s, idx+1, i, new_leading_zeros, new_tight);
        }

        return dp[idx][last_digit][leading_zeros][tight] = ans;
    }
   
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] f = br.readLine().split(" ");
        long l = Long.parseLong(f[0]);
        long r = Long.parseLong(f[1]);  

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 2; k++){
                    for(int m = 0; m < 2; m++){
                        dp[i][j][k][m] = -1;
                    }
                }
            }
        }

        String ri = Long.toString(r);
        long rAns = countingNumbers(ri, 0, 0, 1, 1);

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 2; k++){
                    for(int m = 0; m < 2; m++){
                        dp[i][j][k][m] = -1;
                    }
                }
            }
        }

        String li = Long.toString(l-1);
        long lAns = countingNumbers(li, 0, 0, 1, 1);

        bw.write(Long.toString(rAns-lAns));
 
		bw.flush();
		br.close();
		bw.close();

	}

}
