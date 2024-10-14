/*
5 2 4
1
2
3
4
5
2 1 5
1 3 4 6
2 2 5
1 1 3 -2
2 2 5
2 2 5

26
22
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] tree, numbers, deltas;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        numbers = new long[N];
        tree = new long[N*4];
        deltas = new long[N*4];

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }
        
        makeTree(0, N, 1);

        int type, from, to;
        long delta;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            
            type = Integer.parseInt(st.nextToken());
            if(type==1){
                from = Integer.parseInt(st.nextToken())-1;
                to = Integer.parseInt(st.nextToken());
                delta = Long.parseLong(st.nextToken());
                update(from, to, 0, N, 1, delta);
            } else{
                from = Integer.parseInt(st.nextToken())-1;
                to = Integer.parseInt(st.nextToken());
                sb.append(query(from, to, 0, N, 1)).append('\n');
            }
        }
        System.out.println(sb.toString());
    }
    private static long makeTree(int from, int to, int cur) {
        if(from==to-1){
            tree[cur] = numbers[from];
        } else{
            int mid = (from+to+1)/2;
            tree[cur] = makeTree(from, mid, cur*2)+
            makeTree(mid, to, cur*2+1);
        }
        return tree[cur];
    }
    
    // delta, 부모 value 갱신. delta를 소모하지는 않음.
    private static void update(int from, int to, int curFrom, int curTo, int cur, long delta) {
        if(curFrom>=from && curTo<=to){ // 범위에 쏙 드감
            deltas[cur] += delta;
        }
        
        if(curFrom!=curTo-1){
            // 리프가 아니면 delta 물려주기
            deltas[cur*2] += deltas[cur];
            deltas[cur*2+1] += deltas[cur];
        }
        // delta 실현
        tree[cur] += deltas[cur]*(curTo-curFrom);
        // delta 삭제
        deltas[cur] = 0;

        if(curTo<=from || curFrom>=to){ // 범위에서 나감
            return;
        } else if(curFrom>=from && curTo<=to){ // 범위에 쏙 드감
            return;
        } else{ // 범위가 걸침
            int mid = (curFrom+curTo+1)/2;
            update(from, to, curFrom, mid, cur*2, delta);
            update(from, to, mid, curTo, cur*2+1, delta);
        }
        tree[cur] = tree[cur*2] + tree[cur*2+1];
    }

    // 필요한 노드까지 delta를 실현하고 *자식에게 물려줌. delta가 실현된 값을 리턴
    // delta: 현재까지 내려오며 쌓인 delta
    private static long query(int from, int to, int curFrom, int curTo, int cur) {
        if(curFrom!=curTo-1){
            // 리프가 아니면 delta 물려주기
            deltas[cur*2] += deltas[cur];
            deltas[cur*2+1] += deltas[cur];
        }
        // delta 실현
        tree[cur] += deltas[cur]*(curTo-curFrom);
        // delta 삭제
        deltas[cur] = 0;

        if(curTo<=from || curFrom>=to){ // 범위에서 나감
            return 0;
        } else if(curFrom>=from && curTo<=to){ // 범위에 쏙 드감
            return tree[cur];
        } else{ // 범위가 걸침
            int mid = (curFrom+curTo+1)/2;
            return query(from, to, curFrom, mid, cur*2) + query(from, to, mid, curTo, cur*2+1);
        }
    }
}
