package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class FlightDiscount {
    static class Pair{
        int node; long dist;
        Pair(int node, long dist){
            this.node = node;
            this.dist = dist;
        }
    }

    public static long[] dijkstra(int src, int n, ArrayList<ArrayList<Pair>> adj){
        long[] dist = new long[n];
        Arrays.fill(dist, (long) 1e18);

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->Long.compare(x.dist,y.dist));
        dist[src] = 0; 
        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            if(dist[p.node] < p.dist) continue;
            for(Pair it: adj.get(p.node)){
                if(dist[it.node] > dist[p.node] + it.dist){
                    dist[it.node] = dist[p.node] + it.dist;
                    pq.add(new Pair(it.node, dist[it.node]));
                }
            }
        }

        return dist;
    }

    public static long flightDiscount(int n, ArrayList<ArrayList<Pair>> adj){
        // get the min dist from src to every other node 
        long[] dist_from_src = dijkstra(0, n, adj);

        // reversing the edges of the graph
        ArrayList<ArrayList<Pair>> revAdj = new ArrayList<>();
        for(int i = 0; i < n; i++) revAdj.add(new ArrayList<>());
        for(int i = 0; i < n; i++){
            for(Pair p: adj.get(i)){
                revAdj.get(p.node).add(new Pair(i, p.dist));
            }
        }

        // get the min dist from trg to every other node
        long[] dist_from_trg = dijkstra(n-1, n, revAdj);

        long mn = (long) 1e18;

        // we traverse for every edge (u,v) and apply the discount on that edge and then calculate the min distance by 
        // dist_from_src_to_u + (curr_edge_wt / 2) + dist_from_trg_to_v 

        for(int i = 0; i < n; i++){ // 
            for(Pair j: adj.get(i)){
                if(dist_from_src[j.node] == (int) 1e18 || dist_from_trg[j.node] == (int) 1e18) // if node is unreachable we continue
                    continue;
                long currCost = dist_from_src[i] + (j.dist/2) + dist_from_trg[j.node]; 
                mn = Math.min(mn, currCost);
            }
        }

        return mn;
    }
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0])-1;
            int v = Integer.parseInt(s[1])-1;
            long d = Long.parseLong(s[2]);
            adj.get(u).add(new Pair(v, d));
        }

        bw.write(Long.toString(flightDiscount(n, adj)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
