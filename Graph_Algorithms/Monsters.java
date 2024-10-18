package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Monsters {
    
    static class Pair{
        int row, col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    // func for backtracking the path
    public static boolean dfs(Pair p, char[][] track, ArrayList<Character> path, boolean[][] vis, int curr_time){
        if(curr_time == 0) return false; // if we do dfs depth more than reqr return from it
        vis[p.row][p.col] = true;
        path.add(track[p.row][p.col]);

        if(track[p.row][p.col] == '$') return true; // we found the starting point so return true;

        int m = track.length;
        int n = track[0].length;
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        for(int k = 0; k < 4; k++){
            int nrow = p.row + drow[k];
            int ncol = p.col + dcol[k];

            if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && !vis[nrow][ncol] && track[nrow][ncol]!='#'){
                if(dfs(new Pair(nrow, ncol), track, path, vis, curr_time-1))
                    return true;
            }
        }

        path.remove(path.size()-1);
        return false;
    }

    public static String monsters(char[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        int[][] times = new int[m][n]; // we store min time for monsters to reach i,j cell
        for(int i = 0; i < m; i++) Arrays.fill(times[i], -1);

        int sr = -1, sc = -1; // storing starting pos of player
        Queue<Pair> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'M'){
                    times[i][j] = 0;
                    queue.add(new Pair(i, j));
                } else if(grid[i][j] == 'A'){
                    sr = i; sc = j;
                }
            }
        }

        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        char[] dir = {'U','R','D','L'};

        // here we do bfs from each monster and store the minimum time to reach teh i,j cell
        // the claim is - "if monster can reach a cell in t time and they can also reach in t+1 time"
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                Pair p = queue.poll();
                for(int k = 0; k < 4; k++){
                    int nrow = p.row + drow[k];
                    int ncol = p.col + dcol[k];

                    if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && grid[nrow][ncol]!='#' && times[nrow][ncol]==-1){
                        times[nrow][ncol] = 1 + times[p.row][p.col];
                        queue.add(new Pair(nrow, ncol));
                    }
                }
            }
        }

        int curr_time = 1;
        queue.add(new Pair(sr, sc));
        char[][] track = new char[m][n]; // in this we will store from where we have came
        for(int i = 0; i  < m; i++){
            Arrays.fill(track[i], '#');
        }
        track[sr][sc] = '$';

        // now we do bfs for our player in each cell the player must reach with less time than monsters 
        // if not then we discard this path
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                Pair p = queue.poll();
                for(int k = 0; k < 4; k++){
                    int nrow = p.row + drow[k];
                    int ncol = p.col + dcol[k];

                    if(nrow>=0 && nrow<m && ncol>=0 && ncol<n){
                        if(grid[nrow][ncol]!='#' && track[nrow][ncol]=='#' && (times[nrow][ncol] > curr_time || times[nrow][ncol]==-1)){
                            track[nrow][ncol] = dir[k];
                            queue.add(new Pair(nrow, ncol));
                        }
                    } else {
                        // here we have reached outside border so we will backtrack for path
                        // getting in the grid again as nrow or ncol is out of grid and we start dfs from there 
                        if(nrow<0) nrow++;
                        if(nrow==m) nrow--;
                        if(ncol<0) ncol++;
                        if(ncol==n) ncol--;

                        boolean[][] vis = new boolean[m][n];
                        ArrayList<Character> path = new ArrayList<>();
                        dfs(new Pair(nrow, ncol), track, path, vis, curr_time);
                        StringBuilder sb = new StringBuilder();
                        for(int j = path.size()-2; j>=0; j--){ // we skip last because last char is '$'
                            sb.append(path.get(j));
                        }

                        return "YES" + "\n" + (path.size()-1) + "\n" + sb.toString();
                    }
                }
            }   
            curr_time++;
        }

        return "NO";
    }
    
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int m = Integer.parseInt(f[0]);
        int n = Integer.parseInt(f[1]);

        char[][] grid = new char[m][n];
        for(int i = 0; i < m; i++){
            grid[i] = br.readLine().toCharArray();
        }

        bw.write(monsters(grid));
       
		bw.flush();
		br.close();
		bw.close();
 
	}

}
