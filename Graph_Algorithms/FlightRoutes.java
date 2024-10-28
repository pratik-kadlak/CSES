package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class FlightRoutes {

     static class Pair{
        int node; long dist;
        Pair(int node, long dist){
            this.node = node;
            this.dist = dist;
        }
    }

    public static String flightRoutes(int n, int k, ArrayList<ArrayList<Pair>> adj){
        long[] ans = new long[k];
        int[] upd = new int[n]; // instead of dist array we store for each node how many times it gets updated i.e remove from pq
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->Long.compare(x.dist, y.dist));
        pq.add(new Pair(0, 0));

        int cnt = 0;
        while(cnt < k){
            Pair p = pq.poll();
            if(upd[p.node] >= k) continue; // since we want min k distances so a node at most will get updates k times so if we get 
            upd[p.node]++;                 // more than k times we don't process that node
            if(p.node == n-1){
                ans[cnt] = p.dist;
                cnt++;
            }

            for(Pair it: adj.get(p.node)){
                pq.add(new Pair(it.node, p.dist+it.dist));
                
            }
        }

        StringBuilder sb = new StringBuilder();
        for(long a: ans) sb.append(a + " ");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);
        int k = Integer.parseInt(f[2]);

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0])-1;
            int v = Integer.parseInt(s[1])-1;
            long d = Long.parseLong(s[2]);

            adj.get(u).add(new Pair(v, d));
        }

        bw.write(flightRoutes(n, k, adj));

        bw.flush();
        br.close();
        bw.close();
    }

}
