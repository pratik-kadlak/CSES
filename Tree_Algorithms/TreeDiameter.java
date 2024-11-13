package Tree_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TreeDiameter {
    
    static int dia = 0;

    public static int dfs(int node, int par, ArrayList<ArrayList<Integer>> adj){
        int mx1 = 0, mx2 = 0;
        for(int it: adj.get(node)){
            if(it == par) continue;
            int currHeight = 1 + dfs(it, node, adj);
            if(currHeight > mx1){
                mx2 = mx1;
                mx1 = currHeight;
            } else if(currHeight > mx2) {
                mx2 = currHeight;
            }
        }

        dia = Math.max(dia, mx1 + mx2);
        return mx1;
    }

    public static void treeDiameter(int n, ArrayList<ArrayList<Integer>> adj){
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

        treeDiameter(n, adj);
        bw.write(Integer.toString(dia));

        bw.flush();
        br.close();
        bw.close();
    }

}
