package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class NearestSmallerValues {
    
    public static void nearestSmallerValues(int[] nums){
		int n = nums.length;
		int[] ans = new int[n];
		Stack<Integer> stack = new Stack<>();

		for(int i = 0; i < n; i++){
			while(!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
				int top = stack.pop();
				if(stack.isEmpty()) ans[top] = 0;
				else ans[top] = stack.peek() + 1;
			}
			stack.push(i);
		}

		while(!stack.isEmpty()){
			int top = stack.pop();
			if(stack.isEmpty()) ans[top] = 0;
			else ans[top] = stack.peek() + 1;
 		}

		StringBuilder str = new StringBuilder();
		for(int it: ans){
			str.append(it + " ");
		}

		System.out.println(str.toString());

	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);

		String[] s = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(s[i]);
		}

		nearestSmallerValues(nums);
		
		bw.flush();
		br.close();
		bw.close();

	}

}
