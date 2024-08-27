import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			// 입력 받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 진출 담을 배열 선언
			boolean[][] edges = new boolean[N][N];
			
			// 진입 차수를 위한 배열 선언
			int[] degrees = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				edges[from][to] = true;
				degrees[to]++;
			}
			
			// 위상 정렬한 결과를 담을 리스트
			int[] topology = new int[N];
			
			// 진입 차수가 0인 노드를 담을 큐 선언
			ArrayDeque<Integer> q = new ArrayDeque<>();
			
			// 1. 진입 차수가 0인 노드를 찾아 큐에 담기
			for (int i = 0; i < N; i++) {
				if(degrees[i]==0) q.add(i);
			}
			
			int size = 0;
			// 2. 큐가 empty가 아닐 때까지
			while(!q.isEmpty()) {
				// 2.1 진입 차수가 0인 노드를 큐에서 꺼내서 결과에 담기
				int idx = q.pollFirst();
				topology[size++] = idx+1;
				
				// 2.2 진입 차수가 0인 노드에서 진출하는 노드를 방문
				for (int i = 0; i < N; i++) {
					if(!edges[idx][i]) continue;
					// 2.2.1 방문한 노드의 진입을 줄여준다.
					// 2.2.2 방문한 노드의 진입이 0이라면 큐에 추가
					if(--degrees[i]==0)
						q.addLast(i);
				}
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < N; i++) {
				System.out.print(topology[i] + " ");
			}
			System.out.println();
		}
	}
}
