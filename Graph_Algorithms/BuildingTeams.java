package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class BuildingTeams {
    
    public static boolean bfs(int src, int[] color, ArrayList<ArrayList<Integer>> adj){
        int curr_color = 1;
        Queue<Integer> queue = new LinkedList<>();
        color[src] = curr_color;
        queue.add(src);

        while(!queue.isEmpty()){
            int sz = queue.size();
            int new_color = 1 - curr_color;
            for(int i = 0; i < sz; i++){
                int node = queue.poll();
                for(int it: adj.get(node)){
                    if(color[it] == -1){
                        color[it] = new_color;
                        queue.add(it);
                    } else if(color[it] == curr_color){
                        return false;
                    }
                }
            }
            curr_color = new_color;
        }
        return true;
    }

    public static String buildingTeams(int n, ArrayList<ArrayList<Integer>> adj){
        int[] color = new int[n+1];
        Arrays.fill(color, -1);
        for(int i = 1; i <= n; i++){
            if(color[i] == -1){
                if(!bfs(i, color, adj))
                    return "IMPOSSIBLE";
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append((color[i]+1) + " ");
        }
        return sb.toString();
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

        bw.write(buildingTeams(n, adj));
       
		bw.flush();
		br.close();
		bw.close();
 
	}

}
