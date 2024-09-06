import java.io.*;
import java.util.*;
public class Solution {
	static class Customer{
		int idx, eta, a, b;
		Customer(int idx, int eta){
			this.idx = idx;
			this.eta = eta;
		}
	}
	static int N, M, K, A, B;
	static int[] durationA, durationB, counterA, counterB;
	static PriorityQueue<Customer> customersPQ, qa;
	static ArrayDeque<Customer> qb;
	static Customer[] customerInA, customerInB, customers;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 접수창구 수
			M = Integer.parseInt(st.nextToken()); // 정비소 수
			K = Integer.parseInt(st.nextToken()); // 고객 수
			A = Integer.parseInt(st.nextToken())-1; // 접수창구 번호
			B = Integer.parseInt(st.nextToken())-1; // 정비소 번호
			
			durationA = new int[N];
			durationB = new int[M];
			customerInA = new Customer[N];
			customerInB = new Customer[M];
			customers = new Customer[K];
			
			// 접수 소요시간
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				durationA[i] = Integer.parseInt(st.nextToken());
			
			// 정비 소요시간
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				durationB[i] = Integer.parseInt(st.nextToken());
			
			// 고객 도착시간
			customersPQ = new PriorityQueue<>((a, b)->Integer.compare(a.eta, b.eta));
			qa = new PriorityQueue<>((a, b)->Integer.compare(a.idx, b.idx));
			// ① 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선 접수한다.
			
			qb = new ArrayDeque<>();
			counterA = new int[N];
			counterB = new int[M];
			Arrays.fill(counterA, -1);
			Arrays.fill(counterB, -1);
			
			st = new StringTokenizer(br.readLine());
			int s = 0;
			for (int i = 0; i < K; i++) {
				Customer c = new Customer(i, Integer.parseInt(st.nextToken()));
				customersPQ.add(c);
				customers[s++] = c;
			}
			
			int t = 0, count=0;
			while(count < K) {
//				System.out.println("TIME: " + t);
				while(!customersPQ.isEmpty() && customersPQ.peek().eta == t) {
					Customer c = customersPQ.poll();
					qa.add(c);
				}
				
				// A에서 고객 빼고 큐 B에 넣기 
				for (int i = 0; i < N; i++) {
					if(t == counterA[i]) { // 끝나는 시간이 되었다면
						// i번째 고객을 qb에
						qb.add(customerInA[i]);
						customerInA[i] = null;
					}
				}
				
				// 빈 접수 창구가 있는 경우 빈 접수 창구에 가서 고장을 접수한다.
				int emptyCounterIndex=-1;
				while(!qa.isEmpty() && (emptyCounterIndex=getEmpty(customerInA))!=-1) {
					Customer c = qa.poll();
					counterA[emptyCounterIndex] = t + durationA[emptyCounterIndex]; // 끝나는 시간 기록
					customerInA[emptyCounterIndex] = c; // 창구에 고객 넣기
					c.a = emptyCounterIndex; // 들린 접수창구 기록
				}
				
				// 정비를 마친 고객 빼기
				for (int i = 0; i < M; i++) {
					if(t == counterB[i]) { // 끝나는 시간이 되었다면
						customerInB[i] = null;
						count++;
					}
				}
				
				// 빈 정비 창구가 있는 경우 빈 정비 창구에 가서 차량을 정비 받는다.
				emptyCounterIndex=-1;
				while(!qb.isEmpty() && (emptyCounterIndex=getEmpty(customerInB))!=-1) {
					Customer c = qb.poll();
					counterB[emptyCounterIndex] = t + durationB[emptyCounterIndex]; // 끝나는 시간 기록
					customerInB[emptyCounterIndex] = c; // 창구에 고객 넣기
					c.b = emptyCounterIndex; // 들린 접수창구 기록
				}
				t++;
			}
			
			int res = 0;
			for (int i = 0; i < K; i++) if(customers[i].a == A && customers[i].b == B) res += customers[i].idx+1;
			sb.append("#").append(tc).append(" ").append(res==0? -1:res).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static int getEmpty(Customer[] counter) {
		// ② 빈 창구가 여러 곳인 경우 접수 창구번호가 작은 곳으로 간다.
		for (int i = 0; i < counter.length; i++)
			if(counter[i] == null) return i;
		return -1;
	}
}
