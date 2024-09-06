import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, res;
	static Node[] nodes;
	static boolean[] visited;
	
	static class Node{
		ArrayList<Node> next = new ArrayList<>();
		ArrayList<Node> prev = new ArrayList<>();
		
		int idx;
		
		Node(int idx){
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			nodes = new Node[N];
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				nodes[i] = new Node(i);
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				nodes[from].next.add(nodes[to]);
				nodes[to].prev.add(nodes[from]);
			}
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			
			res = 0;
			// calc sizes
			for (int i = 0; i < N; i++) {
				int size = 0;
				
//				System.out.print(i + ": next: ");
				
				Arrays.fill(visited, false);
				// next size
				q.add(i);
				while(!q.isEmpty()) {
					int cur = q.poll();
					size++;
					for(Node next: nodes[cur].next) {
						if(visited[next.idx]) continue;
						visited[next.idx] = true;
						q.add(next.idx);
					}
				}
//				System.out.print(size + ", next + prev : ");
				
				Arrays.fill(visited, false);
				// prev size
				q.add(i);
				while(!q.isEmpty()) {
					int cur = q.poll();
					size++;
					for(Node prev: nodes[cur].prev) {
						if(visited[prev.idx]) continue;
						visited[prev.idx] = true;
						q.add(prev.idx);
					}
				}
//				System.out.println(size);
				if(size == N+1) res ++;
			}
			

			System.out.println("#" + tc + " " + res);
		}
	}

}
