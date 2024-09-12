import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[n];
		
		for(int i=0; i<n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1]; // i원을 만들 수 있는 최소 동전 갯수
		for(int i=0; i<=k; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		
		dp[0] = 0;
		
		for(int i=1; i<=k; i++) {
			for(int j=0; j<n; j++) {
				if(i >= coins[j] && dp[i-coins[j]]!=Integer.MAX_VALUE && dp[i] > dp[i-coins[j]]+1) {
					dp[i] = dp[i-coins[j]]+1;
				}
			}
		}
		System.out.println(dp[k]==Integer.MAX_VALUE?-1:dp[k]);
	}

}
