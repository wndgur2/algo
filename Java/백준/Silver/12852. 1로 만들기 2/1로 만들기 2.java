import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		int[] dp = new int[N+1];
		
		int[] from = new int[N+1];
		from[N] = N+1;
		for (int i = N-1; i >= 1; i--) {
			dp[i] = dp[i+1]+1;
			from[i] = i+1;
			if(i*2<=N && dp[i] > dp[i*2]+1) {
				dp[i] = dp[i*2]+1;
				from[i] = i*2;
			}
			if(i*3<=N && dp[i] > dp[i*3]+1) {
				dp[i] = dp[i*3]+1;
				from[i] = i*3;
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		
		int idx = 1;
		while(idx<=N) {
			stack.add(idx);
			idx = from[idx];
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(dp[1]).append('\n');
		while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
		System.out.println(sb.toString().trim());
	}

}
