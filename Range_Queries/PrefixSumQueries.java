package Range_Queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class PrefixSumQueries {
    static class Pair{
		long pref, sum;
		Pair(long pref, long sum){
			this.pref = pref;
			this.sum = sum;
		}

		Pair(){
			this.pref = 0;
			this.sum = 0;
		}

	}

	static Pair[] segTree;
	static int size;

	public static void init(int n){
		size = 1;
		while(size < n){
			size *= 2;
		}
		segTree = new Pair[2 * size];
		for(int i = 0; i < (2*size); i++){
			segTree[i] = new Pair(0, 0);
		}

	}

	public static void set(int idx, int val, int x, int lx, int rx){
		if(rx - lx == 1){
			segTree[x].pref = val;
			segTree[x].sum = val;
			return;
		}

		int mid = (lx + rx) / 2;
		if(idx < mid) set(idx, val, 2*x+1, lx, mid);
		else set(idx, val, 2*x+2, mid, rx);

		segTree[x].pref = Math.max(segTree[2*x+1].pref, segTree[2*x+1].sum + segTree[2*x+2].pref);
		segTree[x].sum = segTree[2*x+1].sum + segTree[2*x+2].sum;
	}

	public static void set(int idx, int val){
		set(idx, val, 0, 0, size);
	}

	public static Pair find(int l, int r, int x, int lx, int rx){
		if(l >= rx || r <= lx) return new Pair();
		if(l <= lx && r >= rx) return segTree[x];
		int mid = (lx + rx) / 2;
		Pair p1 = find(l, r, 2*x+1, lx, mid);
		Pair p2 = find(l, r, 2*x+2, mid, rx);

		Pair p = new Pair();
		p.pref = Math.max(p1.pref, p1.sum + p2.pref);
		p.sum = p1.sum + p2.sum;
		return p;
	}

	public static long find(int l, int r){
		Pair res = find(l, r, 0, 0, size);
		return Math.max(res.pref, 0);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int q = Integer.parseInt(f[1]);

		init(n);
		String[] h = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			set(i, Integer.parseInt(h[i]));
		}

		while(q > 0){
			String[] qry = br.readLine().split(" ");
			int op = Integer.parseInt(qry[0]);
			if(op == 1){
				int k = Integer.parseInt(qry[1]);
				int u = Integer.parseInt(qry[2]);
				set(k-1, u);
			} else {
				int a = Integer.parseInt(qry[1]);
				int b = Integer.parseInt(qry[2]);

				bw.write(find(a-1, b) + "\n");
			}
			q--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}
}
