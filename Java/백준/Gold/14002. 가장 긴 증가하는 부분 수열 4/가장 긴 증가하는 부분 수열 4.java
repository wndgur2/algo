import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		// O(N^2) 접근
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int maxIdx = 0;
		int[] numbers = new int[n];
		int[] from = new int[n];
		for (int i = 0; i < n; i++) from[i] = i;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if(numbers[i] > numbers[j] && dp[i]<dp[j]+1) {
					dp[i] = dp[j]+1;
					from[i] = j;
					if(dp[maxIdx] < dp[i]) maxIdx = i;
				}
			}
		}
		
//		System.out.println(Arrays.toString(from));
		
		StringBuilder sb = new StringBuilder();
		sb.append(dp[maxIdx]).append('\n');
		
		Stack<Integer> stack = new Stack<>();
		int idx=maxIdx;
		while(true) {
			stack.add(idx);
			if(idx == from[idx]) break;
			idx = from[idx];
		}
		while(!stack.isEmpty()) sb.append(numbers[stack.pop()] + " ");
		System.out.println(sb.toString().trim());
	}
}

/*
10
1 4 2 3 3 5 2 1 3 3
4
1 2 3 5
*/