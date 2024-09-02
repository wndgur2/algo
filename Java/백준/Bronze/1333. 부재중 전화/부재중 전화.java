import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int answer;
		int call=0;
		while(call < N*(L+5)) {
			call += D;
		}
		answer = call;
		call = 0;
		for (int i = 0; i < N; i++) {
			while(call < i*(L+5)+L)
				call += D;
			if(call < (i+1) * (L+5)){
				answer = call;
				break;
			}
		}
		System.out.println(answer);
	}
}
