import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		long[][][] dp = new long[N][N][3];
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0:ㅡ		1:\		2:|
		
		for (int i = 1; i < N; i++) {
			if(map[0][i]==1) break;
			dp[0][i][0] = 1;
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if(map[i][j]==1) continue;
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
				
				// 대각선
				if(map[i-1][j]==1 || map[i][j-1]==1) continue;
				dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}

}
