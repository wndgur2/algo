import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static boolean[][] wall;
	static int res = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // <= 10000
		C = Integer.parseInt(st.nextToken()); // <= 500
		
		
		// 벽 입력
		wall = new boolean[R][C];
		for (int row = 0; row < R; row++) {
			String str = br.readLine();
			for (int col = 0; col < C; col++) {
				wall[row][col] = str.charAt(col) == 'x';
			}
		}
		
		for (int row = 0; row < R; row++) {
			dfs(row, 0);
		}
		
		System.out.println(res);
	}
	
	public static boolean dfs(int row, int col) {
		wall[row][col] = true;
		if(col==C-1) {
			res++;
			return true;
		}
		if(row>0 && !wall[row-1][col+1] && dfs(row-1, col+1)) return true;
		if(!wall[row][col+1] && dfs(row, col+1)) return true;
		if(row<R-1 && !wall[row+1][col+1] && dfs(row+1, col+1)) return true;
		return false;
	}

}
