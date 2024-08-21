package Introductory_Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class PalindromeReorder {

    public static String palindromeReorder(char[] ch){
        int[] freq = new int[26];
        for(int i = 0; i < ch.length; i++)
            freq[ch[i] - 'A']++;

        int oddCnt = 0;
        for(int i = 0; i < 26; i++){
            if(freq[i] % 2 == 1) oddCnt++;
        }

        if(oddCnt > 1) return "NO SOLUTION";

        char[] palindromeString = new char[ch.length];

        int oddIndex = -1;
        int l = 0, r = ch.length-1;
        for(int i = 0; i < 26; i++){
            if(freq[i] == 0) continue;
            if(freq[i] % 2 == 1){
                oddIndex = i;
                continue;
            }

            while(freq[i] > 0){
                palindromeString[l] = (char)('A' + i);
                palindromeString[r] = (char)('A' + i);
                l++;
                r--;
                freq[i] -= 2;
            }
        }

        if(oddIndex != -1){
            for(int i = l; i <= r; i++){
                palindromeString[i] = (char) ('A' + oddIndex);
            }
        }

        return new String(palindromeString);

    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] ch = br.readLine().toCharArray();

        bw.write(palindromeReorder(ch) + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

}
