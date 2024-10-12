import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

5
Ab3bd

2

*/
/**

LCS를 활용하는 문제

입력 문자열과
입력 문자열을 거꾸로 뒤집은 문자열의
LCS를 구하고
그 길이를 제외한 만큼을 추가한다. 

*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		char[] chars = br.readLine().toCharArray();
		char[] reversed = new char[N];
		for (int i = 0; i < N; i++) {
			reversed[i] = chars[N-i-1];
		}
		int[][] LCS = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if(chars[i-1] == reversed[j-1]) {
					LCS[i][j] = LCS[i-1][j-1] + 1;
				} else {
					LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
				}
			}
		}
		System.out.println(N - LCS[N][N]);
	}
}
