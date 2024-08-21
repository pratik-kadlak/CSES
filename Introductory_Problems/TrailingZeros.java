package Introductory_Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;


public class TrailingZeros {

    public static int trailingZeros(int n){
        int cnt = 0;

        while(n >= 5){
            int q = n / 5;
            n /= 5;
            cnt += q;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        bw.write(String.valueOf(trailingZeros(n)) + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
