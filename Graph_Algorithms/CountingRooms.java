package Graph_Algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class CountingRooms {
    static class Pair{
        int row, col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void bfs(int r, int c, char[][] grid, boolean[][] vis){
        int m = grid.length;
        int n = grid[0].length;
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        Queue<Pair> queue = new LinkedList<>();
        vis[r][c] = true;
        queue.add(new Pair(r,c));

        while(!queue.isEmpty()){
            Pair p = queue.poll();
            for(int k = 0; k < 4; k++){
                int nrow = p.row + drow[k];
                int ncol = p.col + dcol[k];

                if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && grid[nrow][ncol]=='.' && !vis[nrow][ncol]){
                    vis[nrow][ncol] = true;
                    queue.add(new Pair(nrow, ncol));
                }

            }
        }

    }

    public static long countingRooms(char[][] grid){
        int r = grid.length;
        int c = grid[0].length;

        boolean[][] vis = new boolean[r][c];

        int comp = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == '.' && !vis[i][j]){
                    bfs(i, j, grid, vis);
                    comp++;
                }
            }
        }
        return comp;
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

        bw.write(Long.toString(countingRooms(grid)));
 
		bw.flush();
		br.close();
		bw.close();

	}
}
