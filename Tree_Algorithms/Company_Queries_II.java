package Tree_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Company_Queries_II {
    
    static int[] parent;
    static int[] level;
    static int maxBit = 20;
    static int[][] dp;

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

    // stores level of each node
    public static void dfs(int node, int l, ArrayList<ArrayList<Integer>> adj){
        level[node] = l;
        for(int it: adj.get(node)){
            dfs(it, l+1, adj);
        }
    }

    // finds the lca(a,b)
    public static int lca(int a, int b){
        if(level[a] > level[b]) return lca(b, a); 
        int diff = level[b] - level[a]; // find the level diff
        
        // we make b jump to come to same level as a
        for(int bit = maxBit-1; bit >= 0; bit--){
            if((diff & (1<<bit)) != 0){
                b = dp[bit][b];
            }
        }
        
        if(a == b) return a; // if we reach to a i.e a is ancestor of b then we return a

        // else we try to make jumps as far as we can 
        for(int bit = maxBit-1; bit >= 0; bit--){
            int ap = dp[bit][a];
            int bp = dp[bit][b];
            if(ap != bp){ // if we didn't get the same parent that we can make jump 
                a = ap; b = bp; // so we make jump
            }
        }

        a = dp[0][a]; // finally we will be just 1 below level of lca so we store the parent of curr node
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int q = Integer.parseInt(f[1]);

        String[] s = br.readLine().split(" ");

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        parent = new int[n];
        parent[0] = -1;
        for(int i = 0; i < n-1; i++){
            int p = Integer.parseInt(s[i])-1;
            int c = i+1;
            parent[c] = p;
            adj.get(p).add(c);
        }

        level = new int[n];
        dfs(0, 0, adj);
        memo(n, parent);
        while(q > 0){
            String[] qry = br.readLine().split(" ");
            int a = Integer.parseInt(qry[0]) - 1;
            int b = Integer.parseInt(qry[1]) - 1;
            bw.write(lca(a,b) + 1 + "\n");
            q--;
        }

        bw.flush();
        br.close();
        bw.close();
    }

}
