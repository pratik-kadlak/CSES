package Range_Queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class RangeUpdateQueries {
    
	static long[] segTree;
	static int size;

	public static void init(int n){
		size = 1;
		while(size < n){
			size *= 2;
		}
		segTree = new long[2 * size];
	}

	public static void add(int l, int r, int val, int x, int lx, int rx){
		if(r <= lx || l >= rx) return;
		if(l <= lx && r >= rx){
			segTree[x] += val;
			return;
		}

		int mid = (lx + rx) / 2;
		add(l, r, val, 2*x+1, lx, mid);
		add(l, r, val, 2*x+2, mid, rx);
	}

	public static void add(int l, int r, int val){
		add(l, r, val, 0, 0, size);
	}

	public static long find(int k, int x, int lx, int rx){
		if(rx - lx == 1){
			return segTree[x];
		}

		int mid = (lx + rx) / 2;
		if(k < mid) return segTree[x] + find(k, 2*x+1, lx, mid);
		else return segTree[x] + find(k, 2*x+2, mid, rx);
	}

	public static long find(int k){
		return find(k, 0, 0, size);
	}


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int t = Integer.parseInt(f[1]);

		init(n);
		String[] s = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			add(i, i+1, Integer.parseInt(s[i]));
		}

		while(t > 0){
			String[] q = br.readLine().split(" ");
			int op = Integer.parseInt(q[0]);

			if(op == 1){
				int l = Integer.parseInt(q[1]);
				int r = Integer.parseInt(q[2]);
				int v = Integer.parseInt(q[3]);
				add(l-1, r, v);
			} else {
				int k = Integer.parseInt(q[1]);
				bw.write(Long.toString(find(k-1)) + "\n");
			}

			t--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}
}
