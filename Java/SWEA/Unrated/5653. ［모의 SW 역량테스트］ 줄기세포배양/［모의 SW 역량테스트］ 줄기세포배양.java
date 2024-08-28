import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Cell{
	int life, time, y, x;
	boolean isActivated;
	Cell(int life, int y, int x){
		this.life = life;
		this.time = life;
		this.y = y;
		this.x = x;
	}
	
	@Override
	public String toString() {
		return time+"/"+life+' ';
	}
}

public class Solution {
	static int H, W, OFFSET_H, OFFSET_W;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Cell[][] map;
	static int turn, n, m;
	static int[][] visited;
	static ArrayDeque<Cell> activeQueue, inactiveQueue, reproduceQueue;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int k, size;
		Cell cell;
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			activeQueue = new ArrayDeque<>();
			inactiveQueue = new ArrayDeque<>();
			reproduceQueue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			H = n+k+1;
			W = m+k+1;
			OFFSET_H = H/2 - n/2;
			OFFSET_W = W/2 - m/2;

			map = new Cell[H][W];
			visited = new int[H][W];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int life = Integer.parseInt(st.nextToken());
					if(life == 0) continue;
					cell = new Cell(life, OFFSET_H+i, OFFSET_W+j);
					visited[i+OFFSET_H][j+OFFSET_W] = 1;
					map[i+OFFSET_H][j+OFFSET_W] = cell;
					inactiveQueue.add(cell);
				}
			}
			
			// 턴마다
			turn = 1;
			
			// 2초부터 시작
			while(turn++ <= k) {
				
				// 활성화 큐 => 시간 흐르기=> 번식/죽기
				size = activeQueue.size();
				for (int i = 0; i < size; i++) {
					cell = activeQueue.pollFirst();
					
					// 시간 흐름
					--cell.time;
					
					// 방금 활성화된 세포: 번식
					if(cell.life-cell.time == 1) reproduceQueue.add(cell);
					
					// 다 쓴 세포: 죽음
					if(cell.time==0) continue;
					activeQueue.add(cell);
				}
				
				size = inactiveQueue.size();
				
				// 비활성화 시간 흐르기, 활성화.
				for (int i = 0; i < size; i++) {
					cell = inactiveQueue.pollFirst();
					if(--cell.time == 0) {
						cell.time = cell.life;
						activeQueue.add(cell);
					}
					else inactiveQueue.add(cell);
				}
				while(!reproduceQueue.isEmpty()) {
					reproduce(reproduceQueue.pollFirst());
				}
				
//				printMap();
			}
//			System.out.println(activeQueue);
//			System.out.println(inactiveQueue);
			sb.append(activeQueue.size() + inactiveQueue.size()).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static void printMap() {
		for (int i = 0; i < n+20; i++) {
			for (int j = 0; j < m+20; j++) {
				System.out.print(map[i+140][j+140] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void reproduce(Cell cell) {
		int ny, nx;
		for(int[] dir: dirs) {
			ny = cell.y + dir[0];
			nx = cell.x + dir[1];
			// 이미 있던 셀이면 번식 안함
			if(visited[ny][nx]!=0 && visited[ny][nx]<turn) continue;
			// 셀이 나보다 크면 번식 안함
			if(map[ny][nx] != null && map[ny][nx].life>=cell.life) continue;
			// 셀이 나보다 작으면 번식을 하는데, 만들었던 셀을 지움.
			else if(map[ny][nx] != null && map[ny][nx].life<cell.life) inactiveQueue.remove(map[ny][nx]);
			
			Cell newCell = new Cell(cell.life, ny, nx);
			map[ny][nx] = newCell;
			visited[ny][nx] = turn;
			inactiveQueue.add(map[ny][nx]);
		}
	}

}
