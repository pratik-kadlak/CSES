package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class CollectingNumbers {

    public static long collectingNumbers(int[] nums){
		int n = nums.length;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			map.put(nums[i], i);
		}

		int curr = 1;
		int rounds = 1;
		int prevIndex = -1;
		for( ; curr <= n; curr++){
			if(map.get(curr) > prevIndex){
				prevIndex = map.get(curr);
			} else {
				rounds++;
				prevIndex = map.get(curr);
			}
		}
		return rounds;
	}

    // Using Inversion
	public static long collectingNumbers1(int[] nums){
		int n = nums.length;
		int[] map = new int[n+1];

		for(int i = 0; i < nums.length; i++){
			map[nums[i]] = i+1;
		}

		int inversions = 0;
		for(int i = 2; i <= n; i++){
			if(map[i] < map[i-1])
				inversions++;
		}

		return inversions+1;
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

		System.out.println(collectingNumbers(nums));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
