import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb;
	
	static int N;
	static String dir;
	static int[][] board;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append('\n');
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dir = st.nextToken();
			
			board = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve();
		}
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		
		// 시계방향으로 회전
		if(dir.equals("left")) turn(3);
		else if(dir.equals("up")) turn(2);
		else if(dir.equals("right")) turn(1);
		
		down();

		// 시계방향으로 회전
		if(dir.equals("left")) turn(1);
		else if(dir.equals("up")) turn(2);
		else if(dir.equals("right")) turn(3);
		
		sb.append(boardToString());
	}
	
	public static void turn(int n) {
/**
		보드를 시계방향으로 90도 돌림
		
		1 2 3
		4 5 6
		7 8 9

		7 4 1
		8 5 2
		9 6 3

		0,0 => 0,2
		0,1 => 1,2
		0,2 => 2,2
*/
		int[][] oldBoard = board;
		int[][] newBoard = new int[N][N];
		for (int i = 0; i < n; i++) {
			// set newBoard
			for(int r=0; r<N; r++) {
				for (int c = 0; c < N; c++) {
					newBoard[r][c] = oldBoard[N-1-c][r];  
				}
			}
			
			// 보드 카피 O(400)
			for (int j = 0; j < N; j++) {
				oldBoard[j] = Arrays.copyOf(newBoard[j], N);
			}
		}
	}
	
	public static void down() {
		//각 열에 대햐여
		for (int c = 0; c < N; c++) {
			
			// 밑으로 당기기 (Queue) 0의 좌표를 담는 큐
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.clear();
			for (int r = N-1; r >= 0; r--) {
				if(board[r][c]==0) {
					// 큐의 앞에 r을 담는다.
					q.addFirst(r);
				} else {
					if(q.isEmpty()) continue;
					
					// board[큐.last][c]에 board[r][c]를 담는다.
					board[q.pollLast()][c] = board[r][c];
					// board[r][c]를 0으로 만든다.
					board[r][c] = 0;
					// 스택에 r을 담는다.
					q.addFirst(r);
				}
			}
			
			//각 행에 대하여(역순) 위 행과 합치기
			for (int r = N-2; r >= 0; r--) {
				if(board[r][c]==0) continue;
				if(board[r][c] == board[r+1][c]) {
					board[r+1][c] *=2;
					board[r][c] = 0;
					r--; // board[r][c]가 0이라 다음엔 당연히 안 합쳐질 것 
				}
			}
			
			// 밑으로 당기기 (Queue) 0의 좌표를 담는 큐
			q.clear();
			for (int r = N-1; r >= 0; r--) {
				if(board[r][c]==0) {
					// 큐의 앞에 r을 담는다.
					q.addFirst(r);
				} else {
					if(q.isEmpty()) continue;
					
					// board[큐.last][c]에 board[r][c]를 담는다.
					board[q.pollLast()][c] = board[r][c];
					// board[r][c]를 0으로 만든다.
					board[r][c] = 0;
					// 스택에 r을 담는다.
					q.addFirst(r);
				}
			}
		}
	}
	
	public static String boardToString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(board[i][j]).append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
