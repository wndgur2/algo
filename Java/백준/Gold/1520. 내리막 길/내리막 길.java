/*
 * 
 * 경로의 개수
 * DP 혹은 DFS 완전탐색
 * 
 * 세로 M 가로 N
 * 
 * DP
 * DP[i][j] : (i, j)에서 (M-1, N-1)까지 갈 수 있는 경로의 수
 * 초깃값:
 * DP[M-1][N-1] = 1
 * 

4 5
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10

> 3

 * 
 */

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] dp;
    static int H, W;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        dp = new int[H][W];

        for(int i=0; i<H; i++) Arrays.fill(dp[i], -1);
        dp[H-1][W-1] = 1;

        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        
        System.out.println(dp[0][0]);
    }

    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static int dfs(int y, int x){
        if(dp[y][x]!=-1) return dp[y][x];

        int paths = 0;

        for(int[] dir: dirs){
            int ny = y + dir[0];
            int nx = x + dir[1];

            if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
            if(map[ny][nx]>=map[y][x]) continue;
            paths += dfs(ny, nx);
        }
        dp[y][x] = paths;
        return paths;
    }
}
