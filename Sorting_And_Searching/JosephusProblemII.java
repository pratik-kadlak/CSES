package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class JosephusProblemII {
    public static void josephusII(int k, int n, ArrayList<Integer> ans){
		LinkedList<Integer> children = new LinkedList<>();
        
        // Initialize the list of children
        for (int i = 1; i <= n; i++) {
            children.add(i);
        }
        
        // Starting position (index)
        int index = 0;
        
        // Simulate the process of removal
        while (!children.isEmpty()) {
            // Calculate the next index by skipping k children
            index = (index + k) % children.size();
            
            // Remove the child at the current index and print it
            ans.add(children.remove(index));
        }

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] firstLine = br.readLine().split(" ");

		int n = Integer.parseInt(firstLine[0]);
		int k = Integer.parseInt(firstLine[1]);

		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i <= n; i++) nums.add(i); 

		ArrayList<Integer> ans = new ArrayList<>();
		josephusII(k, n, ans);
		
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
