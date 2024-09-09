package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Playlist {
    
    public static int playlist(int[] songs){
		Set<Integer> set = new HashSet<>();
		int l = 0, r = 0;
		int mx = 0;

		while(r < songs.length){
			while(set.contains(songs[r])){
				set.remove(songs[l]);
				l++;
			}
			set.add(songs[r]);
			mx = Math.max(mx, r-l+1);
			r++;
		}

		return mx;

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		String[] secondLine = br.readLine().split(" ");
		int[] songs = new int[n];
		for(int i = 0; i < n; i++){
			songs[i] = Integer.parseInt(secondLine[i]);
		}
		
		System.out.println(playlist(songs));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
