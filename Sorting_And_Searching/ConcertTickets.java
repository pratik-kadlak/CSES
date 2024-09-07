package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class ConcertTickets {
    
    public static void concertTickets(int[] tickets, int[] customers){
		TreeMap<Integer, Integer> treemap = new TreeMap<>();

		for(int i = 0; i < tickets.length; i++){
			treemap.put(tickets[i], treemap.getOrDefault(tickets[i], 0) + 1);
		}

		for(int i = 0; i < customers.length; i++){
			Integer ticket = treemap.floorKey(customers[i]);
			if(ticket == null) {
				ticket = -1;
			} else {
				if(treemap.get(ticket) == 1) treemap.remove(ticket);
				else treemap.put(ticket, treemap.get(ticket)-1);
			}
			System.out.println(ticket);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		String[] secondLine = br.readLine().split(" ");
		String[] thirdLine = br.readLine().split(" ");

		int n = Integer.parseInt(firstLine[0]);
		int m = Integer.parseInt(firstLine[1]);

		int[] tickets = new int[n];
		for(int i = 0; i < n; i++)
			tickets[i] = Integer.parseInt(secondLine[i]);

		int[] customers = new int[m];
		for(int i = 0; i < m; i++)
			customers[i] = Integer.parseInt(thirdLine[i]);

		concertTickets(tickets, customers);
		
		bw.flush();
		br.close();
		bw.close();

	}

}
