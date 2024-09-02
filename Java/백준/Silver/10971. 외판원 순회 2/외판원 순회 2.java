import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 실행시간 76ms
 * 메모리 13204kb
 * 
4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0
 */

public class Main {
	static int N, res = 10000000;
	static int[] numbers;
	static int[][] edges;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringTokenizer st;
		// 간선 입력 받기
		N = Integer.parseInt(br.readLine());
		edges = new int[N][N];
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = i;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				edges[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 순열
		numbers[0] = 0;
		permutation(1, 0);
		
		System.out.println(res);
	}
	
	public static void permutation(int depth, int cost) {
		if(cost >= res) return;
		if(depth == N-1) {
			int w = edges[numbers[N-2]][numbers[N-1]];
			if(w==0) return;
			if(edges[numbers[N-1]][0]==0) return;
			cost += w + edges[numbers[N-1]][numbers[0]];
			
			// 가능하다면 비용 비교
			if(res > cost) res = cost;
			return;
		}
		
		for (int i = depth; i < N; i++) {
			if(edges[numbers[depth-1]][numbers[i]]==0) continue;
			// depth=>i 연결
			swap(i, depth);
			permutation(depth+1, cost + edges[numbers[depth-1]][numbers[depth]]);
			swap(i, depth);
		}
	}
	
	public static void swap(int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}
