package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class RestaurantCustomers {
    
    public static int restaurantCustomers(int[] arrival, int[] departure){
		Arrays.sort(arrival);
		Arrays.sort(departure);

		int mx = 0;
		int time = 0;
		int i = 0, j = 0, cust = 0;

		while(i < arrival.length){
			time = arrival[i];
			cust++;

			while(time >= departure[j]){
				j++;
				cust--;
			}
			mx = Math.max(mx, cust);
			i++;
		}

		return mx;
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[] arrival = new int[n];
		int[] departure = new int[n];

		for(int i = 0; i < n; i++){
			String[] arr = br.readLine().split(" ");
			arrival[i] = Integer.parseInt(arr[0]);
			departure[i] = Integer.parseInt(arr[1]);
		}

		System.out.println(restaurantCustomers(arrival, departure));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
