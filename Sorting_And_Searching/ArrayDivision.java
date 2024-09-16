package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ArrayDivision {
    
    public static long arrayDivision(int k, int[] nums){
		int n = nums.length;
		long low = 0, high = 0;
		for(int i = 0; i < n; i++)
			high += nums[i];

		while(low <= high){
			long mid = (high - (high - low)/2);
			long currParts = 0;
			long currSum = 0;
			for(int i = 0; i < n; i++){
				if(nums[i] > mid){
					currParts = mid + 1;
					break;
				}
				currSum += nums[i];
				if(currSum > mid){
					currParts++;
					currSum = nums[i];
				}
			}
			currParts++;

			if(currParts <= k) high = mid-1;
			else low = mid + 1;
		}
		return low;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int k = Integer.parseInt(f[1]);

		String[] s = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(s[i]);
		}

		bw.write(Long.toString(arrayDivision(k, nums)));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
