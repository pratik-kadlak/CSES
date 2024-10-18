package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Shortest_Routes_II {
    
    static class Tuple{
        int src, dest, dist;
        Tuple(int src, int dest, int dist){
            this.src = src;
            this.dest = dest;
            this.dist = dist;
        }
    }
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);
        int q = Integer.parseInt(f[2]);

        ArrayList<Tuple> edges = new ArrayList<>();

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int d = Integer.parseInt(s[2]);
            edges.add(new Tuple(u-1, v-1, d));
        }

        // floyd warshall algorithm
        long[][] dist = new long[n][n];
        for(long[] d: dist){Arrays.fill(d, (long) 1e14);}
        for(int i = 0; i < n; i++) dist[i][i] = 0;
        for(Tuple t: edges){
            dist[t.src][t.dest] = Math.min(dist[t.src][t.dest], t.dist);
            dist[t.dest][t.src] = Math.min(dist[t.dest][t.src], t.dist);
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }


        for(int i = 0; i < q; i++){
            String[] qry = br.readLine().split(" ");
            int u = Integer.parseInt(qry[0]);
            int v = Integer.parseInt(qry[1]);

            long ans = dist[u-1][v-1] == (long) 1e14 ? -1 : dist[u-1][v-1];
            bw.write(ans + "\n");
        }
       
		bw.flush();
		br.close();
		bw.close();
 
	}

}
