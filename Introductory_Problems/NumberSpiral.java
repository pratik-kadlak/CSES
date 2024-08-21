package Introductory_Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class NumberSpiral {
    
    public static long numberSpiral(long row, long col){
        long m = Math.max(row, col);
        long diff = Math.abs(row-col);
        long corner = m * (m-1) + 1;

        if(row == col) return corner;
        if(row > col) {
            if(row % 2 == 0) return (long) corner + diff;
            else return (long) corner - diff;
        } else {
            if(col % 2 == 0) return (long) corner - diff;
            else return (long) corner + diff;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(br.readLine());

        for(int i = 0; i < q; i++){
            String[] input = br.readLine().split(" ");
            long row = Long.parseLong(input[0]);
            long col = Long.parseLong(input[1]);

            bw.write(numberSpiral(row, col) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }

}
