package Range_Queries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SubarraySumQueries {
    static class Tuple {
		long seg, sum, pre, suf;
		Tuple(long seg, long sum, long pre, long suf){
			this.seg = seg;
			this.sum = sum;
			this.pre = pre;
			this.suf = suf;
		}
	}
 
	static Tuple[] segTree;
	static int size;
	static int INV = Integer.MIN_VALUE / 2;
 
	public static void init(int n){
		size = 1;
		while(size < n){
			size *= 2;
		}
		segTree = new Tuple[2 * size];
 
		for(int i = 0; i < segTree.length; i++){
			segTree[i] = new Tuple(0, 0, 0, 0);
		}
		
		for(int i = 0; i < size; i++){
			set(i, INV);
		}
	}
 
	public static void set(int idx, int val, int x, int lx, int rx){
		if(rx - lx == 1){
			segTree[x].seg = val;
			segTree[x].sum = val;
			segTree[x].pre = val;
			segTree[x].suf = val;
			return;
		}
		
		int mid = (lx + rx) / 2;
		if(idx < mid) set(idx, val, 2*x+1, lx, mid);
		else set(idx, val, 2*x+2, mid, rx); 
 
		segTree[x].sum = segTree[2*x+1].sum + segTree[2*x+2].sum;
		segTree[x].pre = Math.max(segTree[2*x+1].pre, segTree[2*x+1].sum + segTree[2*x+2].pre);
		segTree[x].suf = Math.max(segTree[2*x+2].suf, segTree[2*x+1].suf + segTree[2*x+2].sum);
		segTree[x].seg = Math.max(segTree[2*x+1].seg, Math.max(segTree[2*x+2].seg, segTree[2*x+1].suf + segTree[2*x+2].pre));
	}
 
	public static void set(int idx, int val){
		set(idx, val, 0, 0, size);
	}
 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1]);
 
		init(n);
		String[] s = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			set(i, Integer.parseInt(s[i]));
		}
  
		while(m > 0){
			String[] op = br.readLine().split(" ");
			int index = Integer.parseInt(op[0]);
			int value = Integer.parseInt(op[1]);
 
			set(index-1, value);
			if(segTree[0].seg < 0) System.out.println(0);
			else System.out.println(segTree[0].seg);
			m--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}
}
