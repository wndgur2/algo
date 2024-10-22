import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        long[] muls = new long[N]; // 2를 곱한 횟수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        long res = 0;
        // 5,000,000
        for(int i=1; i<N; i++){
            long temp = (long)Math.ceil(log2(numbers[i-1]) - log2(numbers[i]));
            muls[i] = Math.max(0, temp + muls[i-1]);
            res+=muls[i];
        }
        System.out.println(res);
    }

    public static double log2(int num){
        // log2num = ?
        // 2^? = num
        return Math.log(num)/Math.log(2);
    }
}
