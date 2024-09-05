package Introductory_Problems;

import java.util.Scanner;

public class TwoKnights {

    public static void twoKnights(int n){
        if(n == 1) {
            System.out.println(0);
            return;
        } else if(n == 2){
            System.out.println(0);
            System.out.println(6);
            return;
        } else if(n == 3){
            System.out.println(0);
            System.out.println(6);
            System.out.println(28);
            return;
        }

        // n >= 4;
        long[] dp = new long[n+1];
        dp[1] = 0; dp[2] = 6; dp[3] = 28;

        for(int i = 4; i <= n; i++){
            dp[i] = 30 + (i-4) * 12 + 3 * dp[i-1] - 3 * dp[i-2] + 1 * dp[i-3];
        }

        for(int i = 1; i <= n; i++){
            System.out.println(dp[i]);
        }
    }

    // Actual Solution 
    public static void twoKnights1(int n){
        for(int i = 1; i <= n; i++){
            int totalWays = (i * i) * (i * i - 1) / 2;
            int attackingWays = 4 * (i - 1) * (i - 2);
            System.out.println(totalWays - attackingWays);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        twoKnights(n);
        twoKnights1(n);
    }
    
}
