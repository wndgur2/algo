import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		// O(NlogN) 접근 트리셋 사용.. > 수가 크다고 dp값이 크진 않다. 순서가 고려되어야 하기 때문이다.
		// dp값이 크면 장땡이다. 하지만 지금 수보다 작아야 한다... 이걸 DP..? 이중 DP..?
		// 그러니까 10 이하의 수들의 dp 최댓값은 dp[10]에 들어있다..?
		// 근데 수의 범위가 20억이다. 그러니까 이걸 하려면 해시맵 같은걸로 해야한다.
		// 근데 나보다 작은 최댓값을 찾아야 하니, 트리셋으로 ./?
		// 각 번호에 length 최댓값 저장.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] numbers = new int[n];
		int[] from = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			from[i] = i;
		}
		
		TreeSet<int[]> dp2 = new TreeSet<>((a, b)-> Integer.compare(a[0], b[0])); // [i, j] => i 이하의 최대 dp값. 오름차순
		dp2.add(new int[] {numbers[0], 1, 0}); // 중복데이터처리 (의심)
		
		int maxCnt = 1, maxIdx=0;
		for (int i = 1; i < n; i++) {
			int[] maxCount = dp2.lower(new int[] {numbers[i], 0, 0});
			if(maxCount != null) {
				dp2.add(new int[] {numbers[i], maxCount[1]+1, i}); // 이미 numbers[i]가 있다면 덮어쓰기
				from[i] = maxCount[2];// idx로 바꾸기.
				
				// 얘보다 크고 dp가 같거나 작은 애들을 삭제
				int[] cur;
				while(
						(cur = dp2.higher(new int[] {numbers[i], 0, 0})) != null
						&& cur[1] <= maxCount[1]+1
					)
					dp2.remove(cur);
				
				if(maxCount[1]+1 > maxCnt) {
					maxCnt = maxCount[1]+1;
					maxIdx = i;
				}
			} else {
				dp2.add(new int[] {numbers[i], 1, i});
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(maxCnt).append('\n');
		int idx = maxIdx;
		Stack<Integer> stack = new Stack<>();
		stack.add(numbers[idx]);
		while(from[idx]!=idx) {
			idx = from[idx];
			stack.add(numbers[idx]);
		}
		while(!stack.isEmpty()) sb.append(stack.pop()).append(' ');
		System.out.println(sb.toString().trim());
	}
}

/*
10
1 4 2 3 3 5 2 1 3 3
*/