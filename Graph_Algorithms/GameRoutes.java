package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class GameRoutes {
    
    static int[] dp; // dp[i] : num of paths going from i to n
    static int M = (int) 1e9 + 7;

    public static int dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj){
        vis[node] = true;

        for(int it: adj.get(node)){
            if(!vis[it]) dfs(it, vis, adj);
            dp[node] = (dp[node] + dp[it]) % M; // num of paths from node i to n = sum of paths from child of i to n;
        }
        return dp[node];
    }

    public static int gameRoutes(int n, ArrayList<ArrayList<Integer>> adj){
        dp = new int[n];
        Arrays.fill(dp, 0);
        dp[n-1] = 1;
        boolean[] vis = new boolean[n];
        dfs(0, vis, adj);

        return dp[0];

    }
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]) - 1;
            int v = Integer.parseInt(s[1]) - 1;

            adj.get(u).add(v);
        }

        bw.write(Integer.toString(gameRoutes(n, adj)));

        bw.flush();
        br.close();
        bw.close();
    }

}
