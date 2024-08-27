// 2015 수들의 합
// 이중혁

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long K = Integer.parseInt(st.nextToken());
		long[] nums = new long[N+1];
		HashMap<Long, Integer> sumCnt = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		
		nums[0] = 0;
		for (int i = 1; i < N+1; i++)
			nums[i] = Long.parseLong(st.nextToken()) + nums[i-1];
		
		long res = 0;
		
		// 누적합과 K의 차이를 매울 수 있는게 sumCnt에 있는지?
		// 누적합을 누적합에서 빼는데..
		sumCnt.put(0l,  1);
		for(int i=1; i<N+1; i++) {
			long dif = nums[i] - K;
			res+=sumCnt.getOrDefault(dif,0);
			
			// !!! 지금까지 거쳐온 것만 수를 센다... idx i 미만을 빼는 부분이기 때문에
			sumCnt.put(nums[i], sumCnt.getOrDefault(nums[i], 0)+1);
		}
		System.out.println(res);
	}
}
