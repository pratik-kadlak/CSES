package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class SumOfTwoValues {
    
    public static void twoSum(int[] nums, int x){
		int n = nums.length;
		int[][]  array = new int[n][2];
		for(int i = 0; i < n; i++){
			array[i] = new int[2];
			array[i][0] = nums[i];
			array[i][1] = i;
		}

		Arrays.sort(array, Comparator.comparingInt(a -> a[0]));
		int i = 0, j = n-1;

		while(i < j){
			if(array[i][0] + array[j][0] == x) {
				System.out.println((array[i][1]+1) + " " + (array[j][1]+1));
				return;
			} else if(array[i][0] + array[j][0] < x){
				i++;
			} else {
				j--;
			}
		}

		System.out.println("IMPOSSIBLE");

	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		int n = Integer.parseInt(firstLine[0]);
		int x = Integer.parseInt(firstLine[1]);

		String[] secondLine = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(secondLine[i]);
		}

		twoSum(nums, x);
		
		bw.flush();
		br.close();
		bw.close();

	}

}
