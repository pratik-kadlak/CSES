package Sorting_And_Searching;

public class NestedRangesCount {
    
    // The solution contains use of Segment Tree 
    // So we are gonna solve it afterwards.

}


// Below is my attempted solution which is wrong

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.io.BufferedWriter;
// import java.io.OutputStreamWriter;
// import java.util.*;

// public  class Main {	

// 	static class Range {
// 		int left, right, index;
// 		Range(int left, int right, int index){
// 			this.left = left;
// 			this.right = right;
// 			this.index = index;
// 		}
// 	}

// 	static class Pair {
// 		int index; Range range;
// 		Pair(int index, Range range){
// 			this.index = index;
// 			this.range = range;
// 		}
// 	}

// 	public static void nestedRangeCount(int[][] ranges){
// 		int n = ranges.length;
// 		Range[] sortedRange = new Range[n];
// 		for(int i = 0; i < n; i++){
// 			sortedRange[i] = new Range(ranges[i][0], ranges[i][1], i);
// 		}

// 		Arrays.sort(sortedRange, new Comparator<Range>() {
// 			@Override
// 			public int compare(Range r1, Range r2){
// 				if(r1.left != r2.left) return Integer.compare(r1.left, r2.left);
// 				else return Integer.compare(r2.right, r1.right);
// 			}
// 		});

// 		int[] contains = new int[n];
// 		int[] contained = new int[n];

// 		Stack<Pair> stack = new Stack<>();
// 		for(int i = 0; i < n; i++){
// 			while(!stack.isEmpty() && stack.peek().range.right < sortedRange[i].right){
// 				Pair p = stack.pop();
// 				contains[p.range.index] = i - p.index - 1;
// 				contained[p.range.index] = stack.size();
// 			} 
// 			stack.push(new Pair(i, sortedRange[i]));
// 		}

// 		while(!stack.isEmpty()){
// 			Pair p = stack.pop();
// 			contains[p.range.index] = n - p.index - 1;
// 			contained[p.range.index] = stack.size();
// 		}

// 		StringBuilder s1 = new StringBuilder();
// 		StringBuilder s2 = new StringBuilder();

// 		for(int i = 0; i < n; i++){
// 			s1.append(contains[i] + " ");
// 			s2.append(contained[i] + " ");
// 		}

// 		System.out.println(s1.toString());
// 		System.out.println(s2.toString());

// 	}
	
// 	public static void main(String[] args) throws IOException{
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

// 		int n = Integer.parseInt(br.readLine());

// 		int[][] ranges = new int[n][2];
// 		for(int i = 0; i < n; i++){
// 			String[] arr = br.readLine().split(" ");
// 			ranges[i] = new int[2];
// 			ranges[i][0] = Integer.parseInt(arr[0]);
// 			ranges[i][1] = Integer.parseInt(arr[1]);
// 		}
		
// 		nestedRangeCount(ranges);
		
// 		bw.flush();
// 		br.close();
// 		bw.close();

// 	}

// }

