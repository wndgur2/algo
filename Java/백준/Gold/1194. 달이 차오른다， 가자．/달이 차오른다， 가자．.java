import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int W, H;
	static boolean[][][] visited;
	static char[][] map;
	static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		visited = new boolean[H][W][64]; // flag: 0~63
		map = new char[H][W];
		
		int stY, stX, move, ny, nx, nf;
		stY=stX=move=0;
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					stY = i;
					stX = j;
				}
			}
		}
		visited[stY][stX][0] = true;
		
		int res = -1;
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(stY, stX, 0, move));
		while(!q.isEmpty()) {
			Node node = q.pollFirst();
			int y, x, flag;
			y = node.y;
			x = node.x;
			flag = node.flag;
			move = node.move;
			
//			System.out.println(y + " " + x + " " + flag);
			
			if(map[y][x] == '1') {
				res = move;
				break;
			}
			
			for(int[] dir:dirs) {
				ny = y + dir[0];
				nx = x + dir[1];
				nf = flag;
				
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(map[ny][nx]=='#') continue;
				
				if(visited[ny][nx][flag]) continue;
				visited[ny][nx][flag] = true;
				
				// 열쇠 먹기
				if(map[ny][nx] >= 'a' && map[ny][nx] <= 'f')
					nf |= 1<<(map[ny][nx]-'a');
				
				// 문 열기
				else if(map[ny][nx] >= 'A' && map[ny][nx] <='F')
					if((1<<(map[ny][nx]-'A') & flag) == 0) continue;
				
				q.add(new Node(ny, nx, nf, move+1));
			}
		}
		
		System.out.println(res);
	}
	
}

class Node{
	int y, x, flag, move;
	
	public Node(int y, int x, int flag, int move) {
		this.y = y;
		this.x = x;
		this.flag = flag;
		this.move = move;
	}
}
