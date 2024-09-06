package Introductory_Problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DigitQueries {
    
   public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int q = Integer.parseInt(br.readLine());

		while(q > 0){
			long k = Long.parseLong(br.readLine());

			int numDigits = 1; // count num of digits 
			long count = 9; // number of digits contributed in k length numbers (for k=1 -> count=9, for k=2 -> count=90 as there are 90 2 digit numbers)
			long start = 1; // first number with 'length' digits

			while(k > numDigits * count){
				k -= numDigits * count;
				numDigits++;
				count *= 10;
				start *= 10;
			}

			long num = start + (k-1) / numDigits;
			long digit_index = (k-1) % numDigits;
			String str = "" + num;

			System.out.println(str.charAt((int) digit_index));

			q--;
		}
		
		bw.flush();
		br.close();
		bw.close();

	}

}
