import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N][3];
		for (int i = 0; i < N; i++) 
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		
		dp[0] = cost[0];
		for (int i = 1; i < N; i++) 
			for (int j = 0; j < 3; j++) 
				for (int j2 = 0; j2 < 3; j2++) 
					if(j!=j2) dp[i][j] = Math.min(dp[i][j], dp[i-1][j2] + cost[i][j]);
		
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}

}
