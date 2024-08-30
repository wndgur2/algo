import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

7 // N
8 // E
5 1 2
5 2 2
7 5 2
6 5 2
6 3 3
6 4 4
7 6 3
7 4 5

 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Edge{
		int to, cost;
		Edge(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int[] inDegree = new int[N];
		int[] outDegree = new int[N];
		ArrayList<Edge>[] edges = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		int to, from, cost;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			to = Integer.parseInt(st.nextToken())-1;
			from = Integer.parseInt(st.nextToken())-1;
			cost = Integer.parseInt(st.nextToken());
			inDegree[to]++;
			outDegree[from]++;
			edges[to].add(new Edge(from, cost)); // 역방향 
		}
		
		boolean[] isBase = new boolean[N];
		
		// 기본 부품 찾기
		for (int i = 0; i < N; i++) {
			if(inDegree[i]==0) isBase[i] = true;
		}
		
		// 기본 부품 누적
		int[] amounts = new int[N];
		amounts[N-1] = 1;
		
		// 거꾸로 가면서, 부품 수 누적
		
		ArrayDeque<Edge> q = new ArrayDeque<>();
		q.add(new Edge(N-1, 0));
		
		while(!q.isEmpty()) {
			Edge cur = q.pollFirst();
			for(Edge next: edges[cur.to]) {
				amounts[next.to] += next.cost * amounts[cur.to];
				if(--outDegree[next.to]==0) q.add(next);
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			if(!isBase[i]) continue;
			sb.append(i+1).append(' ').append(amounts[i]).append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
