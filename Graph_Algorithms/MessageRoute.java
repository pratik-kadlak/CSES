package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class MessageRoute {
    
    public static String bfs(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] vis = new boolean[n+1];
        int[] path = new int[n+1];

        for(int i = 0; i <= n; i++){
            vis[i] = false;
            path[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        vis[1] = true;
        path[1] = 0;

        while(!queue.isEmpty()){
            int node = queue.poll();

            if(node == n){
                int moves = 1;
                int curr = n;
                List<Integer> t = new ArrayList<>();
                while(path[curr] != 0){
                    t.add(curr);
                    curr = path[curr];
                    moves++;
                }
                t.add(curr);
                StringBuilder sb = new StringBuilder();
                for(int i = t.size()-1; i >= 0; i--){
                    sb.append(t.get(i) + " ");
                }
               
                return moves + "\n" + sb.toString();
            }

            for(int it: adj.get(node)){
                if(!vis[it]){
                    vis[it] = true;
                    path[it] = node;
                    queue.add(it);
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

        bw.write(bfs(n, adj));
       
		bw.flush();
		br.close();
		bw.close();
 
	}

}
