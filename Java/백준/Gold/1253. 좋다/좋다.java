import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		int l, r, goal, sum;
		int res = 0;
		for(int i=0; i<N; i++) {
			goal = numbers[i];
			l = 0;
			r = N-1;
			while(l<r) {
                sum = numbers[l] + numbers[r];
				if(l==i) {
                    l++;
                    continue;
				}
				else if(r==i) {
                    r--;
                    continue;
				}
				if(sum > goal) {
                    r--;
				} else if(sum==goal) {
//					System.out.println("GOAL: " + goal);
//					System.out.println("L: " + numbers[l] + " R: " + numbers[r]);
					res++;
					break;
				} else {
                    l++;
				}
			}
		}
		System.out.println(res);
	}
}
