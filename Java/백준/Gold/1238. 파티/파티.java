import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int idx;
		long weight;
		public Edge(int idx, long weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// X에서 다익스트라. x2의 최댓값.
		// x: 단방향이므로 돌아오는 최솟값 계산해야함 .
		// N M X
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken())-1;
		
		ArrayList<Edge>[] edges = new ArrayList[N];
		
		for (int i = 0; i < N; i++) edges[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Edge(to, weight));
		}
		
		PriorityQueue<Edge> q = new PriorityQueue<>();
		
		long[][] distances = new long[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(distances[i], Long.MAX_VALUE);
		
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			q.clear();
			q.add(new Edge(i, 0));
			while(!q.isEmpty()) {
				Edge cur = q.poll();
				if(visited[cur.idx]) continue;
				visited[cur.idx] = true;
				
				for(Edge next : edges[cur.idx]) {
					if(distances[i][next.idx] <= cur.weight + next.weight) continue;
					distances[i][next.idx] = cur.weight + next.weight;
					q.add(new Edge(next.idx, distances[i][next.idx]));
				}
			}
		}
		
		long res = 0;
		for (int i = 0; i < N; i++) {
			if(i==X) continue;
			// 제일 긴 거
			long d = distances[i][X] + distances[X][i];
			if(d > res) res = d; 
		}
		System.out.println(res);
	}

}
