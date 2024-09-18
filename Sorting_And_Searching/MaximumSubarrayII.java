package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class MaximumSubarrayII {
 
    public static long maxSubarrayII(int a, int b, int[] nums){
		int n = nums.length;
		long sum = 0;
		long[] prefixSum = new long[n+1];
		for(int i = 1; i <= n; i++){
			sum += nums[i-1];
			prefixSum[i] = sum;
		}

		long gMax = Long.MIN_VALUE;
		TreeMap<Long, Integer> treemap = new TreeMap<>();

		for(int i = a; i <= b; i++){
			treemap.put(prefixSum[i], treemap.getOrDefault(prefixSum[i], 0) + 1);
		}

		for(int i = 1; i <= n-a+1; i++){
			gMax = Math.max(gMax, treemap.lastKey() - prefixSum[i-1]);
			treemap.put(prefixSum[i+a-1], treemap.get(prefixSum[i+a-1]) - 1);
			if(treemap.get(prefixSum[i+a-1])==0) treemap.remove(prefixSum[i+a-1]);

			if(i+b <= n){
				treemap.put(prefixSum[i+b], treemap.getOrDefault(prefixSum[i+b], 0) + 1);
			}
		}
		return gMax;
	}
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int a = Integer.parseInt(f[1]);
		int b = Integer.parseInt(f[2]);

		String[] s = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(s[i]);
		}
		
		bw.write(Long.toString(maxSubarrayII(a, b, nums)));

		bw.flush();
		br.close();
		bw.close();
 
	}

}
