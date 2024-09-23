package Range_Queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class ForestQueries {
    
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int t = Integer.parseInt(f[1]);

		char[][] g = new char[n][n];
		for(int i = 0; i < n; i++){
			String s =  br.readLine();
			char[] ch = s.toCharArray();
			g[i] = ch;
		}

		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(g[i][j] == '*' ) grid[i][j] = 1;
				else grid[i][j] = 0;
			}
		}

		int[][] prefixSum = new int[n+1][n+1];
		// summing rows
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				prefixSum[i][j] = prefixSum[i][j-1] + grid[i-1][j-1];
			}
		}

		// summing cols
		for(int j = 1; j <= n; j++){
			for(int i = 1; i <= n; i++){
				prefixSum[i][j] += prefixSum[i-1][j];
			}
		}
		
		while(t > 0){
			String[] q = br.readLine().split(" ");
			int x1 = Integer.parseInt(q[0]);
			int y1 = Integer.parseInt(q[1]);
			int x2 = Integer.parseInt(q[2]);
			int y2 = Integer.parseInt(q[3]);

			int trees = prefixSum[x2][y2] - prefixSum[x1-1][y2] - prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1];
			System.out.println(trees);

			t--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}

}
