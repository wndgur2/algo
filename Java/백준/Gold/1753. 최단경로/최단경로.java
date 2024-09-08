import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++) edges[i] = new ArrayList<>();
		
		int[] distance = new int[N];
		for(int i=0; i<N; i++) 
			distance[i] = Integer.MAX_VALUE;
		
		int S = Integer.parseInt(br.readLine())-1;
		distance[S] = 0;
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new int[] {to, weight});
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));
		q.add(new int[] {S, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int[] edge: edges[cur[0]]) {
				if(distance[edge[0]] <= edge[1] + distance[cur[0]]) continue;
				distance[edge[0]] = edge[1] + distance[cur[0]];
				q.add(new int[] {edge[0], distance[edge[0]]});
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.println(distance[i]==Integer.MAX_VALUE?"INF":distance[i]);
		}
	}

}
