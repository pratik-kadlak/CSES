package Introductory_Problems;

import java.util.Scanner;

public class Permutations {

    public static String permutation(int n){
        if(n == 2 || n == 3) {
            return "NO SOLUTION";
        }

        StringBuilder str = new StringBuilder();

        for(int j = 2; j <= n; j+=2){
            str.append(j + " ");
        }

        for(int i = 1; i <= n; i+=2){
            str.append(i + " ");
        }

        return str.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        System.out.println(permutation(n));
    }   
}
