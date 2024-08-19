package Introductory_Problems;

import java.util.Scanner;

public class MissingNumber {

    public static long missingNumber(int n, int[] nums){
        long sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }

        long actualSum = (long) n * (n + 1) / 2;

        return actualSum - sum;

    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] nums = new int[n-1];
        for(int i = 0; i < n-1; i++){
            nums[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(missingNumber(n, nums));
    }

}
