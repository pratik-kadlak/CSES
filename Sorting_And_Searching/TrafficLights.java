package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class TrafficLights {

    public static void trafficLights(int n, int[] lights){
		TreeMap<Integer, Integer> treemap = new TreeMap<>();
		TreeSet<Integer> posSet = new TreeSet<>();

		posSet.add(0);
		posSet.add(n);
		treemap.put(n, 1);

		for(int i = 0; i < lights.length; i++){
			int lb = posSet.floor(lights[i]);
			int ub = posSet.ceiling(lights[i]);

			posSet.add(lights[i]);
			treemap.put(ub-lb, treemap.get(ub-lb)-1);
			if(treemap.get(ub-lb) == 0) treemap.remove(ub-lb);
			
			treemap.put(ub-lights[i], treemap.getOrDefault(ub-lights[i], 0)+1);
			treemap.put(lights[i]-lb, treemap.getOrDefault(lights[i]-lb, 0)+1);

			System.out.print(treemap.lastKey() + " ");
		}

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		int n = Integer.parseInt(firstLine[0]);
		int t = Integer.parseInt(firstLine[1]);

		String[] secondLine = br.readLine().split(" ");
		int[] lights = new int[n];
		for(int i = 0; i < t; i++){
			lights[i] = Integer.parseInt(secondLine[i]);
		}
		
		trafficLights(n, lights);
		
		bw.flush();
		br.close();
		bw.close();

	}

    
}
