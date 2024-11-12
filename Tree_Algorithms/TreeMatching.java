package Tree_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TreeMatching {

    static int[][] dp = new int[200000][2];
    // dp[i][0] : max matching by not including the any edge of the type (i, x) i.e don't include any edge which have i as one node
    // dp[i][1] : max matching by including the edge of the type (i, x) i.e include edge (i,x)

    public static void dfs(int src, int par, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> pref = new ArrayList<>();
        ArrayList<Integer> suff = new ArrayList<>();        
        dp[src][0] = dp[src][1] = 0;

        boolean isLeaf = true;
        for(int it: adj.get(src)){
            if(it != par){
                isLeaf = false;
                dfs(it, src, adj);
            }
        }

        // we check if it is a leaf as leaf node will have max matching as 0 so we return
        if(isLeaf) return;

        // else we add the max matching of the childrens of a node in pref and suff list
        for(int it: adj.get(src)){
            if(it != par){
                pref.add(Math.max(dp[it][0], dp[it][1]));
                suff.add(Math.max(dp[it][0], dp[it][1]));
            }
        }

        // then we do the prefixSum and suffixSum of it
        for(int i = 1; i < pref.size(); i++)
            pref.set(i, pref.get(i) + pref.get(i-1));
        for(int i = suff.size()-2; i >= 0; i--)
            suff.set(i, suff.get(i) + suff.get(i+1));
        
        dp[src][0] = suff.get(0); // if we didn't include any edge containing src then dp[src][0] is just 
                                        // sum of max matching of the children

        // for dp[i][1] it will depend on which edge we include for i so will one by one try all edges and then save the max of it
        // dp[i][1] = for child c of i: max(1 + pref.get(i-1) + dp[c][0] + suff.get(i+1))
        // i.e if we take the edge (i,c) then we can't take any edge containing c so we do dp[c][0] i.e max matching of by not taking any edge (c,x)
        // and the rest of the subtrees doesn't change so we take their pref and suff sums

        int cid = 0; 
        for(int c: adj.get(src)){
            if(c == par) continue;
            int left = (cid == 0) ? 0 : pref.get(cid-1);
            int curr = dp[c][0];
            int right = (cid==suff.size()-1) ? 0 : suff.get(cid+1);

            dp[src][1] = Math.max(dp[src][1], 1 + left + curr + right);
            cid++;
        }
    }

    public static void treeMatching(int n, ArrayList<ArrayList<Integer>> adj){
        dfs(0, -1, adj);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < n-1; i++){
            String[] e = br.readLine().split(" ");
            int u = Integer.parseInt(e[0]) - 1;
            int v = Integer.parseInt(e[1]) - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        treeMatching(n, adj);
        bw.write(Integer.toString(Math.max(dp[0][0], dp[0][1])));

        bw.flush();
        br.close();
        bw.close();
    }

}
