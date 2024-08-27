import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Prob implements Comparable<Prob>{
	int number, level;
	Prob(int number, int level){
		this.number = number;
		this.level = level;
	}
	
	public String toString() {
		return "" + number;
	}
	
	public int compareTo(Prob p) {
		if(level==p.level) return p.number-number;
		else return p.level - level;
	}
	
	@Override
	public boolean equals(Object p) {
		return number==((Prob) p).number;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static TreeSet<Prob> probs;
	static HashMap<Integer, Prob> numToProb;
	
	public static void main(String[] args) throws IOException{
		probs = new TreeSet<>();
		numToProb = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		int number;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			number = Integer.parseInt(st.nextToken());
			Prob prob = new Prob(number, Integer.parseInt(st.nextToken()));
			probs.add(prob);
			numToProb.put(number, prob);
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch(cmd) {
			case "add":
				number = Integer.parseInt(st.nextToken());
				Prob prob = new Prob(number, Integer.parseInt(st.nextToken()));
				probs.add(prob);
				numToProb.put(number, prob);
				break;
			case "recommend":
				int x = Integer.parseInt(st.nextToken());
				if(x==1) sb.append(probs.first().toString());
				else sb.append(probs.last().toString());
				sb.append('\n');
				break;
			case "solved":
				number = Integer.parseInt(st.nextToken());
				probs.remove(numToProb.get(number));
				break;
			}
		}
		
		System.out.println(sb.toString());
	}

}
