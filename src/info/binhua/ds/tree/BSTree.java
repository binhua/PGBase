package info.binhua.ds.tree;
import info.binhua.ds.mstack.MStack;

// the node
class Node{
	public int data;
	public Node lChild;
	public Node rChild;
	
	public Node(int data){
		this.data = data;
		lChild = null;
		rChild = null;
	}
}

// the node tree
public class BSTree {
	// the root
	public Node root;
	
	public BSTree(){
		root = null;
	}
	
	//insert node
	/*public void insert(int newData){
		Node newNode = new Node(newData);
		if(root == null) // empty tree
			root = newNode;
		else{
			Node parent = null;
			Node curNode = root;
			while(true){
				parent = curNode;
				if(newData<curNode.data){ // smaller than parent
					curNode = curNode.lChild;
					if(curNode == null){
						parent.lChild = newNode;
						return;
					}
				}else{ // bigger than parent
					curNode = curNode.rChild;
					if(curNode == null){
						parent.rChild = newNode;
						return;
					}
				}
			}// end while
		}// end else
	} // end insert
*/	
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
				}else{
					curNode = curNode.rChild;
				}
			}
			if(isLchild)
				curParent.lChild = newNode;
			else
				curParent.rChild = newNode;
		}
	}
	
	//find key
	public Node find(int key){
		Node curNode = root;
		while(curNode.data != key){
			if(key < curNode.data)
				curNode = curNode.lChild;
			else 
				curNode = curNode.rChild;
			if(curNode == null)
				return null;
		}
		return curNode;
	}
	
	//delete
	public boolean delete(int key){
		Node parent = null;
		Node curNode = root;
		boolean isLChild = true;
		while(curNode.data != key){
			parent = curNode;
			if(key < curNode.data){
				isLChild = true;
				curNode = curNode.lChild;
			}else{
				isLChild = false;
				curNode = curNode.rChild;
			}
			if(curNode == null)// not find the key node
				return false;
		}
		// find the node to delete
		// if no children , just delete it
		if(curNode.lChild==null && curNode.rChild==null){
			if(curNode == root) // is root?
				root = null;
			else if(isLChild) 	// left child
				parent.lChild = null;
			else 				// right child
				parent.rChild = null;
		// if no right child, replace with the left subtree
		}else if(curNode.rChild == null){
			if(curNode == root)
				root = root.lChild;
			else if(isLChild)
				parent.lChild = curNode.lChild;
			else
				parent.rChild = curNode.lChild;
		// if no left child, replace with the right subtree
		}else if(curNode.lChild == null){
			if(curNode == root)
				root = curNode.rChild;
			else if(isLChild)
				parent.lChild = curNode.rChild;
			else
				parent.rChild = curNode.rChild;
		// two children, then replace with the inorder successor
		}else{
			Node sorNode = getSuccessor(curNode); 
			if(curNode == root)
				root = sorNode;
			else if(isLChild)
				parent.lChild = sorNode;
			else
				parent.rChild = sorNode;
			sorNode.lChild = curNode.lChild;
		}
		return true;
	}
	
	// when the delete node have two childen, then get the successor node
	public Node getSuccessor(Node delNode){
		Node parent = delNode;
		Node successor = delNode;
		Node curNode = delNode.rChild; // can not be null 
		while(curNode != null){
			parent = successor;
			successor = curNode;
			curNode = curNode.lChild;
		}
		
		if(successor != delNode.rChild){
			parent.lChild = successor.rChild;
			successor.rChild = delNode.rChild;// the successor now will replace the delNode
		}
		return successor;
	}
	
	//find the max node
	public Node findMax(){
		Node maxNode = null;
		Node curNode = root;
		while(curNode != null){
			maxNode = curNode;
			curNode = curNode.rChild;
		}
		return maxNode;
	}
	
	//find the min node
	public Node findMin(){
		Node minNode = null;
		Node curNode = root;
		while(curNode != null){
			minNode = curNode;
			curNode = curNode.lChild;
		}
		return minNode;
	}
	
	//pre order
	public void preOrder(Node r){
		if(r != null){
			visit(r);
			preOrder(r.lChild);
			preOrder(r.rChild);
		}
	}
	 
	//in order
	public void inOrder(Node r){
		if(r != null){
			inOrder(r.lChild);
			visit(r);
			inOrder(r.rChild);
		}
	}
	
	//post order
	public void postOrder(Node r){
		if(r != null){
			postOrder(r.lChild);
			postOrder(r.rChild);
			visit(r);
		}
	}
	
	//display tree
	public void display(){
		MStack gStack = new MStack<Node>(30);
		gStack.push(root);
		
		int nBlanks = 34;
		boolean isRowEmpty = false;
		System.out.println("-----------------------------------------------------------------------------");
		while(!isRowEmpty){
			MStack lStack = new MStack<Node>(30);
			isRowEmpty = true;
			
			for(int i=0; i<nBlanks; i++)
				System.out.print(' ');
			while(!gStack.isEmpty()){
				Node temp = (Node)gStack.pop();
				if(temp != null){
					System.out.print(temp.data);
					lStack.push(temp.lChild);
					lStack.push(temp.rChild);
					if(temp.lChild != null || temp.rChild != null)
						isRowEmpty = false;
				}else{
					System.out.print("X");
					lStack.push(null);
					lStack.push(null);
				}
				for(int j=0; j<nBlanks*2-1; j++)
					System.out.print(' ');
			}// end while gstack
			System.out.println();
			System.out.println();
			nBlanks /= 2;
			while(!lStack.isEmpty()){
				gStack.push(lStack.pop());
			}
		}
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	//visit, just print the key
	public void visit(Node node){
		System.out.println(node.data+" ");
	}
	
	/**********************************Test*************************************/
	public static void main(String[] args){
		BSTree bsTree = new BSTree();
		bsTree.insert(8);
		bsTree.insert(3);
		bsTree.insert(5);
		bsTree.insert(4);
		bsTree.insert(14);
		bsTree.insert(7);
		bsTree.insert(9);
		bsTree.insert(12);
		bsTree.insert(22);
		
		bsTree.display();
		
		System.out.println();
		System.out.println("Max Node: "+ bsTree.findMax().data);
		System.out.println("find Key: "+ bsTree.find(14).data);
		System.out.println("Min Node: "+ bsTree.findMin().data);
		
		bsTree.delete(3);
		System.out.println("after delete node3: ");
		bsTree.display();
	}
	/**********************************End Test*********************************/
}
