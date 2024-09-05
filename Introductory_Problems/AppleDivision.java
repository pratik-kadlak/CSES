package Introductory_Problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AppleDivision {
    
    public static long backtrack(int index, long s1, long s2, int[] nums){
		if(index == nums.length){
			return Math.abs(s1 - s2);
		}

		long a = backtrack(index+1, s1+nums[index], s2, nums);
		long b = backtrack(index+1, s1, s2+nums[index], nums);

		return Math.min(a, b);
	}

	public static long appleDivision(int[] nums){
		return backtrack(0, 0, 0, nums);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] array = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(array[i]);
		}

		System.out.println(appleDivision(nums));

		bw.flush();
		br.close();
		bw.close();

	}

}
