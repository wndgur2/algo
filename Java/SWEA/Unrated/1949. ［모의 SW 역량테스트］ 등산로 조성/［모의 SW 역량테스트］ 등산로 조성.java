import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 완탐

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, res, maxH;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			maxH=0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > maxH) maxH = map[i][j];
				}
			}
			
			// 지형 하나를 깎고 BFS
			res=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						map[i][j] -= k;
						seek();
						map[i][j] += k;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void seek() {
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) 
				if(map[i][j]==maxH) bfs(i,j);
	}
	
	public static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
	
	public static void bfs(int y, int x) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {y, x, 1}); // y, x, length
		int maxL = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2]>maxL) maxL = cur[2];
			int h = map[cur[0]][cur[1]];
			for(int[] dir:dirs) {
				int ny = cur[0] + dir[0];
				int nx = cur[1] + dir[1];
				
				if(ny<0||nx<0||ny>=N||nx>=N) continue;
				if(map[ny][nx] >= h) continue;
				
				q.add(new int[] {ny, nx, cur[2]+1});
			}
		}
		if(res < maxL) res = maxL;
	}
}
