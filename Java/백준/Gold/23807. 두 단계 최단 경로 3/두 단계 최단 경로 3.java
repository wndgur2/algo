import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge{
		int to;
		long weight;
		public Edge(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	static class Vertex implements Comparable<Vertex>{
		int idx;
		long weight;
		public Vertex(int idx, long weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(weight, o.weight);
		}
	}
	
	static int P, X, Z;
	static int[] ps;
	static boolean[][] visited;
	static long[][] distances;
	static ArrayList<Edge>[] edges;
	static long res;

	public static void main(String[] args) throws IOException{
		// 다익스트라 x 101 + 100P3
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			long weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Edge(to, weight));
			edges[to].add(new Edge(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken())-1;
		Z = Integer.parseInt(st.nextToken())-1;
		
		P = Integer.parseInt(br.readLine());
		ps = new int[P];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < P; i++)
			ps[i] = Integer.parseInt(st.nextToken())-1;
		
		visited = new boolean[P][N];
		distances = new long[P][N];
		for (int i = 0; i < P; i++) 
			Arrays.fill(distances[i], Long.MAX_VALUE);
		
		for (int i = 0; i < P; i++) {
			dijkstra(i);
		}
		
		res = Long.MAX_VALUE;
		permutation(0, new int[3], new boolean[P]);
		System.out.println(res==Long.MAX_VALUE?-1:res);
	}
	
	static void dijkstra(int from) {
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		q.add(new Vertex(ps[from], 0));
		distances[from][ps[from]] = 0;
		while(!q.isEmpty()) {
			Vertex cur = q.poll();
			if(visited[from][cur.idx]) continue;
			visited[from][cur.idx] = true;
			for(Edge next: edges[cur.idx]) {
				if(visited[from][next.to] || distances[from][next.to] <= cur.weight + next.weight) continue;
				distances[from][next.to] = cur.weight + next.weight;
				q.add(new Vertex(next.to, distances[from][next.to]));
			}
		}
	}
	
	static void permutation(int depth, int[] path, boolean[] visited) {
		if(depth == 3) {
			checkCost(path);
			return;
		}
		
		for (int i = 0; i < P; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			path[depth] = i;
			permutation(depth+1, path, visited);
			visited[i] = false;
		}
	}
	
	static void checkCost(int[] path) {
		// calc cost X->path[0]->path[1]->path[2]->Z);
		long d = distances[path[0]][X] + distances[path[0]][ps[path[1]]] + distances[path[1]][ps[path[2]]] + distances[path[2]][Z];
		if(distances[path[0]][X] == Long.MAX_VALUE || distances[path[0]][ps[path[1]]] == Long.MAX_VALUE || distances[path[1]][ps[path[2]]] == Long.MAX_VALUE || distances[path[2]][Z] == Long.MAX_VALUE) return;
		if(res > d) {
//			System.out.println(Arrays.toString(path));
//			System.out.println(distances[path[0]][X] + " " + distances[path[0]][ps[path[1]]] + " " + distances[path[1]][ps[path[2]]] + " " + distances[path[2]][Z]);
			res = d;
		}
	}
}
