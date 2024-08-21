package Introductory_Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class CoinPiles {

    public static String coinPiles(int a, int b){
        if((a+b)%3 == 0 && 2*a >= b && 2*b >= a) return "YES";
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while(t > 0){
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            bw.write(coinPiles(a, b) + "\n");
            t--;
        }

        bw.flush();
        br.close();
        bw.close();        

    }

}
