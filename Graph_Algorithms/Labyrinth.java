package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Labyrinth {
    
    static class Tuple {
        int row, col, moves;
        StringBuilder path;
        Tuple(int row, int col, int moves, StringBuilder path){
            this.row = row;
            this.col = col;
            this.moves = moves;
            this.path = path;
        }
    }
 
    public static String bfs(int r, int c, char[][] grid){
        int m = grid.length;
        int n = grid[0].length;
 
        boolean[][] vis = new boolean[m][n];
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        char[] dir = {'U','R','D','L'};
 
        Queue<Tuple> queue = new LinkedList<>();
        vis[r][c] = true;
        StringBuilder sb = new StringBuilder();
        queue.add(new Tuple(r,c,0, sb));
 
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                Tuple t = queue.poll();
                if(grid[t.row][t.col] == 'B'){
                    String res = "YES" + "\n" + t.moves + "\n" + t.path.toString();
                    return res;
                }
 
                for(int k = 0; k < 4; k++){
                    int nrow = t.row + drow[k];
                    int ncol = t.col + dcol[k];
    
                    if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && grid[nrow][ncol]!='#' && !vis[nrow][ncol]){
                        vis[nrow][ncol] = true;
                        StringBuilder temp = new StringBuilder();
                        temp.append(t.path);
                        temp.append(dir[k]);
                        queue.add(new Tuple(nrow, ncol, t.moves+1, temp));
                    }
                }
            }
        }
 
        return "NO";
 
    }
 
    public static String labyrinth(char[][] grid){
        int m = grid.length;
        int n = grid[0].length;
 
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'A'){
                    return bfs(i, j, grid);
                }
            }
        }
        return "NO";
    }
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String[] f = br.readLine().split(" ");
        int r = Integer.parseInt(f[0]);
        int c = Integer.parseInt(f[1]);
 
        char[][] grid = new char[r][c];
        for(int i = 0; i < r; i++){
            grid[i] = br.readLine().toCharArray();
        }
 
        bw.write(labyrinth(grid));
 
		bw.flush();
		br.close();
		bw.close();
 
	}
}
