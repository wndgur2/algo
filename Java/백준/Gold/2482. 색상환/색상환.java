import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][N+1];
		for (int i = 0; i < N+1; i++) {
			dp[i][0] = 1;
			dp[i][1] = i;
		}
		for (int i = 4; i < N+1; i++) {
			for (int j = 2; j < K+1; j++) {
				dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % 1000000003;
			}
//			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println(dp[N][K]);
	}
}
