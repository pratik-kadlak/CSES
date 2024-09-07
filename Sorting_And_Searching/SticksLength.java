package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SticksLength {
    public static long sticksLength(int[] sticks){
		Arrays.sort(sticks);

		long cost = 0;
		int mid = sticks.length / 2;

		for(int i = 0; i < sticks.length; i++){
			cost += Math.abs(sticks[i] - sticks[mid]);
		}

		return cost;

	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] secondLine = br.readLine().split(" ");

		int[] sticks = new int[n];
		for(int i = 0; i < n; i++){
			sticks[i] = Integer.parseInt(secondLine[i]);
		}

		System.out.println(sticksLength(sticks));
		
		bw.flush();
		br.close();
		bw.close();

	}
}
