import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        // 10만 BFS

        int[] visit = new int[200001];
        Arrays.fill(visit, 100000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, 0});
        visit[a] = 0;
        while(!q.isEmpty()){
            int cur[] = q.pollFirst();

            if(cur[0]>0 && cur[0]<=100000 && visit[cur[0]*2]>cur[1]){
                q.addLast(new int[]{cur[0]*2, cur[1]});
                visit[cur[0]*2] = cur[1];
            }
            if(cur[0]<100000 && visit[cur[0]+1]>cur[1]+1){
                q.addLast(new int[]{cur[0]+1, cur[1]+1});
                visit[cur[0]+1] = cur[1]+1;
            }
            if(cur[0]>0 && visit[cur[0]-1]>cur[1]+1){
                q.addLast(new int[]{cur[0]-1, cur[1]+1});
                visit[cur[0]-1] = cur[1]+1;
            }
        }
        System.out.println(visit[b]);
    }
}