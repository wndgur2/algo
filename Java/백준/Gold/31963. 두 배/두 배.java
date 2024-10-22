import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        // 5,000,000
        // 250000
        for(int i=1; i<N; i++){
            //log1000000 = 20
            while(numbers[i-1]>numbers[i]) {
                numbers[i]*=2;
                res++;
            }
        }

        System.out.println(res);
    }
}
