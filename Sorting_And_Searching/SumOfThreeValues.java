package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SumOfThreeValues {
 
    static class Pair {
		int value, index;
		Pair(int value, int index){
			this.value = value;
			this.index = index;
		}
	}

	public static String threeSum(int target, int[] nums){
		int n = nums.length;
		Pair[] pairs = new Pair[n];
		for(int i = 0; i < nums.length; i++){
			pairs[i] = new Pair(nums[i], i+1);
		}

		Arrays.sort(pairs, (a,b)->a.value-b.value);
		int[] ans = new int[3];
		boolean flag = false;

		for(int curr = 0; curr < pairs.length; curr++){
			int rem = target - pairs[curr].value;
			int i = 0, j = pairs.length-1;

			while(i < j){
				if(i == curr){ i++; continue;}
				if(j == curr){ j--; continue;}

				if(pairs[i].value + pairs[j].value == rem){
					ans[0] = pairs[curr].index;
					ans[1] = pairs[i].index;
					ans[2] = pairs[j].index;
					flag = true;
					break;
				} else if(pairs[i].value + pairs[j].value < rem) i++;
				else j--;
			}

			if(flag) break;
		}

		if(!flag) return "IMPOSSIBLE";
		return ans[0] + " " + ans[1] + " " + ans[2];

	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int t = Integer.parseInt(f[1]);

		String[] s = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(s[i]);
		}

		bw.write(threeSum(t, nums));
		
		bw.flush();
		br.close();
		bw.close();

	}
    
}
