import java.io.*;
import java.util.*;
class Solution {
    ArrayList<int[]>[] edges;
    int[] nodes;
    final int MAX_INTENSITY = 10000001;
    final int GATE = 1;
    final int SUMMIT = 2;
    int minIntensity = MAX_INTENSITY;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) throws IOException{
        int[] answer = {0, MAX_INTENSITY};

        nodes = new int[n];
        for(int gate:gates){
            nodes[gate-1] = GATE;
        }
        for(int summit:summits){
            nodes[summit-1] = SUMMIT;
        }
        Arrays.sort(summits);

        edges = new ArrayList[n];
        for(int i=0; i<n; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<paths.length; i++){
            int from = paths[i][0]-1;
            int to = paths[i][1]-1;
            int cost = paths[i][2];
            edges[from].add(new int[]{to, cost});
            edges[to].add(new int[]{from, cost});
        }

        for(int i=0; i<summits.length; i++){
            // 각 봉우리로부터 gate 찾기 dijkstra는 최소 intensity를 보장한다.
            int intensity = Dijkstra(n, summits[i]-1);
            if(intensity < answer[1]) {
                answer[0] = summits[i];
                answer[1] = intensity;
                minIntensity = intensity;
            }
        }
        return answer;
    }

    int Dijkstra(int n, int from){
        int intensity = 0;
        boolean[] visited = new boolean[n];
        visited[from] = true;

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));

        q.add(new int[]{from, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            visited[cur[0]] = true;
            if(cur[1]>intensity){
                intensity = cur[1];
                if(intensity >= minIntensity) return MAX_INTENSITY;
            }
            if(nodes[cur[0]] == GATE) return intensity;
            for(int[] to: edges[cur[0]]){
                if(visited[to[0]]) continue; // visited
                if(nodes[to[0]] == SUMMIT) continue; // 봉우리
                q.add(new int[]{to[0], to[1]});
            }
        }
        System.out.println("WHY IS THIS PRINTING?");
        return MAX_INTENSITY;
    }
}