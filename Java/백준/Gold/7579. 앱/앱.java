import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int costSum=0;

		int[] memories = new int[N];
		int[] costs = new int[N];
		
		// memory
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int m = Integer.parseInt(st.nextToken());
			memories[i] = m;
		}
		
		// cost
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int cost = Integer.parseInt(st.nextToken());
			costs[i] = cost;
			costSum += cost;
		}
		
		int[] dp = new int[costSum+1]; // dp[i] : i비용으로 확보할 수 있는 최대 memory
		
		for(int i=0; i<N; i++) {
			for(int j=costSum; j>=costs[i]; j--) {
				// i번째 앱을 종료하는게 더 크다!
				if(dp[j-costs[i]] + memories[i] > dp[j]) dp[j] = dp[j-costs[i]] + memories[i]; 
			}
//			System.out.println(Arrays.toString(dp));
		}
		
		
		for(int i=0; i<=costSum; i++) {
			if(dp[i]>=M) {
				System.out.print(i);
				break;
			}
		}
	}

}

/**
5 60
30 10 20 35 40
3 6 3 5 6
 */

