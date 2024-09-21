package Range_Queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class RangeXorQueries {
    
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1]);

		int[] nums = new int[n];
		String[] s = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(s[i]);
		}

		long[] prefixXor = new long[n+1];
		for(int i = 1; i <= n; i++){
			prefixXor[i] = prefixXor[i-1] ^ nums[i-1];
		}

		while(m > 0){
			String[] q = br.readLine().split(" ");
			int l = Integer.parseInt(q[0]);
			int r = Integer.parseInt(q[1]);
			bw.write((prefixXor[r] ^ prefixXor[l-1]) + "\n");
			m--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}
 

}
