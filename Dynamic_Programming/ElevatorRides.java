package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class ElevatorRides {

    static class Pair{
        long rides, occ;
        Pair(long rides, long occ){
           this.rides = rides;
           this.occ = occ;
        }
     }
  
    static Pair[] dp;
  
    public static Pair f(int mask, int n, long limit, long[] weights){
        if(mask == 0) return new Pair(1,0);
        if(dp[mask].rides != (int) 1e9) return dp[mask];
  
        long mr = (int) 1e9, mo = -1;
        for(int i = 0; i < n; i++){
            if((mask & (1 << i)) != 0){
                Pair p = f(mask^(1<<i), n, limit, weights); // rides and occ from prev state

                long cr = p.rides; // curr rides
                long co = p.occ; // curr occupancy

                if(co + weights[i] > limit){ // if we can't put current person (i'th person) in the same ride as putting him will overload 
                    cr++;                    // then we increment current ride by one i.e we start a new ride
                    co = weights[i];         // and save curr occ as the weight of the i'th person
                } else {                     // else means we can put the i'th person in the same ride
                    co += weights[i];        // in that case the rides does not increase but the occ increses by the weight of the i'th person
                }

                if(cr < mr || (cr==mr && co < mo)){  // if we get less rides and if rides are same and we get less occ in the curr ride
                    mr = cr;                         // then we update minrides and minoccupancy
                    mo = co;
                }
            }
        }
  
        return dp[mask] = new Pair(mr, mo);
    }
  
    public static long elevatorRides(int n, long limit, long[] weights){
        dp = new Pair[1<<n];
        for(int i = 0; i < (1<<n); i++){
            dp[i] = new Pair((int)1e9, -1);
        }
        return f((1<<n)-1, n, limit, weights).rides;
    }

    // tabulated elevatorRides
    public static long tabulatedElevatorRides(int n, long limit, long[] weights){
        Pair[] dp = new Pair[1<<n];
        dp[0] = new Pair(1, 0);
  
        for(int mask = 1; mask < (1<<n); mask++){
           long mr = (int) 1e9, mo = (int) 1e9;
           for(int bit = 0; bit < n; bit++){
              if((mask & (1<<bit)) != 0){
                 Pair p = dp[mask ^ (1<<bit)];
                 long cr = p.rides;
                 long co = p.occ;
  
                 if(co + weights[bit] > limit){
                    cr += 1;
                    co = weights[bit];
                 } else co += weights[bit];
  
                 if(cr < mr || (cr == mr && co < mo)){
                    mr = cr;
                    mo = co;
                 }
  
              }
           }
           dp[mask] = new Pair(mr, mo);
        }
  
        return dp[(1<<n)-1].rides;
  
     }
      
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  
        String[] f = br.readLine().split(" ");
        int n = Integer.parseInt(f[0]);
        long limit = Long.parseLong(f[1]);
  
        String[] s = br.readLine().split(" ");
        long[] weights = new long[n];
        for(int i = 0; i < n; i++){
           weights[i] = Integer.parseInt(s[i]);
        }
  
        bw.write(Long.toString(elevatorRides(n, limit, weights)));
        bw.write("\n");
        bw.write(Long.toString(tabulatedElevatorRides(n, limit, weights)));

  
        bw.flush();
        br.close();
        bw.close();
   
    }
}
