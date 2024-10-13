import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

// N M K
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5

17
12

1>>변경 b->c
2>>query b~c
 */

public class Main{
    static int N, M, K;
    static long[] segments, numbers;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new long[N];
        segments = new long[N*4];

        for (int i = 0; i < N; i++)
            numbers[i] = Long.parseLong(br.readLine());

        makeTree();
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if(query==1){ // 수정
                set(0, N, 1, a-1, b);
            } else{ // query
                sb.append(getSum(a-1, (int)b)).append('\n');
            }
        }
        System.out.println(sb.toString());
    }
    
    private static long getSum(int left, int right){
        return get(left, right, 0, N, 1);
    }

    private static long get(int left, int right, int curLeft, int curRight, int cur) {
        // 범위가 겹치지 않는 경우
        if(left >= curRight || right <= curLeft) return 0;
        // 리프인 경우
        if(curLeft +1 == curRight) return segments[cur];

        int leftChild = cur*2;
        int rightChild = cur*2+1;
        int mid = (curLeft + curRight + 1) / 2;

        if(left<=curLeft && right >=curRight){ 
            // left, right가 curLeft, curRight을 포함하는 경우 (inclusive)
            return segments[cur];
        } else{
            return get(left, right, curLeft, mid, leftChild) + get(left, right, mid, curRight, rightChild);
        }
    }

    private static void makeTree() {
        for (int i = 0; i < N; i++) {
            // recursion으로 구현해야
            // -> root에서 호출해야함
            set(0, N, 1, i, numbers[i]);
        }
    }

    private static long set(int l, int r, int cur, int idx, long num) {
        int leftChild = cur*2;
        int rightChild = cur*2+1;
        int mid = (l + r + 1) / 2;
        if(l<=idx && r>idx){ // include
            if(l==r-1){ // leaf node
                segments[cur] = num;
                return num;
            } else{
                segments[cur] = set(l, mid, leftChild, idx, num) + set(mid, r, rightChild, idx, num);
                return segments[cur];
            }
        }  else return segments[cur];
    }
}