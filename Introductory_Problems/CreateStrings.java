package Introductory_Problems;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class CreateStrings {
    
    public static void swap(char[] ch, int i, int j){
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}

	public static void createStrings(char[] ch, int left, int right, ArrayList<String> ans){
		if(left == ch.length){
			ans.add(new String(ch));
			return;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = left; i <= right; i++){
			if(map.containsKey((ch[left]-'a')*26+(ch[i]-'a'))) continue;
			map.put((ch[left]-'a')*26+(ch[i]-'a'), 1);
			swap(ch, left, i);
			createStrings(ch, left+1, right, ans);
			swap(ch, left, i);
		}
	}	

	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String firstLine = br.readLine();
		
		char[] ch = firstLine.toCharArray();
		ArrayList<String> ans = new ArrayList<>();
		createStrings(ch, 0, ch.length-1, ans);

		System.out.println(ans.size());
		Collections.sort(ans);
		for(String s: ans){
			System.out.println(s);

		}

		bw.flush();
		br.close();
		bw.close();

	}

}
