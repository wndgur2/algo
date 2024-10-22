import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] Xs = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Xs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(Xs);
        
        int[] Ts = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Ts[i] = Integer.parseInt(st.nextToken());
        }

        // 마지막 집에 감
        int res = 0;
        res += Math.max(Ts[N-1], Xs[N-1]);
        int cur = Xs[N-1];
        for(int i=N-2; i>=0; i--){
            res += Math.max(Ts[i]-res, cur-Xs[i]);
            cur = Xs[i];
        }
        
        // 0으로 돌아감
        res += cur;
        System.out.println(res);
    }
}
