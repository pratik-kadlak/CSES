package Introductory_Problems;

import java.util.Scanner;

public class IncreasingArray {

    public static long increasingArray(int n, int[] nums){
        long moves = 0;
        for(int i = 1; i < n; i++){
            if(nums[i] < nums[i-1]){
                moves += (nums[i-1] - nums[i]);
                nums[i] = nums[i-1];
            }
        }

        return moves;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] nums = new int[n];

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }

        sc.close();

        System.out.println(increasingArray(n, nums));

    }

}
