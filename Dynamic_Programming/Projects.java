package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Projects {
    public static long projects(int[][] proj){
		int n = proj.length;
		TreeMap<Integer, Integer> compress = new TreeMap<>();
		for(int[] it: proj){
			compress.put(it[0], 0);
			compress.put(it[1], 0);
		}

		int coords = 0;
		for (Map.Entry<Integer, Integer> entry : compress.entrySet()) {
			entry.setValue(coords);  // Update the value for the current key
			coords++;
		}

		ArrayList<ArrayList<Pair>> project = new ArrayList<>();
		for(int i = 0; i < coords; i++) project.add(new ArrayList<>());

		for(int i = 0; i < n; i++){
			project.get(compress.get(proj[i][1])).add(new Pair(compress.get(proj[i][0]), proj[i][2]));
		}

		long[] dp = new long[coords];
		for(int i = 0; i < coords; i++){
			if(i > 0) dp[i] = dp[i-1];
			for(Pair p: project.get(i)){
				dp[i] = Math.max(dp[i], dp[p.arrival] + p.reward);
			}
		}
		
		// System.out.println(Arrays.toString(dp));
		return dp[coords-1];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[][] proj = new int[n][3];
		for(int i = 0; i < n; i++){
			String[] f = br.readLine().split(" ");
			int[] x = new int[3];
			for(int j = 0; j < 3; j++){
				x[j] = Integer.parseInt(f[j]);
			}
			x[1]++;
			proj[i] = x;
		}

		bw.write(Long.toString(projects(proj)));

		bw.flush();
		br.close();
		bw.close();
 
	}
}

class Pair {
	int arrival, reward;
	Pair(int arrival, int reward){
		this.arrival = arrival;
		this.reward = reward;
	}
}