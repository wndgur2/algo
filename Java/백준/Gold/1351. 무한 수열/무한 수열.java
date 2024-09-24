import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static long P, Q;
	static HashMap<Long, Long> valuePool = new HashMap<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long i = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		System.out.println(getRes(i));
	}
	
	public static long getRes(long n) {
		if(n<=0) return 1;
		if(!valuePool.containsKey(n)) valuePool.put(n, getRes((long)(n/P)) + getRes((long)(n/Q)));
		return valuePool.get(n);
		
	}
}
