import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] edges = new ArrayList[N];
		boolean[] visited = new boolean[N];
		for(int i=0; i<N; i++) edges[i] = new ArrayList<>();
		int[] dXY = new int[N];
		int[] dYZ = new int[N];
		int[] dXZ = new int[N];
		
		for(int i=0; i<N; i++) {
			dXY[i] = Integer.MAX_VALUE;
			dYZ[i] = Integer.MAX_VALUE;
			dXZ[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new int[] {to, weight});
		}

		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken())-1;
		int Y = Integer.parseInt(st.nextToken())-1;
		int Z = Integer.parseInt(st.nextToken())-1;

		dXY[X] = 0;
		dYZ[Y] = 0;
		dXZ[X] = 0;
		
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));
		
		// X -> Y
		q.add(new int[] {X, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(visited[cur[0]]) continue;
			visited[cur[0]] = true;
			if(cur[0]==Y) break;
			for(int[] next: edges[cur[0]]) {
				if(visited[next[0]] || dXY[next[0]] <= cur[1] + next[1]) continue;
				dXY[next[0]] = cur[1] + next[1];
				q.add(new int[] {next[0], dXY[next[0]]});
			}
		}
		
		// Y -> Z
		Arrays.fill(visited, false);
		q.clear();
		q.add(new int[] {Y, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(visited[cur[0]]) continue;
			visited[cur[0]] = true;
			if(cur[0]==Z) break;
			for(int[] next: edges[cur[0]]) {
				if(visited[next[0]] || dYZ[next[0]] <= cur[1] + next[1]) continue;
				dYZ[next[0]] = cur[1] + next[1];
				q.add(new int[] {next[0], dYZ[next[0]]});
			}
		}
		
		int xyz, xz;
		
		if(dXY[Y]==Integer.MAX_VALUE || dYZ[Z]==Integer.MAX_VALUE) xyz = -1;
		else xyz = dXY[Y] + dYZ[Z];
		
		// X -> Z (Y를 제외)
		Arrays.fill(visited, false);
		visited[Y] = true;
		q.clear();
		q.add(new int[] {X, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(visited[cur[0]]) continue;
			visited[cur[0]] = true;
			if(cur[0]==Z) break;
			for(int[] next: edges[cur[0]]) {
				if(visited[next[0]] || dXZ[next[0]] <= cur[1] + next[1]) continue;
				dXZ[next[0]] = cur[1] + next[1];
				q.add(new int[] {next[0], dXZ[next[0]]});
			}
		}
		
		if(dXZ[Z] == Integer.MAX_VALUE) xz = -1;
		else xz = dXZ[Z];
		
		System.out.println(xyz + " " + xz);
	}

}

