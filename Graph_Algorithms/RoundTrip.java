package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class RoundTrip {
    
    public static boolean dfs(int node, int parent, boolean[] vis, int[] path, ArrayList<ArrayList<Integer>> adj, int[] startNode){
        vis[node] = true;
        path[node] = parent;

        for(int it: adj.get(node)){
            if(!vis[it]){
                if(dfs(it, node, vis, path, adj, startNode)) return true;
            } else if(it != parent) {
                startNode[0] = it;
                path[it] = node;
                return true;
            }
        }

        return false;
    }

    public static String roundTrip(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] vis = new boolean[n+1];
        int[] path = new int[n+1];
        int[] startNode = {-1};

        for(int i = 1; i <= n; i++){
            if(!vis[i]){
                if(dfs(i, -1, vis, path, adj, startNode)){
                    int moves = 0;
                    int curr = startNode[0];
                    ArrayList<Integer> cycle = new ArrayList<>();
                    do{
                        cycle.add(curr);
                        curr = path[curr];
                        moves++;
                    } while(curr != startNode[0]);
                    moves++;
                    cycle.add(startNode[0]);

                    StringBuilder sb = new StringBuilder();
                    for(int it: cycle) sb.append(it + " ");
                    return moves + "\n" + sb.toString();
                }
            }
        }   

        return "IMPOSSIBLE";
    }

 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        bw.write(roundTrip(n, adj));
       
		bw.flush();
		br.close();
		bw.close();
 
	}


}
