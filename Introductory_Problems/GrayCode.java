package Introductory_Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class GrayCode {

    public static void grayCode(int n) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(0);

        for(int i = 1; i <= n; i++){
            
            int xr = 1 << (i-1);
            
            int curSize = nums.size();
            for(int j = curSize; j > 0; j--){
                nums.add((nums.get(j-1)) ^ xr);
            }
        }

        for(int i = 0; i < nums.size(); i++){
            int var = 1 << (n-1);
            StringBuilder str  = new StringBuilder();
            while (var > 0) {
                int bit = nums.get(i) / var;
                str.append(bit);
                nums.set(i, nums.get(i) - (var * bit));
                var = var >> 1;
            }   
            System.out.println(str.toString());
        }

    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        grayCode(n);  

        bw.flush();
        br.close();
        bw.close();
    }
    
}
