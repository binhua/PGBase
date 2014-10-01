package info.binhua.base;
public class TestGraph{
	public static void main(String[] args){
		Graph graph = new Graph();
		graph.addNode('A');
		graph.addNode('B');
		graph.addNode('C');
		graph.addNode('D');
		graph.addNode('E');
		graph.addNode('F');
		graph.addNode('G');
		graph.addNode('H');
		graph.addNode('I');
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(1, 5);
		graph.addEdge(5, 7);
		graph.addEdge(3, 6);
		graph.addEdge(6, 8);
		
		graph.displayTree();
		
		System.out.print("DFS visit: ");
		graph.dfs();
		System.out.println();
		
		System.out.print("BFS visit: ");
		graph.bfs();
	}
}

class Graph {
	private final static int MAX_NODE_SIZE = 20;
	private Node[] nodes;
	private GStack gStack;
	private GQueue gQueue;
	private int[][] rG;
	private int nodeNums;
	

	public Graph(){
		nodes = new Node[MAX_NODE_SIZE];
		nodeNums = 0;
		gStack = new GStack();
		gQueue = new GQueue();
		rG = new int[MAX_NODE_SIZE][MAX_NODE_SIZE];
		for(int i=0; i<MAX_NODE_SIZE; i++)
			for(int j=0; j<MAX_NODE_SIZE; j++)
				rG[i][j] = 0;
	}
	
	//add node
	public void addNode(char lab){
		nodes[nodeNums++] = new Node(lab);
	}
	
	//add edge
	public void addEdge(int start, int end){
		rG[start][end] = 1;
		rG[end][start] = 1;
	}
	
	/*
	 * deep first search
	 */
	public void dfs(){
		nodes[0].isVisited = true;
		displayNode(0);
		gStack.push(0);
		while(!gStack.isEmpty()){
			int v = getUnVisitedNode(gStack.peek());
			if(v == -1){
				gStack.pop();
			}else{
				nodes[v].isVisited = true;
				displayNode(v);
				gStack.push(v);
			}
		}
		//restart set the node state
		reStart();
	}
	
	/*
	 * bread first search
	 */
	public void bfs(){
		nodes[0].isVisited = true;
		displayNode(0);
		gQueue.insert(0);
		int v2;
		while(!gQueue.isEmpty()){
			int v1 = gQueue.remove();
			while((v2=getUnVisitedNode(v1)) != -1){
				gQueue.insert(v2);
				nodes[v2].isVisited = true;
				displayNode(v2);
			}
		}
		reStart();
	}
	
	//return unvisited nearby
	public int getUnVisitedNode(int p) {
		for(int i=0; i<nodeNums; i++){
			if(rG[p][i]==1 && nodes[i].isVisited==false){
				return i;
			}
		}
		return -1;
	}

	//display node
	public void displayNode(int p){
		System.out.print(nodes[p].label);
	}
	
	//restart the label
	public void reStart(){
		for (int i = 0; i <nodeNums; i++) {
			nodes[i].isVisited = false;
		}
	}
	
	//simple print the tree
	public void displayTree(){
		System.out.println("   A");
		System.out.println("B C D E");
		System.out.println("F   G  ");
		System.out.println("H   I  ");
	}
}

//the node
class Node{
	public char label;
	public boolean isVisited;
	
	public Node(char name){
		this.label = name;
		isVisited = false;
	}                                                                                                      
}

//the stack store the index of node
class GStack{
	private final int SIZE = 20;
	private int[] gStack;
	private int top;
	
	public GStack(){
		gStack = new int[SIZE];
		top = -1;
	}
	
	public void push(int p){
		gStack[++top] = p;
	}
	
	public int pop(){
		return gStack[top--];
	}
	
	public int peek(){
		return gStack[top];
	}
	
	public boolean isEmpty(){
		return (top == -1);
	}
}

//the queue store the index of node
class GQueue{
	private final int SIZE = 20;
	private int[] gQueue;
	private int front;
	private int rear;
	
	public GQueue(){
		gQueue = new int[SIZE];
		front = 0;
		rear = -1;
	}
	
	public void insert(int p){
		if(rear == SIZE-1){
			rear = -1;
		}
		gQueue[++rear] = p;
	}
	
	public int remove(){
		int temp = gQueue[front++];
		if(front == SIZE){
			front = 0;
		}
		return temp;
	}
	
	public boolean isEmpty(){
		return (rear+1 == front || front+SIZE-1 == rear);
	}
}
