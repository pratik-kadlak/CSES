package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class TasksAndDeadlines {
    
    public static long tasksAndDeadlines(int[][] tasks){
		Arrays.sort(tasks, (a,b) -> a[0] - b[0]);

		long currTime = 0;
		long summationD = 0;
		long summationF = 0;
		
		for(int[] it: tasks){
			summationD += it[1];
			currTime += it[0];
			summationF += currTime;
		}

		return summationD - summationF;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		int[][] tasks = new int[n][2];
		for(int i = 0; i < n; i++){
			String[] s = br.readLine().split(" ");
			tasks[i] = new int[2];
			tasks[i][0] = Integer.parseInt(s[0]);
			tasks[i][1] = Integer.parseInt(s[1]);
		}

		bw.write(Long.toString(tasksAndDeadlines(tasks)));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
