package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class CollectingNumbersII {
    
    public static String collectingNumbersII(int[] nums, int[][] operations){
		int n = nums.length;
		int[] map = new int[n+2];
		map[0] = Integer.MIN_VALUE;
		map[map.length-1] = Integer.MAX_VALUE;

		for(int i = 0; i < nums.length; i++){
			map[nums[i]] = i+1;
		}

		int inversions = 0;
		for(int i = 1; i <= n; i++){
			if(map[i] < map[i-1])
				inversions++;
		}

		StringBuilder str = new StringBuilder();

		Set<Pair<Integer, Integer>> set = new HashSet<>();
		for(int[] it: operations){
			set.add(new Pair<>(nums[it[0]-1]-1,nums[it[0]-1]));
			set.add(new Pair<>(nums[it[0]-1], nums[it[0]-1]+1));
			set.add(new Pair<>(nums[it[1]-1]-1, nums[it[1]-1]));
			set.add(new Pair<>(nums[it[1]-1], nums[it[1]-1]+1));

			for(Pair<Integer, Integer> p: set){
				if(map[p.second] < map[p.first]) inversions--;
			}

			int temp = map[nums[it[0]-1]];
			map[nums[it[0]-1]] = map[nums[it[1]-1]];
			map[nums[it[1]-1]] = temp;

			temp = nums[it[0]-1];
			nums[it[0]-1] = nums[it[1]-1];
			nums[it[1]-1] = temp;


			for(Pair<Integer, Integer> p: set){
				if(map[p.second] < map[p.first]) inversions++;
			}

			set.clear();
			str.append((inversions+1) + "\n");
		}
		
		return str.toString();
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");
		int n = Integer.parseInt(firstLine[0]);
		int m = Integer.parseInt(firstLine[1]);

		String[] secondLine = br.readLine().split(" ");
		int[] nums = new int[n];
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(secondLine[i]);
		}

		int[][] operations = new int[m][2];
		for(int i = 0; i < m; i++){
			String[] ops = br.readLine().split(" ");
			operations[i] = new int[2];
			operations[i][0] = Integer.parseInt(ops[0]);
			operations[i][1] = Integer.parseInt(ops[1]);
		}


		bw.write(collectingNumbersII(nums, operations));
		
		bw.flush();
		br.close();
		bw.close();

	}

}

class Pair<U, V> {
	public final U first;
	public final V second;

	public Pair(U first, V second){
		this.first = first;
		this.second = second;
	}

	@ Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Pair<?, ?> pair = (Pair<?, ?>) o;
		return first.equals(pair.first) && second.equals(pair.second); 
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}

