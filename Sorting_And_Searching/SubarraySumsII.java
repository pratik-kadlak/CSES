package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class SubarraySumsII {
 
    public static long subarraySumsI(int t, int[] nums){
		HashMap<Long, Integer> map = new HashMap<>();
		long sum = 0;
		long ans = 0;

		for(int i = 0; i < nums.length; i++){
			sum += nums[i];
			if(sum == t) ans++;

			long rem = sum - t;
			if(map.containsKey(rem)){
				ans += map.get(rem);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		return ans;
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

		bw.write(Long.toString(subarraySumsI(t, nums)));
		
		bw.flush();
		br.close();
		bw.close();

	}
    
}
