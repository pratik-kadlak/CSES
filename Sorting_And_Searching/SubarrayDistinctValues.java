package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class SubarrayDistinctValues {
    
    public static long subarrayDistinctValues(int k, int[] nums){
		int n = nums.length;
		HashMap<Integer, Integer> map = new HashMap<>();
		long ans = 0;

		int l = 0, r = 0;

		while(r < n){
			map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
			while(map.size() > k){
				map.put(nums[l], map.get(nums[l])-1);
				if(map.get(nums[l]) == 0) map.remove(nums[l]);
				l++;
			}
			ans += r - l + 1;
			r++;
		}

		return ans;
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

		bw.write(Long.toString(subarrayDistinctValues(k, nums)));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
