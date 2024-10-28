package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    
     public static String courseSchedule(int n, ArrayList<ArrayList<Integer>> adj){
        int[] indeg = new int[n];
        for(int i = 0; i < n; i++){
            for(int it: adj.get(i)){
                indeg[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(indeg[i]==0) queue.add(i);
        }

        List<Integer> topo = new ArrayList<>();
        while(!queue.isEmpty()){
            int node = queue.poll();
            topo.add(node);
            for(int it: adj.get(node)){
                indeg[it]--;
                if(indeg[it]==0){
                    queue.add(it);
                }
            }
        }

        if(topo.size() != n) return "IMPOSSIBLE";
        StringBuilder sb = new StringBuilder();
        for(int it: topo) sb.append((it+1) + " ");
        return sb.toString();
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        int e = Integer.parseInt(f[1]);

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < e; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0])-1;
            int v = Integer.parseInt(s[1])-1;
            adj.get(u).add(v);
        }

        bw.write(courseSchedule(n, adj));

        bw.flush();
        br.close();
        bw.close();
    }

}
