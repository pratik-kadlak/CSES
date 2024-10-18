package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Shortest_Routes_I {
    
    static class Pair{
        int node; long dist;
        Pair(int node, long dist){
            this.node = node;
            this.dist = dist;
        }
    }
    
    public static String dijkstra(int n, ArrayList<ArrayList<Pair>> adj){
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);       
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->Long.compare(x.dist, y.dist));
        dist[1] = 0;
        pq.add(new Pair(1, 0));

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            if(p.dist > dist[p.node]) continue; // optimization
            for(Pair it: adj.get(p.node)){
                if(dist[it.node] > dist[p.node] + it.dist){
                    dist[it.node] = dist[p.node] + it.dist;
                    pq.add(new Pair(it.node, dist[it.node]));
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) sb.append(dist[i] + " ");

        return sb.toString();
    }
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            long d = Long.parseLong(s[2]);

            adj.get(u).add(new Pair(v, d));
        }

        bw.write(dijkstra(n, adj));
       
		bw.flush();
		br.close();
		bw.close();
 
	}

}
