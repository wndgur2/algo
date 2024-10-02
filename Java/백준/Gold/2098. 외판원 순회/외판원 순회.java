import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0

 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dp, costs;
    static int n;
    
    static final int MAX_VALUE = 16000000;
    
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine()); // <= 16
        dp = new int[n][1<<n];
        costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                costs[i][j] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(dfs(0, 1)); // returns dp[0][1]
        // dp[i][visit] : 	visit만큼 방문한 상태에서,
        //					i번 노드로부터 탐색을 시작할 때 가능한 최소 순회 비용 
        
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
    }

    public static int dfs(int cur, int visit) {
        if(visit == (1<<n)-1) {
            if(costs[cur][0]>0) return costs[cur][0];
            else return MAX_VALUE;
        }
        
        if(dp[cur][visit]>0) return dp[cur][visit]; // 갱신한 적이 있음.
        
        dp[cur][visit] = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if((visit & (1<<i))>0 || costs[cur][i]==0) continue;
            dp[cur][visit] = Math.min(dp[cur][visit], costs[cur][i] + dfs(i, visit|(1<<i)));
        }
//        System.out.println("DP: " + dp[cur][visit]);
        return dp[cur][visit];
    }
}