import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Coord implements Comparable<Coord>{
		int loc, idx;
		
		public Coord(int loc, int idx) {
			super();
			this.loc = loc;
			this.idx = idx;
		}

		@Override
		public int compareTo(Coord o) {
			if(o.loc == loc) Integer.compare(idx, o.idx);
			return Integer.compare(o.loc, loc);
		}
		
		@Override
		public String toString() {
			return "(" + this.loc + " = " + this.idx + ")";
		}
	}
	
	static void make() {
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra==rb) return false;
		parents[ra] = rb;
		return true;
	}
	
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int cost, from, to;

		public Edge(int cost, int from, int to) {
			super();
			this.cost = cost;
			this.from = from;
			this.to = to;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws IOException{
		// x, y, z 중 가장 가까운 행성 찾아가기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		parents = new int[n];
		int[][] planets = new int[n][3];
		
		PriorityQueue<Coord> xs = new PriorityQueue<>();		
		PriorityQueue<Coord> ys = new PriorityQueue<>();		
		PriorityQueue<Coord> zs = new PriorityQueue<>();
		
		PriorityQueue<Coord>[] coordQs = new PriorityQueue[] {xs, ys, zs};
		
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			xs.add(new Coord(x, i));
			ys.add(new Coord(y, i));
			zs.add(new Coord(z, i));
			
			planets[i] = new int[] {x, y, z};
		}
		
		Coord x = xs.poll();
		Coord y = ys.poll();
		Coord z = zs.poll();
		
		Coord[] coords = new Coord[] {x, y, z};
		
		make();
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				edges.add(new Edge(coords[j].loc - coordQs[j].peek().loc, coords[j].idx, coordQs[j].peek().idx));
				coords[j] = coordQs[j].poll();
			}
		}
		
		int cnt = 1;
		long res = 0;
		while(cnt < n) {
			Edge cur = edges.poll();
			if(union(cur.from, cur.to)) {
				cnt++;
				res += (long)cur.cost;
			}
		}
		
		System.out.println(res);
	}
}

/*

3
30 10 -10
1 2 3
-10 -10 -10
 * 
*/