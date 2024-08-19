package Introductory_Problems;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class TwoSets {

    public static void twoSets(int n){
        long sum = (long) n * (n+1) / 2;

        if(sum % 2 == 0) System.out.println("YES");
        else {
            System.out.println("NO");
            return;
        }

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int i = 1; i <= n; i++) set1.add(i);

        sum /= 2;
        while(sum != 0){
            if(sum >= n){
                set1.remove(n);
                set2.add(n);
                sum -= n;
            } 
            n--;
        }

        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();

        System.out.println(set1.size());
        for(int it: set1){
            str1.append(it + " ");
        }

        System.out.println(str1.toString());

        System.out.println(set2.size());
        for(int it: set2){
            str2.append(it + " ");
        }

        System.out.println(str2.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        sc.close();

        twoSets(n);
    }

}
