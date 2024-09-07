package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class MovieFestival {
    
    public static int movieFestival(int[][] movies){
		Arrays.sort(movies, Comparator.comparingInt(a -> a[1]));

		int mx = 0, index = 0, time = 0; 

		while(index < movies.length){
			if(movies[index][0] >= time){
				time = movies[index][1];
				mx++;
			}
			index++;
		}
		return mx;
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[][] movies = new int[n][2];

		for(int i = 0; i < n; i++){
			String[] arr = br.readLine().split(" ");
			int[] t = {Integer.parseInt(arr[0]), Integer.parseInt(arr[1])};
			movies[i] = t;
		}

		System.out.println(movieFestival(movies));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
