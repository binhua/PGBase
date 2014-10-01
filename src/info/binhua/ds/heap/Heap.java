package info.binhua.ds.heap;

public class Heap {

	private Node[] heapArray;
	private int maxSize;
	private int curSize;
	
	public Heap(int mx){
		this.maxSize = mx;
		curSize = 0;
		heapArray = new Node[maxSize];
	}

	public boolean isEmpty(){
		return curSize == 0;
	}
	
	public boolean insert(int key){
		if(curSize == maxSize) 
			return false;
		Node newNode = new Node(key);
		heapArray[curSize] = newNode;
		trickUp(curSize++);
		return true;
	}

	public void trickUp(int index) {

		int parent = (index-1)/2;
		Node bottom = heapArray[parent];
		while(index>0 && bottom.data>heapArray[parent].data){
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent-1)/2;
		}
		heapArray[index] = bottom;
	}
	
	public Node remove(){
		Node root = heapArray[0];
		heapArray[0] = heapArray[--curSize];
		trickDown(0);
		return root;
	}
	
	public void trickDown(int index){
		int largeChild;
		Node top = heapArray[index];
		while(index<curSize/2){
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;
			
			if(rightChild<curSize &&
					heapArray[leftChild].data<heapArray[rightChild].data){
				largeChild = rightChild;
			}else{
				largeChild = leftChild;
			}
			
			if(top.data >= heapArray[largeChild].data)
				break;
			heapArray[index] = heapArray[largeChild];
			index = largeChild;
		}
		heapArray[index] = top;
	}
}

class Node{
	public int data;
	
	public Node(int key){
		this.data = key;
	}
}


