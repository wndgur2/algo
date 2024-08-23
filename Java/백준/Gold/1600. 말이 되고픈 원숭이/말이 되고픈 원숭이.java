import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][][] dp;
	static boolean[][][] visited;
	static boolean[][] wall;
	static int K, W, H;
	static int res;
	
	public static void main(String[] args) throws IOException{
		res=40001;
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		dp = new int[H][W][K+1]; // dp[i][j] = (i, j)까지 점프를 k번해서 갈 수 있는 최소 이동 횟수
		visited = new boolean[H][W][K+1]; // visited[i][j] = (i, j)에  점프를 k번해서 방문한 적이 있는지
		wall = new boolean[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				wall[i][j] = st.nextElement().equals("1");
				Arrays.fill(dp[i][j], 40001);
				Arrays.fill(visited[i][j], false);
			}
		}
		dp[0][0][0] = 0;
		visited[0][0][0] = true;
		queue.add(new Node(0, 0, 0, 0));
		bfsVisit();
		System.out.println(res==40001?-1:res);
	}
	
	// 12방
	static int[][] walks = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int[][] jumps = {{2, 1}, {2, -1}, {-2, 1}, {-2,-1}, {-1, 2}, {1, 2}, {-1, -2}, {1, -2}};
	static ArrayDeque<Node> queue = new ArrayDeque<>();
	
	static class Node{
		int y;
		int x;
		int k;
		int depth;
		
		Node(int y, int x, int k, int depth){
			this.y = y;
			this.x = x;
			this.k = k;
			this.depth = depth;
		}
	}
	
	public static void bfsDP() {
		while(!queue.isEmpty()) {
			Node node = queue.pollFirst();
			int y = node.y;
			int x = node.x;
			int depth = node.depth;
			int k = node.k;
			
//			System.out.println("(" + y + " " + x + ")");
			
			if(y==H-1 && x==W-1) {
				if(depth<res) res = depth;
				return;
			}
			int ny, nx;
			
			// 걷기 탐색
			for(int[] walk: walks) {
				ny = y+walk[0];
				nx = x+walk[1];
				
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(wall[ny][nx]) continue;
				
				if(dp[ny][nx][k] < depth+1) continue;
				dp[ny][nx][k] = depth+1;
				
				queue.add(new Node(ny, nx, k, depth+1));
			}
			if(k>=K) continue;
			// 점프 탐색
			for(int[] jump: jumps) {
				ny = y+jump[0];
				nx = x+jump[1];
				
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(wall[ny][nx]) continue;
				
				if(dp[ny][nx][k+1] < depth+1) continue;
				dp[ny][nx][k+1] = depth+1;
				
				queue.add(new Node(ny, nx, k+1, depth+1));
			}
		}
	}
	
	public static void bfsVisit() {
		while(!queue.isEmpty()) {
			Node node = queue.pollFirst();
			int y = node.y;
			int x = node.x;
			int depth = node.depth;
			int k = node.k;
			
			if(y==H-1 && x==W-1) {
				if(depth<res) res = depth;
				return;
			}
			int ny, nx;
			
			// 걷기 탐색
			for(int[] walk: walks) {
				ny = y+walk[0];
				nx = x+walk[1];
				
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(wall[ny][nx]) continue;
				
				if(visited[ny][nx][k]) continue;
				visited[ny][nx][k] = true;
				
				queue.add(new Node(ny, nx, k, depth+1));
			}
			if(k>=K) continue;
			// 점프 탐색
			for(int[] jump: jumps) {
				ny = y+jump[0];
				nx = x+jump[1];
				
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(wall[ny][nx]) continue;

				if(visited[ny][nx][k+1]) continue;
				visited[ny][nx][k+1] = true;
				
				queue.add(new Node(ny, nx, k+1, depth+1));
			}
		}
	}
}
