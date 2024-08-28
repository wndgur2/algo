import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Gem {
	int weight, value;
	
	Gem(int weight, int value){
		this.weight = weight;
		this.value = value;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		// 무게 오름차순 보석
		PriorityQueue<Gem> gemByWeight = new PriorityQueue<>((g1, g2)->g1.weight-g2.weight);
		
		// 가치 내림차순 보석 우선순위큐
		PriorityQueue<Gem> gemByValue = new PriorityQueue<>((g1, g2)-> {
			if(g2.value==g1.value) return g2.weight-g1.weight;
			return g2.value-g1.value;
		});
		
		// 무게 오름차순 가방
		PriorityQueue<Integer> bags = new PriorityQueue<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			Gem gem = new Gem(w, v);
			gemByWeight.add(gem);
		}
		
		for (int i = 0; i < K; i++) {
			int bag = Integer.parseInt(br.readLine());
			bags.add(bag);
		}

		long res = 0;
		while(!bags.isEmpty()) {
			// 가장 작은 가방 뽑기
			int bag = bags.poll();
			
			// 가방에 넣을 수 있는 보석들 뽑기
			while(!gemByWeight.isEmpty() && gemByWeight.peek().weight <= bag)
				gemByValue.add(gemByWeight.poll());
			
			// 넣을 수 있는 보석이 없음
			if(!gemByValue.isEmpty())
				// 가장 비싼 보석 넣기
				res += (long) gemByValue.poll().value;
		}
		System.out.println(res);
	}
}
