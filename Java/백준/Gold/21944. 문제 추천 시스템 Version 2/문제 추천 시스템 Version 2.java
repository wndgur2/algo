import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Prob implements Comparable<Prob>{
	int number, level, type;
	
	Prob(int number, int level, int type){
		this.number = number;
		this.level = level;
		this.type = type;
	}
	
	@Override
	public int compareTo(Prob o) { // 오름차순 
		if(level == o.level) return number-o.number; 
		return level-o.level;
	}
}

public class Main {
	
	static TreeSet<Prob> probs;
	static HashMap<Integer, TreeSet<Prob>> typeToProbs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		probs = new TreeSet<>();
		typeToProbs = new HashMap<>();
		HashMap<Integer, Prob> probsMap = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 번호 난이도 알고리즘
			int number = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());
			Prob prob = new Prob(number, level, type);
			
			probs.add(prob);
			probsMap.put(number, prob);
			
			if(!typeToProbs.containsKey(type)) typeToProbs.put(type, new TreeSet<>());
			typeToProbs.get(type).add(prob);
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		int p, l, g, x;
		Prob prob;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch (cmd) {
			case "recommend": // G x
				g = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				if(x==1) sb.append(typeToProbs.get(g).last().number);
				if(x==-1) sb.append(typeToProbs.get(g).first().number);
				sb.append('\n');
				break;
			case "recommend2": // x
				x = Integer.parseInt(st.nextToken());
				if(x==1) sb.append(probs.last().number);
				if(x==-1) sb.append(probs.first().number);
				sb.append('\n');
				break;
			case "recommend3": // x L
				x = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				if(x==1) prob = probs.ceiling(new Prob(0, l, 0));
				else prob = probs.lower(new Prob(0, l, 0));
				if(prob==null) sb.append(-1);
				else sb.append(prob.number);
				sb.append('\n');
				break;
			case "add": // P L G
				p = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				g = Integer.parseInt(st.nextToken());
				prob = new Prob(p, l, g);
				probs.add(prob);
				probsMap.put(p, prob);
				if(!typeToProbs.containsKey(g)) typeToProbs.put(g, new TreeSet<>());
				typeToProbs.get(g).add(prob);
				break;
			case "solved": // P
				// map과 set에서 prob 제거
				p = Integer.parseInt(st.nextToken());
				prob = probsMap.get(p);
				probs.remove(prob);
				typeToProbs.get(prob.type).remove(prob);
				probsMap.remove(p);
				break;

			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}

}
