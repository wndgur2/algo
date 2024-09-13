import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// 병합정렬처럼 작동하기
		// 3개를 봐서 1,2를 합칠지 2,3을 합칠지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] matrix = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			matrix[i][0] = r;
			matrix[i][1] = c;
		}
		int[][] dp = new int[n][n]; // i~j를 합쳤을 때 최소 연산 횟수 
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) dp[i][j] = Integer.MAX_VALUE;
		
		for(int i=0; i<n; i++) dp[i][i] = 0;

		for(int len=1; len<n; len++) {
			for(int l=0; l<n-len; l++) {
				int r= l + len;
				for(int m= l; m<r; m++) {
					dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m+1][r] + matrix[l][0]*matrix[m][1]*matrix[r][1]);
				}
			}
		}
		System.out.println(dp[0][n-1]);
	}
}
