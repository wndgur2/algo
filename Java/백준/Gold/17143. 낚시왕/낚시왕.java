import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Shark implements Comparable<Shark>{
	int y, x, speed, dir, size;
	
	Shark(int y, int x, int speed, int dir, int size){
		this.y = y;
		this.x = x;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
	
	@Override
	public int compareTo(Shark o) {
		return o.size - size;
	}
}

public class Main {
	static int H, W, N, res;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Shark[][] cage;
	static TreeSet<Shark> sharks;
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		sharks = new TreeSet<>();
		res = 0;
		
		cage = new Shark[H][W];
		
		int r, c, s, d, z;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r,c,s,d,z);
			sharks.add(shark);
			cage[r][c] = shark;
		}
		
		for (int i = 0; i < W; i++) {
//			printCage();
			res += catchShark(i);
			move();
		}
//		printCage();
		
		System.out.println(res);
	}
	
	public static void move() {
		ArrayList<Shark> deadSharks = new ArrayList<>();
		Iterator<Shark> iter = sharks.iterator();
		while(iter.hasNext()) {
			Shark shark = iter.next();
			
			// 아무도 여기로 안옴
			if(cage[shark.y][shark.x] == shark)
				cage[shark.y][shark.x] = null;
			
			// 상어 이동
			move(shark);
			
			if(cage[shark.y][shark.x] != null) {
				// 큰놈이 이미 와있음
				if(cage[shark.y][shark.x].size > shark.size) {
					deadSharks.add(shark); // 나 죽어
				} else {// 작은놈이 아직 안움직임
					// 내가 대신 들어감
					cage[shark.y][shark.x] = shark;
				}
			} else { // 아무도 없음
				cage[shark.y][shark.x]= shark; 
			}
		}
		for(Shark shark:deadSharks) {
			sharks.remove(shark);
		}
	}
	
	public static void move(Shark shark) {
//		System.out.println(shark.y + " " + shark.x + " d: " + shark.dir + " s: " + shark.speed);
		int speed = shark.speed;
		int y = shark.y, x = shark.x;
		switch (shark.dir) {
		case 1: // 위
			speed %= 2*(H-1);
			
			if(speed >= H + y) {
				shark.y = H-2 -(speed-H-y);
			} else if(speed > y) {
				shark.dir = 2;
				shark.y = speed-y;
			} else {
				shark.y -= speed;
			}
			break;
		case 2: // 아래
			speed %= 2*(H-1);
			
			if(speed > H-1-y + H-1) {
				shark.y = speed-H-(H-2-y);
			} else if(speed > H-1-y) {
				shark.dir = 1;
				shark.y = H-1 - (speed-(H-1-y));
			} else {
				shark.y += speed;
			}
			break;
		case 3: // 오른
			speed %= 2*(W-1);
			
			if(speed > W-1-x + W-1) {
				shark.x = speed-W-(W-2-x);
			} else if(speed > W-1-x) {
				shark.dir = 4;
				shark.x = W-1 - (speed-(W-1-x));
			} else {
				shark.x += speed;
			}
			break;
		case 4: // 왼
			speed %= 2*(W-1);
			
			if(speed >= W + x) {
				shark.x = W-2 -(speed-W-x);
			} else if(speed > x) {
				shark.dir = 3;
				shark.x = speed-x;
			} else {
				shark.x -= speed;
			}
			break;
		}
//		System.out.println(shark.y+ " " + shark.x);
//		System.out.println("=====");
	}
	
	public static int catchShark(int col) {
		for (int i = 0; i < H; i++) {
			if(cage[i][col]==null) continue;
//			System.out.println("CATCH: " + cage[i][col].size);
			int res = cage[i][col].size;
			sharks.remove(cage[i][col]);
			cage[i][col] = null;
			return res;
		}
		return 0;
	}
	
	public static void printCage() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(cage[i][j] == null)
					System.out.print("  x  ");
				else
					System.out.print("  " + cage[i][j].size + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
