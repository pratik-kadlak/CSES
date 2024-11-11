package Tree_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Subordinates {
    
    public static int dfs(int node, int[] ans, ArrayList<ArrayList<Integer>> adj){
        int child = 0;
        for(int it: adj.get(node)){
            child += 1 + dfs(it, ans, adj);
        }
        return ans[node] = child;
    }

    public static String subordinate(int n, ArrayList<ArrayList<Integer>> adj){
        int[] ans = new int[n];
        dfs(0, ans, adj);
        StringBuilder sb = new StringBuilder();
        for(int it: ans) sb.append(it + " ");
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < n-1; i++){
            int p = Integer.parseInt(s[i])-1;
            int c = i+1;
            adj.get(p).add(c);
        }

        bw.write(subordinate(n, adj));

        bw.flush();
        br.close();
        bw.close();
    }
}
