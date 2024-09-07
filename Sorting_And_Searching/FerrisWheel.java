package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class FerrisWheel {
    
    public static int ferrisWheel(int[] weights, int x){
		Arrays.sort(weights);
		int i = 0, j = weights.length-1;
		int rides = 0;

		while(i < j){
			if(weights[i]+weights[j] <= x){
				i++; j--;
			} else {
				j--;
			}
			rides++;
		}
		if(i == j) rides++;
		return rides;
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		String[] secondLine = br.readLine().split(" ");

		int n = Integer.parseInt(firstLine[0]);
		int x = Integer.parseInt(firstLine[1]);

		int[] weights = new int[n];
		for(int i = 0; i < n; i++)
			weights[i] = Integer.parseInt(secondLine[i]);

		System.out.println(ferrisWheel(weights, x));
		
		bw.flush();
		br.close();
		bw.close();

	}


}
