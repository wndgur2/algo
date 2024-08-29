import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, res;
	static int[][] room, stairs;
	static ArrayList<int[]> people;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			res = 1000000000;
			N = Integer.parseInt(br.readLine());
			
			room = new int[N][N];
			people = new ArrayList<>();
			stairs = new int[2][2];
			stairs[0] = null;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
					if(room[i][j] == 1) people.add(new int[] {i,j});
					else if(room[i][j] > 1) {
						if(stairs[0] == null) stairs[0] = new int[] {i, j};
						else stairs[1] = new int[] {i, j};
					}
				}
			}
			
			// 누가 어느 계단으로 갈 건지
			subset(0);
			System.out.println("#" + tc + " " + res);
		}
	}

	public static void subset(int depth) {
		if(depth == people.size()) {
			// 최소 시간 구하기
			int temp = getTime();
			if(temp <= res)
				res = temp;
			return;
		}
		int y = people.get(depth)[0];
		int x = people.get(depth)[1];

		room[y][x] = -1;
		subset(depth+1);
		room[y][x] = -2;
		subset(depth+1);
	}
	
	public static int getTime() {
		// 사람들의 목적 계단까지의 거리
		PriorityQueue<Integer>[] pqs = new PriorityQueue[2];
		pqs[0] = new PriorityQueue<>();
		pqs[1] = new PriorityQueue<>();

		// 계단을 내려가는 중인 사람들의 남은 시간
		ArrayDeque<Integer>[] stairsQ = new ArrayDeque[2];
		stairsQ[0] = new ArrayDeque<>();
		stairsQ[1] = new ArrayDeque<>();
		
		for (int i = 0; i < people.size(); i++) {
			int[] p = people.get(i);
			int[] stair=null;
			PriorityQueue<Integer> pq = null;
			if(room[p[0]][p[1]] == -1) {
				pq = pqs[0];
				stair = stairs[0];
			} else if(room[p[0]][p[1]] == -2){
				pq = pqs[1];
				stair = stairs[1];
			}
			pq.add(Math.abs(p[0] - stair[0]) + Math.abs(p[1] - stair[1]));
		}
		
		int time = 0;
		int peopleN = people.size();
		
		while(peopleN>0) {
			time++;
			for(int i=0; i<2; i++) {
				// 계단에서 사람 빼기
				int size = stairsQ[i].size();
				for (int j = 0; j < size; j++) {
					int left = stairsQ[i].pollFirst();
					if(--left>0) stairsQ[i].add(left);
					else {
						peopleN--;
					}
				}
				
				// 사람 계단에 넣기 
				while(stairsQ[i].size()<3 && !pqs[i].isEmpty() && pqs[i].peek()<time) {
					pqs[i].poll();
					stairsQ[i].add(room[stairs[i][0]][stairs[i][1]]);
				}
			}
		}
		return time;
	}

}
