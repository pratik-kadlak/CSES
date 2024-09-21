package Range_Queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class DynamicRangeSumQueries {

    static int[] segTree;
	static int size;

	public static void init(int n){
		size = 1;
		while(size < n){
			size *= 2;
		}
		segTree = new int[2 * size];
		for(int i = 0; i < segTree.length; i++){
			set(i, 0);
		}
	}

	public static void set(int idx, int val, int x, int lx, int rx){
		if(rx - lx == 1){
			segTree[x] = val;
			return;
		}

		int mid = (lx + rx) / 2;
		if(idx < mid) set(idx, val, 2*x+1, lx, mid);
		else set(idx, val, 2*x+2, mid, rx);

		segTree[x] = segTree[2*x+1] + segTree[2*x+2];
	}

	public static void set(int idx, int val){
		set(idx, val, 0, 0, size);
	}

	public static int find(int l, int r, int x, int lx, int rx){
		if(r <= lx || l >= rx) return 0; 
		if(l <= lx && r >= rx) return segTree[x];

		int mid = (lx + rx) / 2;
		int m1 = find(l, r, 2*x+1, lx, mid);
		int m2 = find(l, r, 2*x+2, mid, rx);

		return m1+m2;
	}

	public static int find(int l, int r){
		return find(l, r, 0, 0, size);
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
			String[] q = br.readLine().split(" ");
			int o = Integer.parseInt(q[0]);

			if(o == 1){
				int idx = Integer.parseInt(q[1]);
				int val = Integer.parseInt(q[2]);
				set(idx-1, val);
			} else {
				int l = Integer.parseInt(q[1]);
				int r = Integer.parseInt(q[2]);
				bw.write(find(l-1, r) + "\n");
			}

			m--;
		}
		
		bw.flush();
		br.close();
		bw.close();
 
	}

}
