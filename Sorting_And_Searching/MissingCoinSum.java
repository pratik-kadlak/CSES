package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class MissingCoinSum {

    public static long missingCoinSum(int[] coins){
		Arrays.sort(coins);
		long sum = 0;

		for(int i = 0; i < coins.length; i++){
			if(coins[i] <= sum+1){
				sum += coins[i];
			} else break;
		}

		return sum+1;

	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] secondLine = br.readLine().split(" ");

		int[] coins = new int[n];
		for(int i = 0; i < n; i++){
			coins[i] = Integer.parseInt(secondLine[i]);
		}

		System.out.println(missingCoinSum(coins));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
