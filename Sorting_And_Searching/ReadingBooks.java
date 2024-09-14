package Sorting_And_Searching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadingBooks {
    
    public static long readingBooks(int[] books){
		long sum = 0;
		long max = 0;

		for(int i = 0; i < books.length; i++){
			sum += books[i];
			max = Math.max(books[i], max);
		}

		if(max <= (sum - max)) return sum;
		return 2 * max;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		String[] s = br.readLine().split(" ");
		int[] books = new int[n];
		for(int i = 0; i < n; i++){
			books[i] = Integer.parseInt(s[i]);
		}

		bw.write(Long.toString(readingBooks(books)));
		
		bw.flush();
		br.close();
		bw.close();

	}

}
