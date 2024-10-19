import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] tank; // N x N
    static final int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Shark{
        int y, x;
        int size = 2;
        int toGrow = 2;
        boolean isPassingBy; // 같은 크기의 물고기를 지나치고 있음

        Shark(){}

        void init(int y, int x){
            this.y = y;
            this.x = x;
        }

        void goEat(int ny, int nx){
            tank[ny][nx] = 0;
            tank[this.y][this.x] = 0;

            if(--this.toGrow==0)
                this.toGrow = ++this.size;

            this.y = ny;
            this.x = nx;
        }

        boolean isMovable(int ny, int nx){
            if(ny<0||nx<0||ny>=N||nx>=N) return false;
            if(tank[ny][nx]>size) return false;
            return true;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tank = new int[N][N];
        Shark shark = new Shark();
        // int leftFishToEat = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                tank[i][j] = Integer.parseInt(st.nextToken());
                if(tank[i][j] == 9){
                    shark.init(i, j);
                    tank[i][j] = 0;
                } 
                
            }
        }

        /* BFS
            - 먹을 수 있는 물코기 만나면 먹기
            - 답에 움직인 거리 적용
            - BFS 새로 진행
        */

        int res = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->{
            if(a[2]==b[2]){
                if(a[0]==b[0]) return Integer.compare(a[1], b[1]); // x가 작은 곳부터 방문
                return Integer.compare(a[0], b[0]); // y가 작은 곳부터 방문
            }
            return Integer.compare(a[2], b[2]); // 가까운 곳부터 방문
        });
        boolean[][] visited = new boolean[N][N];

        q.add(new int[]{shark.y, shark.x, 0});
        visited[shark.y][shark.x] = true;


        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(tank[cur[0]][cur[1]]<shark.size && tank[cur[0]][cur[1]]!=0){
                // eat
                res += cur[2];

                shark.goEat(cur[0], cur[1]);
                
                q.clear();
                q.add(new int[]{cur[0], cur[1], 0});
                visited = new boolean[N][N];
                visited[cur[0]][cur[1]] = true;

                continue;
            }

            for(int[] dir: dirs){
                int ny = cur[0] + dir[0];
                int nx = cur[1] + dir[1];
                if(!shark.isMovable(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx, cur[2]+1});
            }
        }

        System.out.println(res);
    }
}
