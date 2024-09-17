package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class SlidingWindowMedian {
    public static String slidingWindowMedian(int k, int[] nums){
		int n = nums.length;
		int mxSize = 0, mnSize = 0;
		int idealSize = k / 2;
		if(k % 2 == 1) idealSize++;
		TreeMap<Integer, Integer> maxTree = new TreeMap<>();
		TreeMap<Integer, Integer> minTree = new TreeMap<>();
		int[] ans = new int[n-k+1];
 
		for(int i = 0; i < k; i++){
			minTree.put(nums[i], minTree.getOrDefault(nums[i], 0)+1);
			mnSize++;
		}
 
		for(int i = k; i <= n; i++){
			while(mnSize > idealSize){
				int toTransfer = minTree.firstKey();
				if(minTree.get(toTransfer) == 1) minTree.remove(toTransfer);
				else minTree.put(toTransfer, minTree.get(toTransfer)-1);
				mnSize--;
				maxTree.put(toTransfer, maxTree.getOrDefault(toTransfer, 0) + 1);
				mxSize++;
 
			}
 
			if(k%2==0) ans[i-k] = maxTree.lastKey();
			else ans[i-k] = minTree.firstKey();

 
			if(maxTree.containsKey(nums[i-k])){
				if(maxTree.get(nums[i-k]) == 1) maxTree.remove(nums[i-k]);
				else maxTree.put(nums[i-k], maxTree.get(nums[i-k])-1);
				mxSize--;
			} else {
				if(minTree.get(nums[i-k]) == 1) minTree.remove(nums[i-k]);
				else minTree.put(nums[i-k], minTree.get(nums[i-k])-1);
				mnSize--;
			}
 
			if(i == n) break;
 
			int newElement = nums[i];
            if (!maxTree.isEmpty() && newElement < maxTree.lastKey()) {
                maxTree.put(newElement, maxTree.getOrDefault(newElement, 0) + 1);
                mxSize++;
            } else {
                minTree.put(newElement, minTree.getOrDefault(newElement, 0) + 1);
                mnSize++;
            }

            // Rebalance after adding the new element
            while (mnSize > idealSize) {
                int toTransfer = minTree.firstKey();
                if (minTree.get(toTransfer) == 1) minTree.remove(toTransfer);
                else minTree.put(toTransfer, minTree.get(toTransfer) - 1);
                mnSize--;
                maxTree.put(toTransfer, maxTree.getOrDefault(toTransfer, 0) + 1);
                mxSize++;
            }

            while (mxSize > k / 2) {
                int toTransfer = maxTree.lastKey();
                if (maxTree.get(toTransfer) == 1) maxTree.remove(toTransfer);
                else maxTree.put(toTransfer, maxTree.get(toTransfer) - 1);
                mxSize--;
                minTree.put(toTransfer, minTree.getOrDefault(toTransfer, 0) + 1);
                mnSize++;
            }
		}
 
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < ans.length; i++)
			str.append(ans[i] + " ");
	
		return str.toString();
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
 
		bw.write(slidingWindowMedian(k, nums));
		
		bw.flush();
		br.close();
		bw.close();
 
	}
}
