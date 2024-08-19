package Introductory_Problems;

import java.util.Scanner;

public class Repetitions {

    public static int repititions(String word){
        char[] ch = word.toCharArray();
        int ans = 1;

        int left = 0, right = 0;

        while(right < ch.length){
            while(right < ch.length && ch[right] == ch[left]){
                right++;
            }

            ans = Math.max(ans, right-left);
            left = right;
        }

        ans = Math.max(ans, right-left);
        return ans;

    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        sc.close();

        System.out.println(repititions(word));
    }   
    
}
