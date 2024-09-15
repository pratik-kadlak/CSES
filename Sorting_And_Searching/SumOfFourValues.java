package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SumOfFourValues {
    
    static class PairSum{
		int i, j, sum;
		PairSum(int i, int j, int sum){
			this.i = i;
			this.j = j;
			this.sum = sum;
		}
	}

	public static boolean noCommon(PairSum a, PairSum b){
		if(a.i == b.i || a.i == b.j || a.j == b.i || a.j == b.j) 
			return false;
		return true;
	}

	public static void findFourElements(int[] nums, int target){
		int i, j;
		int len = nums.length;

		PairSum[] aux = new PairSum[len*(len-1)/2];

		int k = 0;
		for(i = 0; i < len; i++){
			for(j = i+1; j < len; j++){
				aux[k] = new PairSum(i+1, j+1, nums[i]+ nums[j]);
				k++;
			}
		}

		Arrays.sort(aux, (x,y)->x.sum-y.sum);

		i = 0; j = aux.length-1;
		while(i < aux.length && j >= 0){
			if(aux[i].sum + aux[j].sum == target && noCommon(aux[i], aux[j])){
				System.out.print(aux[i].i + " ");
				System.out.print(aux[i].j + " ");
				System.out.print(aux[j].i + " ");
				System.out.print(aux[j].j + " ");
				return;
			} else if (aux[i].sum + aux[j].sum < target) i++;
			else j--;
		}

		System.out.println("IMPOSSIBLE");
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

		findFourElements(nums, t);;
		
		bw.flush();
		br.close();
		bw.close();

	}

}
