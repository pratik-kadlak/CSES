package Introductory_Problems;

import java.util.Scanner;

public class BitStrings {

    static int MOD =  (int) 1e9 + 7;
    public static long power(int n){
        if(n == 0) return 1;
        long half = power(n/2);
        if(n % 2 == 0) return (half * half) % MOD;
        return (2 * half * half) % MOD;
    }

    public static double bitStrings(int n){
        double ans = power(n);
        return ans;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        System.out.println((long) bitStrings(n));

    }

}
