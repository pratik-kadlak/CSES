package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class NestedRangesCheck {
    
    static class Range {
		int left, right, index;
		Range(int left, int right, int index){
			this.left = left;
			this.right = right;
			this.index = index;
		}
	}
	
	public static void nestedRangeCheck(int[][] ranges){
		int n = ranges.length;
		Range[] sortedRange = new Range[n];
		for(int i = 0; i < n; i++){
			sortedRange[i] = new Range(ranges[i][0], ranges[i][1], i);
		}

		Arrays.sort(sortedRange, new Comparator<Range>() {
			@Override
			public int compare(Range r1, Range r2) {
				if(r1.left != r2.left) return Integer.compare(r1.left, r2.left);
				else return Integer.compare(r2.right, r1.right);
			}
		});

		boolean[] contains = new boolean[n];
		boolean[] contained = new boolean[n];

		int minRightBoundary = Integer.MAX_VALUE;
		for(int i = n-1; i >= 0; i--){
			if(sortedRange[i].right >= minRightBoundary){
				contains[sortedRange[i].index] = true;
			} else {
				minRightBoundary = sortedRange[i].right;
			}
		}

		int maxRightBoundary = 0;
		for(int i = 0; i < n; i++){
			if(sortedRange[i].right <= maxRightBoundary){
				contained[sortedRange[i].index] = true;
			} else {
				maxRightBoundary = sortedRange[i].right;
			}
		}

		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();

		for(int i = 0; i < n; i++){
			if(contains[i]) s1.append(1 + " ");
			else s1.append(0 + " ");
		}

		for(int i = 0; i < n; i++){
			if(contained[i]) s2.append(1 + " ");
			else s2.append(0 + " ");
		}

		System.out.println(s1);
		System.out.println(s2);

		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		int[][] ranges = new int[n][2];
		for(int i = 0; i < n; i++){
			String[] arr = br.readLine().split(" ");
			ranges[i] = new int[2];
			ranges[i][0] = Integer.parseInt(arr[0]);
			ranges[i][1] = Integer.parseInt(arr[1]);
		}
		
		nestedRangeCheck(ranges);
		
		bw.flush();
		br.close();
		bw.close();

	}

}
