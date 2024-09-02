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
		permutation(0);
		
		System.out.println(res);
	}
	
	public static void permutation(int depth) {
		if(depth == N-1) {
			// numbers 순서 + numbers[0]으로 방문 가능한지
			int w, temp=0;
			for (int i = 0; i < N-1; i++) {
				w = edges[numbers[i]][numbers[i+1]];
				if(w==0) return;
				temp+=w;
			}
			w = edges[numbers[N-1]][numbers[0]];
			if(w==0) return;
			else temp+=w;
			
			// 가능하다면 비용 비교
			if(res > temp) res = temp;
			return;
		}
		
		for (int i = depth; i < N; i++) {
			swap(i, depth);
			permutation(depth+1);
			swap(i, depth);
		}
	}
	
	public static void swap(int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}
