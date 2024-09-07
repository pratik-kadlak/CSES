package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Apartments {

    public static int appartment(int[] desired, int[] apartmentSize, int k){
		Arrays.sort(desired);
		Arrays.sort(apartmentSize);

		int cnt = 0;
		int i = 0, j = 0;
		int n = desired.length, m = apartmentSize.length;
		
		while(i < n && j < m){
			if(Math.abs(desired[i] - apartmentSize[j]) <= k) {
				cnt++;
				i++; j++;
			} else if(desired[i] > apartmentSize[j]){
				j++;
			} else {
				i++;
			}
		}
		return cnt;
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		String[] secondLine = br.readLine().split(" ");
		String[] thirdLine = br.readLine().split(" ");

		int n = Integer.parseInt(firstLine[0]);
		int m = Integer.parseInt(firstLine[1]);
		int k = Integer.parseInt(firstLine[2]);

		int[] desired = new int[n];
		for(int i = 0; i < n; i++)
			desired[i] = Integer.parseInt(secondLine[i]);

		int[] apartmentSize = new int[m];
		for(int i = 0; i < m; i++)
			apartmentSize[i] = Integer.parseInt(thirdLine[i]);

		System.out.println(appartment(desired, apartmentSize, k));
		
		bw.flush();
		br.close();
		bw.close();

	}
    
}
