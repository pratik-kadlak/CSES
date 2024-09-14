package Sorting_And_Searching;

import java.util.*;

public  class RoomAllocation {	

	static class Query {
        int type, time, id;
        Query(int type, int time, int id) {
            this.type = type;
            this.time = time;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        TreeSet<Integer> availableRooms = new TreeSet<>();
        Query[] queries = new Query[2 * N];
        int[] ans = new int[N + 1];

        // Read input and create queries for arrival and departure
        for (int i = 0; i < N; i++) {
            availableRooms.add(i + 1); // Available room numbers
            int a = sc.nextInt(); // Arrival time
            int b = sc.nextInt(); // Departure time
            queries[2 * i] = new Query(0, a, i + 1); // Arrival query
            queries[2 * i + 1] = new Query(1, b, i + 1); // Departure query
        }

        // Sort queries first by time, then by type (arrival before departure if times are equal)
        Arrays.sort(queries, (Query A, Query B) -> {
            if (A.time == B.time) {
                return Integer.compare(A.type, B.type);
            }
            return Integer.compare(A.time, B.time);
        });

        // Allocate and deallocate rooms based on sorted queries
        for (int i = 0; i < 2 * N; i++) {
            if (queries[i].type == 0) { // Arrival
                int assignedRoom = availableRooms.first(); // Smallest available room
                ans[queries[i].id] = assignedRoom;
                availableRooms.remove(assignedRoom); // Mark room as taken
            } else { // Departure
                availableRooms.add(ans[queries[i].id]); // Free the room
            }
        }

        // Find the maximum number of rooms used
        int maxRooms = 0;
        for (int i = 1; i <= N; i++) {
            maxRooms = Math.max(maxRooms, ans[i]);
        }

        // Output the result
        System.out.println(maxRooms); // Total rooms used
        for (int i = 1; i <= N; i++) {
            System.out.print(ans[i] + (i == N ? "\n" : " ")); // Room assignments for each customer
        }

        sc.close();
    }

}

