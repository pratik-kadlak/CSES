package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class DistinceNumbers {
    public static int distinctNums(int[] nums){
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < nums.length; i++){
			set.add(nums[i]);
		}
		return set.size();
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

		System.out.println(distinctNums(nums));
		
		bw.flush();
		br.close();
		bw.close();

	}   
}
