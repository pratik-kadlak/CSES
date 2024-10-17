package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class BuildingRoads {
    
    static class DisjointSet{
        int[] rank;
        int[] parent;
        
        DisjointSet(int n){
            rank = new int[n];
            parent = new int[n];

            for(int i = 0; i < n; i++){
                rank[i] = 0;
                parent[i] = i;
            }
        }

        public int find(int u){
            if(parent[u] == u) return u;
            return parent[u] = find(parent[u]);
        }

        public void union(int u, int v){
            u = find(u);
            v = find(v);

            if(u != v){
                if(rank[u] < rank[v]){
                    int temp = u;
                    u = v;
                    v = temp;
                }
                parent[v] = u;
                if(rank[u] == rank[v]) rank[u]++;
            }
        }
    }
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);

        DisjointSet ds = new DisjointSet(n+1);
        
        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            ds.union(u, v);
        }

        int conn = 0;
        List<Integer> newEdges = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            if(ds.find(1) != ds.find(i)){
                conn++;
                newEdges.add(i);
                ds.union(1, i);
            }
        }

        bw.write(conn + "\n");
        for(int it: newEdges){
            bw.write(1 + " " + it + "\n");
        }

		bw.flush();
		br.close();
		bw.close();
 
	}

}
