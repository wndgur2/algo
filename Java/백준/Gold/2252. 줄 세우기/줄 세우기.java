import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] topology = new int[N];
		int[] inDegree = new int[N];
		ArrayList<Integer>[] edges = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to= Integer.parseInt(st.nextToken())-1;
			edges[from].add(to);
			inDegree[to]++;
		}
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) 
			if(inDegree[i]==0) q.add(i);
		
		int size = 0;
		while(!q.isEmpty()) {
			int idx = q.pollFirst();
			topology[size++] = idx+1;
			for(int to:edges[idx]) {
				if(--inDegree[to]==0)
					q.add(to);
			}
		}
		
		for (int i = 0; i < N; i++) 
			sb.append(topology[i]).append(" ");
		System.out.println(sb.toString());
	}
}
