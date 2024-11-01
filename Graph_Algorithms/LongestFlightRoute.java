package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class LongestFlightRoute {
    static int[] dp; // dp[i] stores max path length to go from i to n
    static int[] next_node; // next_node[i] stores to get the max path to which node we have to go next

    public static int dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj){
        vis[node] = true;
        if(dp[node] != -1) return dp[node];

        for(int it: adj.get(node)){ // we traverse adj vertices of a node
            if(!vis[it]) dfs(it, vis, adj); // if it's not visited then we call dfs() for it
            if(dp[node] < 1 + dp[it] && dp[it] != -1){ // and after coming from call we check if it gives longer path
                dp[node] = 1 + dp[it]; // if yes we update the dp[node] with 1 + dp[it]
                next_node[node] = it; // and next_node[node] to it;
            }
        }

        return dp[node];
    }

    public static String longestFlightRoute(int n, ArrayList<ArrayList<Integer>> adj){
        dp = new int[n];
        next_node = new int[n];
        Arrays.fill(dp, -1); // initialize dp with -1
        dp[n-1] = 1; // for dp[n] we know the longest path is of length 1 
        boolean[] vis = new boolean[n];
        dfs(0, vis, adj);

        if(dp[0] == -1) return "IMPOSSIBLE"; // if dp[0]==-1 means we can't reach the last node

        StringBuilder sb = new StringBuilder();
        int node = 0;
        while(next_node[node] != 0){
            sb.append((node+1) + " ");
            node = next_node[node];
        }
        sb.append(n);

        return dp[0] + "\n" + sb.toString();

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

        bw.write(longestFlightRoute(n, adj));

        bw.flush();
        br.close();
        bw.close();
    }
}
