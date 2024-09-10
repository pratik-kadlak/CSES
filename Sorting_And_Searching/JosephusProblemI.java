package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class JosephusProblemI {

    public static void josephusI(ArrayList<Integer> nums, ArrayList<Integer> ans){
		if(nums.size() == 1){
			ans.add(nums.get(0));
			return;
		}		

		for(int i = 1; i < nums.size(); i+=2){
			ans.add(nums.get(i));
		}

		ArrayList<Integer> newNums = new ArrayList<>();
		if(nums.size() % 2 == 0){
			for(int i = 0; i < nums.size(); i+=2)
				newNums.add(nums.get(i));
		} else {
			newNums.add(nums.remove(nums.size()-1));
			for(int i = 0; i < nums.size(); i+=2)
				newNums.add(nums.get(i));
		}

		josephusI(newNums, ans);

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i <= n; i++) nums.add(i); 

		ArrayList<Integer> ans = new ArrayList<>();
		josephusI(nums, ans);
		
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < ans.size(); i++){
			str.append(ans.get(i) + " ");
		}

		bw.write(str.toString());
		
		bw.flush();
		br.close();
		bw.close();

	}


}
