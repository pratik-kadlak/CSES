package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FactoryMachines {
    public static boolean isPossible(int t, long deadline, int[] machines){
		long products = 0;
		int n = machines.length;

		for(int i = 0; i < n; i++){
			products += (deadline / (long) machines[i]);
		}
		
		return products >= t || products < 0; // products < 0 for Long overflow.
	}

	public static long factoryMachines(int t, int[] machines){
		long low = Integer.MAX_VALUE;
		long high = Integer.MIN_VALUE;

		for(int i = 0; i < machines.length; i++){
			low = Math.min(low, machines[i]);
			high = Math.max(high ,machines[i]);
		}

		high = t * high;

		while(low <= high){
			long mid = (low + high) / 2;
			if(isPossible(t, mid, machines)) high = mid-1;
			else low = mid+1;
		}

		return low;

	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		int n = Integer.parseInt(firstLine[0]);
		int t = Integer.parseInt(firstLine[1]);

		String[] secondLine = br.readLine().split(" ");
		int[] machines = new int[n];
		for(int i = 0; i < n; i++){
			machines[i] = Integer.parseInt(secondLine[i]);
		}

		bw.write(Long.toString(factoryMachines(t, machines)));
		
		bw.flush();
		br.close();
		bw.close();

	}
}
