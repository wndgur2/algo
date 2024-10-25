import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] indexes = new int[N];
        ArrayList<Integer> LIS = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            int idx = (-Collections.binarySearch(LIS, numbers[i]) -1);
            // System.out.println(idx);
            // System.out.println("idx:" + idx + ", LIS.size(): " + LIS.size());
            if(idx<0) {
                indexes[i] = -idx-1;
            } else if(idx>= LIS.size()){
                indexes[i] = LIS.size();
                LIS.add(numbers[i]);
            } else{
                indexes[i] = idx;
                LIS.set(idx, numbers[i]);
            }
        }

        // System.out.println(LIS);
        // System.out.println(Arrays.toString(indexes));
        int curIdx = LIS.size()-1;
        ArrayDeque<Integer> stack = new ArrayDeque<>(); 
        for(int i=N-1; i>=0; i--){
            if(indexes[i] == curIdx){
                curIdx--;
                stack.addFirst(numbers[i]);
                if(curIdx==-1) break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(LIS.size()).append("\n");
        while(!stack.isEmpty())sb.append(stack.pop() + " ");
        System.out.println(sb.toString().trim());
    }
}
