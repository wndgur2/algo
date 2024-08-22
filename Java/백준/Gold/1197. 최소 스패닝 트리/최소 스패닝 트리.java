import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	Node root = this;
	int idx;
	
	Node(int idx){
		this.idx = idx;
	}
	
	void union(Node to) {
		root.root = to.root;
	}
	
	Node getRoot() {
		if(root == this) return this;
		root = root.getRoot();
		return root;
	}
}

class Edge implements Comparable<Edge>{
	Node from;
	Node to;
	int cost;
	
	public Edge(Node from, Node to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge other) {
		return cost - other.cost;
	}
}

public class Main {
	static int S, E;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Node[] nodes;
	static Edge[] edges;
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		nodes = new Node[S];
		for (int i = 0; i < S; i++)
			nodes[i] = new Node(i);
		edges = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			int from, to, cost;
			st = new StringTokenizer(br.readLine());
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(nodes[from-1], nodes[to-1], cost);
		}
		Arrays.sort(edges);
		
		int res=0;
		for (int i = 0; i < E; i++) {
			Node from = edges[i].from;
			Node to = edges[i].to;
			int cost = edges[i].cost;
			
			if(from.getRoot() == to.getRoot()) continue;
			
			// connect
			res += cost;
			from.union(to);
		}
		System.out.println(res);
	}
}
