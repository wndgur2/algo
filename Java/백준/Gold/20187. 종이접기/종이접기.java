import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static char[] folds;
	static int[][] board;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 입력
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		folds = new char[K*2];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K*2; i++) {
			folds[i] = st.nextToken().charAt(0);
		}
		
		// 2^k * 2^k int 보드 생성
		int l = (int)Math.pow(2, K);
		board = new int[l][l];
		
		// D: y+, R: x+ => 구멍이 그대로 뚫리는 위치 찾기
		int y=0, x=0, dy=l, dx=l;
		for (int i = 0; i < K*2; i++) {
			switch (folds[i]) {
			case 'D':
				dy /= 2;
				y += dy;
				break;
			case 'R':
				dx /= 2;
				x += dx;
				break;
			case 'U':
				dy /= 2;
				break;
			case 'L':
				dx /= 2;
				break;
			default:
				System.out.println("FOLDS ERR: 46");
				break;
			}
		}
		
		// 해당 위치 구멍을 input으로 설정.
		board[y][x] = Integer.parseInt(br.readLine());
		
		// 대칭점 구멍 설정을 반복 (K*2번)
		int h=1, w=1;
		for (int i = K*2-1; i >= 0; i--) {
			// 접은거의 역순, 시작좌표 얻기
			switch (folds[i]) {
			case 'D': // 위로 펴기
				for (int ty = y; ty < y+h; ty++) {
					for (int tx = x; tx < x+w; tx++) {
						// 대칭찍기
						if(h==1) board[ty-h][tx] = flipY(board[ty][tx]);
						else board[ty-h][tx] = board[ty][tx];
					}
				}
				y-=h;
				h*=2;
				break;
			case 'U': // 아래로 펴기
				for (int ty = y; ty < y+h; ty++) {
					for (int tx = x; tx < x+w; tx++) {
						// 대칭찍기
						if(h==1) board[ty+h][tx] = flipY(board[ty][tx]);
						else board[ty+h][tx] = board[ty][tx];
					}
				}
				h*=2;
				break;
			case 'R': // 왼쪽으로 펴기
				for (int ty = y; ty < y+h; ty++) {
					for (int tx = x; tx < x+w; tx++) {
						// 대칭찍기
						if(w==1) board[ty][tx-w] = flipX(board[ty][tx]);
						else board[ty][tx-w] = board[ty][tx];
					}
				}
				x-=w;
				w*=2;
				break;
			case 'L': // 오른쪽으로 펴기
				for (int ty = y; ty < y+h; ty++) {
					for (int tx = x; tx < x+w; tx++) {
						// 대칭찍기
						if(w==1) board[ty][tx+w] = flipX(board[ty][tx]);
						else board[ty][tx+w] = board[ty][tx];
					}
				}
				w*=2;
				break;
			}
		}
		
		// 답 출력
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l-1; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append(board[i][l-1]).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static int flipY(int prev) {
		switch (prev) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		case 3:
			return 1;
		}
		System.out.println("flipY err");
		return 0;
	}
	
	public static int flipX(int prev) {
		switch (prev) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		System.out.println("flipX err");
		return 0;
	}
}
