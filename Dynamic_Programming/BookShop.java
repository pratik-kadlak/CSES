package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BookShop {
    
    public static long bookShop(int n, int x, int[] price, int[] pages){
		long[] dp = new long[x+1];

		for(int i = 1; i <= n; i++){
			long[] curr = new long[x+1];
			for(int j = 1; j <= x; j++){
				long take = 0;
				if(j >= price[i-1])	
					take = pages[i-1] + dp[j - price[i-1]];

				long nottake = dp[j];

				curr[j] = Math.max(take, nottake);
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
		int[] price = new int[n];
		for(int i = 0; i < n; i++){
			price[i] = Integer.parseInt(s[i]);
		}

		String[] t = br.readLine().split(" ");
		int[] pages = new int[n];
		for(int i = 0; i < n; i++){
			pages[i] = Integer.parseInt(t[i]);
		}
		

		bw.write(Long.toString(bookShop(n, x, price, pages)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
