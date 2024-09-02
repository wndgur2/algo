import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
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
		permutation(0, 0);
		
		System.out.println(res);
	}
	
	public static void permutation(int depth, int cost) {
		if(depth == N-1) {
			int w = edges[numbers[N-2]][numbers[N-1]];
			if(w==0) return;
			if(edges[numbers[N-1]][numbers[0]]==0) return;
			w += edges[numbers[N-1]][numbers[0]];
			cost+=w;
			
			// 가능하다면 비용 비교
			if(res > cost) res = cost;
			return;
		}
		
		for (int i = depth; i < N; i++) {
			if(depth>0 && edges[numbers[depth-1]][numbers[i]]==0) continue;
			int w = depth==0? 0:cost + edges[numbers[depth-1]][numbers[i]];
			swap(i, depth);
			permutation(depth+1, w);
			swap(i, depth);
		}
	}
	
	public static void swap(int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}
