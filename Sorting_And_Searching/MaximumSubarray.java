package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MaximumSubarray {
    
    public static long maxSubarray(int[] nums){
		long mxSum = Integer.MIN_VALUE, currSum = Integer.MIN_VALUE;

		for(int i = 0; i < nums.length; i++){
			currSum += nums[i];
			if(currSum < nums[i]) currSum = nums[i];
			mxSum = Math.max(mxSum, currSum);
		}

		return mxSum;

	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] secondLine = br.readLine().split(" ");

		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(secondLine[i]);
		}

		System.out.println(maxSubarray(nums));
		
		bw.flush();
		br.close();
		bw.close();

	}
    
}
