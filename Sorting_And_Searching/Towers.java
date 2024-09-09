package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Towers {
    
    public static int lowerBound(int curr, int[] towers){
		int low = 0, high = towers.length-1;
		while(low <= high){
			int mid = (low + high) / 2;
			if(towers[mid] > curr){
				high = mid-1;
			} else {
				low = mid+1;
			}
		} 
		return low;
	}

	public static int minTowers(int[] cubes){
		int n = cubes.length;
		int[] towers = new int[n];
		Arrays.fill(towers, Integer.MAX_VALUE);

		for(int i = 0; i < n; i++){
			towers[lowerBound(cubes[i], towers)] = cubes[i];
		}

		int index = 0;
		for( ; index < towers.length; index++){
			if(towers[index] == Integer.MAX_VALUE) 
				break;
		}

		return index;

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		String[] secondLine = br.readLine().split(" ");
		int[] towers = new int[n];
		for(int i = 0; i < n; i++){
			towers[i] = Integer.parseInt(secondLine[i]);
		}
		
		System.out.println(minTowers(towers));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
