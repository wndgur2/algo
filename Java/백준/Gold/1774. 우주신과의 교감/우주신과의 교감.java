/*
 * ! 이미 연결된 간선이 하나의 그래프가 아닐 수 있음.
 * 크루스칼 문제
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static class Edge implements Comparable<Edge>{
		int from, to;
		double cost;
		
		public Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(cost, o.cost);
		}
	}
	
	public static double getDistance(int y1, int x1, int y2, int x2) {
		return Math.sqrt(Math.pow(y1-y2, 2) + Math.pow(x1-x2, 2));
	}
	
	static int N;
	
	public static int[] parents;
	
	public static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return false;
		parents[ra] = rb;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] nodes = new int[N][2];
		ArrayList<Edge> edges = new ArrayList<>();
		
		make();
		
		int y, x, from, to;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			nodes[i][0] = y;
			nodes[i][1] = x;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken())-1;
			to = Integer.parseInt(st.nextToken())-1;
			union(from, to);
		}
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				edges.add(new Edge(i, j, getDistance(nodes[i][0], nodes[i][1], nodes[j][0], nodes[j][1])));
			}
		}
		Collections.sort(edges);
		
		double cost=0;
		int cnt=0;
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			if(union(edge.from, edge.to)) {
				cost += edge.cost;
				if(++cnt==N-1) break; //...
			}
		}
		System.out.println(String.format("%.2f", cost)); // 반올림 됨!
	}
}
