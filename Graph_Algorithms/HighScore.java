package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class HighScore {
    
    static class Tuple{
        int src, dest, dist;
        Tuple(int src, int dest, int dist){
            this.src = src;
            this.dest = dest;
            this.dist = dist;
        }
    }

    // this func checks if the nodes in changing and marked by dfs can reach (n-1)th node or not
    public static boolean bfs(int n, ArrayList<Tuple> edges, ArrayList<Integer> changing){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(Tuple t: edges){
            adj.get(t.src).add(t.dest);
        }

        boolean[] mark = new boolean[n];
        dfs(0, adj, mark); // this will mark all nodes reachable from 0th node

        boolean[] vis = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for(int it: changing){
            if(mark[it]){ // we will only put then nodes which are changing and also reachable from 0 
                vis[it] = true;
                queue.add(it);
            }
        }

        while(!queue.isEmpty()){ // we check if the nodes can reach the final node then we have to return -1
            int node = queue.poll();
            if(node == n-1) return true; // if we can reach the node then we return true;
            for(int it: adj.get(node)){
                if(!vis[it]){
                    vis[it] = true;
                    queue.add(it);
                }
            }
        }

        return false; // here queue becomes empty so none of the changing nodes can reach the final node
    }

    // this func is to mark nodes that are reachable from 0th node as we are starting from it
    public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] mark){
        mark[node] = true;
        for(int it: adj.get(node)){
            if(!mark[it]){
                dfs(it, adj, mark);
            }
        }
    }

 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);

        ArrayList<Tuple> edges = new ArrayList<>();

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int d = Integer.parseInt(s[2]);
            edges.add(new Tuple(u-1, v-1, d));
        }

        // bellman-ford algorithm
        long[] dist = new long[n];
        Arrays.fill(dist, -(long) 1e14);
        dist[0] = 0;

        // we try to relax the edges n-1 times
        for(int i = 0; i < n-1; i++){
            for(Tuple t: edges){
                if(dist[t.dest] < dist[t.src] + t.dist){
                    dist[t.dest] = dist[t.src] + t.dist;
                }
            }
        }

        ArrayList<Integer> changing = new ArrayList<>();
        for(Tuple t: edges){
            if(dist[t.dest] < dist[t.src] + t.dist){
                changing.add(t.dest); // we save those nodes whose dist are changing 
            }
        }

        if(bfs(n, edges, changing)) bw.write(Long.toString(-1));
        else bw.write(Long.toString(dist[n-1])); // if bfs returns false we return the dist[n-1]

		bw.flush();
		br.close();
		bw.close();
 
	}

}
