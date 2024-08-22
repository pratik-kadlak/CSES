package Introductory_Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class TowerOfHanoi {

    public static void towerOfHanoi(int n, int src, int dest, int aux){
        if(n == 0) return;
        towerOfHanoi(n-1, src, aux, dest);
        System.out.println(src + " " + dest);
        towerOfHanoi(n-1, aux, dest, src);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        System.out.println((int) (Math.pow(2, n)-1));
        towerOfHanoi(n,1,3,2);  

        bw.flush();
        br.close();
        bw.close();
    }

}
