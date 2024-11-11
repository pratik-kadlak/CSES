package Tree_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Company_Queries_I {
    
    static int[][] dp;
    static int maxBit = 20; // k is at max 2*10^5 so it will be under 2^20 i.e 20th bit

    // build's dp table
    public static void memo(int n, int[] parent){
        dp = new int[maxBit][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = parent[i];
        }

        for(int i = 1; i < maxBit; i++){
            for(int j = 0; j < n; j++){
                if(dp[i-1][j] == -1) dp[i][j] = -1;
                else dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }
    }

    // finds kth ancestor using dp table
    public static int kthAncestor(int node, int k){
        for(int bit = maxBit-1; bit >= 0; bit--){
            if((k & (1<<bit)) != 0){
                if(dp[bit][node] == -1) return -2;
                node = dp[bit][node];
            }
        }
        return node;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int q = Integer.parseInt(f[1]);

        String[] s = br.readLine().split(" ");
        int[] parent = new int[n];

        parent[0] = -1;
        for(int i = 0; i < n-1; i++){
            parent[i+1] = Integer.parseInt(s[i])-1;
        }
        
        memo(n, parent);
        while(q > 0){
            String[] qry = br.readLine().split(" ");
            int node = Integer.parseInt(qry[0])-1;
            int k = Integer.parseInt(qry[1]);

            bw.write(kthAncestor(node, k) + 1 + "\n");
            q--;
        }

        bw.flush();
        br.close();
        bw.close();

    }

}
