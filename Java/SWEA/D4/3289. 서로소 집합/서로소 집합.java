import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, cmd, a, b;
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n];
			for (int i = 0; i < n; i++) parents[i] = i;
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				cmd = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				if(cmd == 0) union(a, b);
				else sb.append(find(a, b));
			}
			
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void union(int a, int b) {
		int r1 = findSet(a);
		int r2 = findSet(b);
		if(r1==r2) return;
		parents[r2] = r1;
	}
	
	public static int find(int a, int b) {
		int r1 = findSet(a);
		int r2 = findSet(b);
		return r1==r2?1:0;
	}
	
	public static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}

}
