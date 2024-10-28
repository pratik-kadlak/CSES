package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class RoundTripII {
    
     public static boolean detectCycle(int node, boolean[] vis, boolean[] pathVis, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> cycle){
        vis[node] = true;
        pathVis[node] = true;
        cycle.add(node);

        for(int it: adj.get(node)){
            if(!vis[it]){
                if(detectCycle(it, vis, pathVis, adj, cycle)) return true;
            } else if(pathVis[it]){
                cycle.add(it);
                return true;
            }
        }
        pathVis[node] = false;
        cycle.remove(cycle.size()-1);
        return false;
    }

    public static String roundTripII(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] vis = new boolean[n];
        boolean[] pathVis = new boolean[n];
        ArrayList<Integer> cycle = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(!vis[i]){
                detectCycle(i, vis, pathVis, adj, cycle);
                if(!cycle.isEmpty()) break;
            }
        }

        if(cycle.size() == 0) return "IMPOSSIBLE";
        int idx = 0;
        int lastElement = cycle.get(cycle.size()-1);
        while(cycle.get(idx) != lastElement) idx++;
        
        int cLen = 0;
        StringBuilder sb = new StringBuilder();
        while(idx < cycle.size()){
            sb.append((cycle.get(idx)+1) + " ");
            idx++; cLen++;
        }
    
        return cLen + "\n" + sb.toString();
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
            int u = Integer.parseInt(s[0])-1;
            int v = Integer.parseInt(s[1])-1;
            adj.get(u).add(v);
        }

        bw.write(roundTripII(n, adj));

        bw.flush();
        br.close();
        bw.close();
    }

}
