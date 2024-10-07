import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*

4
2 KIWI BANANA
2 KIWI APPLE
2 APPLE APPLE
3 APPLE BANANA KIWI

APPLE
--APPLE
--BANANA
----KIWI
KIWI
--APPLE
--BANANA

 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node implements Comparable<Node>{
		TreeSet<Node> children = new TreeSet<>();
		String value;
		
		Node(String value){
			this.value = value;
		}
		
		void addChild(Node node) {
			children.add(node);
		}
		
		@Override
		public int compareTo(Node o) {
			return this.value.compareTo(o.value);
		}
	}
	
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		
		Node root = new Node("");
		
		// 노드 추가 추가 추가
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			Node prev = root;
			for (int j = 0; j < n; j++) {
				String value = st.nextToken();
				
				Node node = new Node(value);
				if(prev.children.contains(new Node(value))) {
					node = prev.children.floor(new Node(value));
				} else {
					prev.addChild(node);
				}
				prev = node;
			}
		}
		
		dfs(root, 0);
	}
	
	public static void dfs(Node node, int depth) {
		for (int i = 1; i < depth; i++)
			System.out.print("--");
		if(!node.value.equals("")) System.out.println(node.value);
		for(Node child: node.children) {
			dfs(child, depth+1);
		}
	}

}
