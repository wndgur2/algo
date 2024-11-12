import java.io.*;
import java.util.*;

/*
5
2 4 1 5 3
4 5 1 3 2

3
3 4 5



5
3 4 5 1 2
1 2 3 4 5


*/

class Main{
  public static void main(String[] args)throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    // dp[i] : 전깃줄이 i까지 내려왔을 때 킬 수 있는 최대 전구 수
    int[] switches = new int[n];
    int[] switchLocs = new int[n];
    int[] bulbLocs = new int[n];
    int[] bulbs = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());

    int number;
    for(int i=0; i<n; i++){
      // i번째 스위치의 번호는 number다
      number = Integer.parseInt(st.nextToken())-1;
      switches[i] = number;
      // number번 스위치는 i에 있다.
      switchLocs[number] = i;
    }

    // switch 번호로 전구 위치를 찾아갈 수 있어야 함.
    // switches[i]에 i를 담기
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++){
      // 이 전구에 연결되는 스위치의 전구는 i번째에 있다.
      number = Integer.parseInt(st.nextToken())-1;
      bulbLocs[number] = i;
      bulbs[i] = number;
    }
    
    int[] dp = new int[n];
    int res = 0;
    int resIdx = -1;
    int from, to, maxBulbs, idx;
    int[] indexes = new int[n]; // 역추적
    for(int i=0; i<n; i++) indexes[i] = -1;
    for(int i=0; i<n; i++){
      from = i;
      to = bulbLocs[switches[i]]; // i번째 스위치의 번호와 같은 번호의 전구의 위치
      maxBulbs = 0;
      idx = -1;
      for(int j=0; j<to; j++){
        if(dp[j]>maxBulbs){
          maxBulbs = dp[j];
          idx = j;
        }
      }
      dp[to] = maxBulbs+1;
      indexes[to] = idx;
      if(dp[to]>res){
        res = dp[to];
        resIdx = to;
      }
    }
    ArrayList<Integer> resArr = new ArrayList<>();
    from = resIdx;
    while(from!=-1){
      resArr.add(bulbs[from]+1);
      from = indexes[from];
    }
    Collections.sort(resArr);
    System.out.println(res);
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<resArr.size(); i++){
      sb.append(resArr.get(i) + " ");
    }
    System.out.println(sb.toString().trim());

    // int maxTemp = 0;
    // ArrayList<Integer> res = new ArrayList<>();
    // for(int i=0; i<n; i++){
    //   if(dp[i]>maxTemp) {
    //     res.add(bulbs[i]+1);
    //     maxTemp = dp[i];
    //   }
    // }
    // System.out.println(maxTemp);
    // Collections.sort(res);
  }
}