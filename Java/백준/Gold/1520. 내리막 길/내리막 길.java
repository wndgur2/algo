import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    static int M, N, ans;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];
        int[][] cnt = new int[M][N];
        boolean[][] visited = new boolean[M][N];
        cnt[0][0] = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<>((a,b) -> Integer.compare(map[b.y][b.x], map[a.y][a.x]));
        pq.offer(new Point(0,0));
        
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || ny >= M || nx < 0 || nx >= N || map[ny][nx] >= map[cur.y][cur.x]) continue;
                cnt[ny][nx] += cnt[cur.y][cur.x];
                if (!visited[ny][nx]) {
                    pq.offer(new Point(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
        
        System.out.println(cnt[M-1][N-1]);
    }

}
