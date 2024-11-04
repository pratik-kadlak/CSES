package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Investigation {

    static class Pair{
        int node; long dist;
        Pair(int node, long dist){
            this.node = node;
            this.dist = dist;
        }
    }

    static long[] dist; // dist[i] -> min dist from node 0 to i
    static long[] ways; // ways[i] -> num of ways of min dist from node 0 to i
    static long[] mnSteps; // mnSteps[i] -> min steps required to reach node i from 0 with min dist
    static long[] mxSteps; // mxSteps[i] -> max steps required to reach node i from 0 with max dist
    static int M = (int) 1e9 + 7;
    static long INF = (long) 1e16;
  
    public static void dijkstra(int n, ArrayList<ArrayList<Pair>> adj){
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->Long.compare(x.dist, y.dist));
        pq.add(new Pair(0, 0));

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            if(dist[p.node] < p.dist) continue;
            for(Pair it: adj.get(p.node)){
                if(dist[it.node] > p.dist + it.dist){
                    dist[it.node] = p.dist + it.dist;
                    ways[it.node] = ways[p.node];
                    mnSteps[it.node] = 1 + mnSteps[p.node];
                    mxSteps[it.node] = 1 + mxSteps[p.node];
                    pq.add(new Pair(it.node, dist[it.node]));
                } else if(dist[it.node] == p.dist + it.dist){
                    ways[it.node] = (ways[p.node] + ways[it.node]) % M;
                    mnSteps[it.node] = Math.min(mnSteps[it.node], 1 + mnSteps[p.node]);
                    mxSteps[it.node] = Math.max(mxSteps[it.node], 1 + mxSteps[p.node]);
                }
            }
        }

    }

    public static String investigation(int n, ArrayList<ArrayList<Pair>> adj){
        dist = new long[n];
        ways = new long[n];
        mnSteps = new long[n];
        mxSteps = new long[n];

        Arrays.fill(dist, INF);
        Arrays.fill(ways, 0);
        Arrays.fill(mnSteps, INF);
        Arrays.fill(mxSteps, -1);

        dist[0] = 0;
        ways[0] = 1;
        mnSteps[0] = 0;
        mxSteps[0] = 0;

        dijkstra(n, adj);
        return dist[n-1] + " " + ways[n-1] + " " + mnSteps[n-1] + " " + mxSteps[n-1];
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
            int u = Integer.parseInt(s[0]) - 1;
            int v = Integer.parseInt(s[1]) - 1;
            long d = Long.parseLong(s[2]);
            adj.get(u).add(new Pair(v, d));
        }

        bw.write(investigation(n, adj));

        bw.flush();
        br.close();
        bw.close();
    }
}
