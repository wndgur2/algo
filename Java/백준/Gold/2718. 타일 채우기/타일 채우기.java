import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc<T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[n+1];
			dp[0] = 1;
			dp[1] = 1;
			for(int i=2; i<n+1; i++) {
				for(int j=1; j<=i; j++) {
					if(i-j >= 0)
						dp[i] += dp[i-j]*getMult(j); 
				}
			}
			
			System.out.println(dp[n]);
		}
	}
	
	public static int getMult(int j) {
		if(j==1) return 1;
		else if(j==2) return 4;
		else if(j%2==0) return 3;
		else return 2;
	}
}
