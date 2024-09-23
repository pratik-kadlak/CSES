package Range_Queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class HotelQueries {

    static int[] segTree;
	static int size;

	public static void init(int n){
		size = 1;
		while(size < n){
			size *= 2;
		}
		segTree = new int[2 * size];
	}

	public static void set(int idx, int val, int x, int lx, int rx){
		if(rx - lx == 1){
			segTree[x] = val;
			return;
		}
		int mid = (lx + rx) / 2;
		if(idx < mid) set(idx, val, 2*x+1, lx, mid);
		else set(idx, val, 2*x+2, mid, rx);

		segTree[x] = Math.max(segTree[2*x+1], segTree[2*x+2]);
	}

	public static void set(int idx, int val){
		set(idx, val, 0, 0, size);
	}

	public static int find(int val, int x, int lx, int rx){
		if(rx - lx == 1) return lx;
		int mid = (lx + rx) / 2;
		if(segTree[2*x+1] >= val) return find(val, 2*x+1, lx, mid);
		else return find(val, 2*x+2, mid, rx);
	}

	public static int find(int val){
		if(segTree[0] < val) return -1;
		return find(val, 0, 0, size);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] f = br.readLine().split(" ");
		int n = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1]);

		init(n);
		String[] h = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			set(i, Integer.parseInt(h[i]));
		}

		StringBuilder str = new StringBuilder();
		String[] q = br.readLine().split(" ");

		for(int i = 0; i < m; i++){
			int r = Integer.parseInt(q[i]);
			int hotel = find(r);
			str.append(hotel + 1 + " ");
			if(hotel != -1) set(hotel, segTree[((size)-1)+hotel]-r);
		}

		bw.write(str.toString());
		
		bw.flush();
		br.close();
		bw.close();
 
	}
}
