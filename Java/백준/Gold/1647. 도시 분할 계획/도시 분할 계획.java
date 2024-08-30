/*
 * 최소신장트리를 구하고, 가중치가 가장 큰 간선을 제거한다.
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, E;
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(cost, o.cost);
		}
	}
	
	static int[] parents;
	
	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra==rb) return false;
		parents[ra] = rb;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[E];
		int from, to, cost;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken())-1;
			to = Integer.parseInt(st.nextToken())-1;
			cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, cost);
		}
		
		int res = 0, maxCost = 0, cnt = 0;
		make();
		
		Arrays.sort(edges);
		for (int i = 0; i < E; i++) {
			Edge edge = edges[i];
			if(union(edge.from, edge.to)) {
				res += edge.cost;
				if(edge.cost > maxCost) maxCost = edge.cost;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(res - maxCost);
	}

}
