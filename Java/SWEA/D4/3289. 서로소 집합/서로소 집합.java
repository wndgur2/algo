import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parents = new int[n];
			for (int i = 0; i < n; i++)
				parents[i] = i;
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				if(cmd == 0) union(a, b);
				else if(cmd == 1) sb.append(find(a, b));
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void union(int a, int b) {
		int r1 = find(a);
		int r2 = find(b);
		if(r1==r2) return;
		parents[r2] = r1;
	}
	
	public static int find(int a, int b) {
		int r1 = find(a);
		int r2 = find(b);
		return r1==r2?1:0;
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

}
