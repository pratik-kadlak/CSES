package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class SubarrayDivisibility {

    public static long subarrayDivisibility(int[] nums){
		int n = nums.length;
		HashMap<Long, Integer> map = new HashMap<>();
		long sum = 0;
		long ans = 0;

		for(int i = 0; i < nums.length; i++){
			sum += nums[i];
			long rem = (sum % n);
			if(rem == 0) ans++;
			if(rem < 0) rem += n;

			if(map.containsKey(rem)){
				ans += map.get(rem);
			}

			map.put(rem, map.getOrDefault(rem, 0) + 1);
		}

		return ans;
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

		bw.write(Long.toString(subarrayDivisibility(nums)));
		
		bw.flush();
		br.close();
		bw.close();

	}
    
}