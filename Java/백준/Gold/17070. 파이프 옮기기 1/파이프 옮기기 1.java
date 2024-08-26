import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int res, N;
	public static boolean[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		res = 0;
		
		map = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().equals("1");
			}
		}
		
		dfs(0,1,0);
		System.out.println(res);
	}
	
	public static int[][] dirs = {{0, 1}, {1, 1}, {1, 0}}; 
	
	public static void dfs(int y, int x, int dir) {
//		System.out.println(y + " " + x);
		if(y==N-1 && x==N-1) {
			res++;
			return;
		}
		
		int ny, nx;
		for (int i = 0; i < 3; i++) {
			if(i==0 && dir==2) continue;
			if(i==2 && dir==0) continue;
			ny = y + dirs[i][0];
			nx = x + dirs[i][1];
//			System.out.println("next: " + ny + " " + nx);
			if(ny<0||nx<0||ny>=N||nx>=N) continue;
			if(map[ny][nx]) continue;
			if(i==1) {
				if(map[ny-1][nx] || map[ny][nx-1]) continue;
			}
			dfs(ny, nx, i);
		}
	}

}
