package info.binhua.ds.rbtree;

class Node{
	public int data;
	public int color;
	
	public Node parent;
	public Node lChild;
	public Node rChild;

	public Node(int data) {
		this.data = data;
		this.color = 0;
		this.parent = null;
		this.lChild = null;
		this.rChild = null;
	}
}

public class RBTree {
	public static final int RED = 0;
	public static final int BLACK = 1;
	public Node root;
	
	//insert
	public void insert(int key){
		Node newNode = new Node(key);
		if(root == null){
			root = newNode;
		}else{
			Node curParent = null;
			Node curNode = root;
			boolean isLchild = false;
			while(curNode != null){
				curParent = curNode;
				if(key < curNode.data){ //go left child
					curNode = curNode.lChild;
					isLchild = true;
				}else{ //go right child
					curNode = curNode.rChild;
				}
			}
			if(isLchild)
				curParent.lChild = newNode;
			else
				curParent.rChild = newNode;
			newNode.parent = curParent; //point to its parent
			
			/*to adjust RBtree*/
			//the newNode as the root node
			insert_case1(newNode);
		}
	}
	
	//1.newNode is root
	private void insert_case1(Node newNode){
		if(newNode.parent == null)
			newNode.color = BLACK;
		else
			insert_case2(newNode);
	}
	
	//2.newNode's parent is BLACK
	private void insert_case2(Node newNode) {
		if(newNode.parent.color == BLACK)
			return ;//the tree is still right, do nothing
		else
			insert_case3(newNode);
	}

	//3.newNode's parent is RED
	private void insert_case3(Node newNode) {
		//to see its uncle's color
		Node uncNode = null;
		Node gNode = newNode.parent.parent;
		if(gNode.lChild == newNode.parent)
			uncNode = gNode.rChild;
		else
			uncNode = gNode.rChild;
		//3.1newNode's parent and its uncle are all RED
		if(uncNode != null && uncNode.color == RED){
			newNode.parent.color = BLACK;
			uncNode.color = BLACK;
			gNode.color = RED;
			//gNode now is RED, maybe its parent is RED too, so recheck rbtree
			insert_case1(gNode);
		}else
			insert_case4(newNode);
	}
	
	//4.newNode's parent is RED, but its uncle is BLACK
	private void insert_case4(Node newNode){
		//prepare to rotate
		//newNode is right child and its parent is left child
		if(newNode.parent == newNode.parent.parent.lChild){
			if(newNode == newNode.parent.rChild){
				//TODO:rotate left
				
			}else{ // newNode is left child
				
			}
		}else //its parent is right child
			insert_case5(newNode);
	}
	
	//5.newNode's parent is RED and is right child, its uncle is BLACK
	public void insert_case5(Node newNode){
		
	}

	public static void main(String[] args) {
		Node n = new Node(2);
	}
}
