import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // X -> P[], Z -> P[] 각각 구하고, X->P[i] + Z->P[i] 중 최솟값.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<long[]>[] edges = new ArrayList[N];
        long[] distanceFromX = new long[N];
        long[] distanceFromZ = new long[N];
        boolean[] visited = new boolean[N];

        for(int i=0; i<N; i++) {
            edges[i] = new ArrayList<>();
            distanceFromX[i] = Long.MAX_VALUE;
            distanceFromZ[i] = Long.MAX_VALUE;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            edges[from].add(new long[] {to, weight});
            edges[to].add(new long[] {from, weight});
        }

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken())-1;
        int Z = Integer.parseInt(st.nextToken())-1;

        int P = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] ps = new int[P];
        for(int i=0; i<P; i++)
            ps[i] = Integer.parseInt(st.nextToken())-1;

        PriorityQueue<long[]> q = new PriorityQueue<>((a, b)->Long.compare(a[1], b[1]));

        // X ->
        //			visited[Z] = true;
        distanceFromX[X] = 0;
        q.add(new long[] {X, 0});
        while(!q.isEmpty()) {
            long[] cur = q.poll();
            int v = (int) cur[0];
            if(visited[v]) continue;
            visited[v] = true;
            for(long[] next: edges[v]) {
                int nextV = (int)next[0];
                if(visited[nextV]) continue;
                if(distanceFromX[nextV] <= distanceFromX[v] + next[1]) continue;
                distanceFromX[nextV] = distanceFromX[v] + next[1];
                q.add(new long[] {nextV, distanceFromX[nextV]});
            }
        }

        //		System.out.println(Arrays.toString(distanceFromX));

        // Z ->
        q.clear();
        Arrays.fill(visited, false);
        //			visited[X] = true;
        distanceFromZ[Z] = 0;
        q.add(new long[] {Z, 0});
        while(!q.isEmpty()) {
            long[] cur = q.poll();
            int v = (int) cur[0];
            if(visited[v]) continue;
            visited[v] = true;
            for(long[] next: edges[v]) {
                int nextV = (int) next[0];
                if(visited[nextV]) continue;
                if(distanceFromZ[nextV] <= distanceFromZ[v] + next[1]) continue;
                distanceFromZ[nextV] = distanceFromZ[v] + next[1];
                q.add(new long[] {nextV, distanceFromZ[nextV]});
            }
        }
        //		System.out.println(Arrays.toString(distanceFromZ));

        long res = Long.MAX_VALUE;

        for(int p: ps) {
            if(distanceFromX[p] == Long.MAX_VALUE || distanceFromZ[p] == Long.MAX_VALUE) continue;

            long d = distanceFromX[p] + distanceFromZ[p];
            if(d < res) res = d; 
        }

        System.out.println(res==Long.MAX_VALUE?-1:res);
    }

}


