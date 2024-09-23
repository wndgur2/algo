/*
10
10 -4 3 1 5 6 -35 12 21 -1
*/

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[2][N+1];
		dp[0][0] = 0; // 지우지 않은 경우
		dp[1][0] = 0; // 하나 지운 경우
		StringTokenizer st = new StringTokenizer(br.readLine());
		int res = -1000;
		
		int number = Integer.parseInt(st.nextToken());
		dp[0][1] = number;
		dp[1][1] = 0;
		
		res = number;
		
		for(int i=2; i<=N; i++) {
			number = Integer.parseInt(st.nextToken());
			
			boolean restarted = false;
			
			// 이어서 추가하기
			dp[0][i] = dp[0][i-1] + number;
			// 새로 시작
			if(dp[0][i] < number) {
				dp[0][i] = number;
				restarted = true;
			}
			
			if(dp[0][i] > res) res = dp[0][i];
			
			// 이어서 추가하기
			dp[1][i] = dp[1][i-1] + number;
			// 지금껄 빼서 대체하기 -> 새로 시작한걸 빼면 개수가 0이 됨 
			if(dp[1][i]<dp[0][i] - number && !restarted) dp[1][i] = dp[0][i] - number;
			
			if(dp[1][i] > res) res = dp[1][i];
		}

//		System.out.println(Arrays.toString(dp[0]));
//		System.out.println(Arrays.toString(dp[1]));
		
		System.out.println(res);
	}

}

