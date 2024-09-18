package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MovieFestivalII {
    public static int movieFestivalII(int k, int[][] movies){
		Arrays.sort(movies, new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b){
				if(a[1] == b[1]) return Integer.compare(a[0], b[0]);
				return Integer.compare(a[1], b[1]);
			}
		});

		int canWatch = 0;
		int mapSize = 0;
		TreeMap<Integer, Integer> treemap = new TreeMap<>();

		for(int[] mv: movies){
			Integer it = treemap.floorKey(mv[0]);
			if(it != null){
				canWatch++;
				if(treemap.get(it) == 1) treemap.remove(it);
				else treemap.put(it, treemap.get(it)-1);
				treemap.put(mv[1], treemap.getOrDefault(mv[1], 0) + 1);
			} else if(mapSize < k){
				canWatch++;
				mapSize++;
				treemap.put(mv[1], treemap.getOrDefault(mv[1], 0)+1);
			}
		}
		return canWatch;
	}
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int k = Integer.parseInt(f[1]);

		int[][] movies = new int[n][2];
		for(int i = 0; i < n; i++){
			String[] s = br.readLine().split(" ");
			movies[i] = new int[2];
			movies[i][0] = Integer.parseInt(s[0]);
			movies[i][1] = Integer.parseInt(s[1]);
		}
		
		bw.write(Integer.toString(movieFestivalII(k, movies)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}
