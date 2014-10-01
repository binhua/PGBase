package info.binhua.ds.mqueue;

import javax.swing.text.MaskFormatter;

class MQueue {
	public int maxSize;
	public int front;
	public int rear;
	public Object[] qArr;
	public int nItems;
	
	public MQueue(int size){
		this.maxSize = size;
		front = 0;
		rear = -1;
		qArr = new Object[maxSize];
		nItems = 0;
	}
	
	//insert 
	public void insert(Object obj){
		if(rear == maxSize-1) //full
			rear = -1;
		qArr[++rear] = obj;
		nItems++;
		if(nItems>=maxSize)
			nItems = maxSize;
	}
	
	// remove
	public Object remove(){
		Object temp = qArr[front++];
		if(front == maxSize)
			front = 0;
		nItems--;
		return temp;
	}
	
	// peek at fron of queue
	public Object peekFront(){
		return qArr[front];
	}
	
	//is the queue full?
	public boolean isFull(){
		return nItems == maxSize;
	}
	
	// is the queue empty?
	public boolean isEmpty(){
		return nItems == 0;
	}
	
	// the queue's length
	public int size(){
		return nItems;
	}
}

//priority queue
class PriorityQ{
	public int maxSize;
	public int nItems;
	public int[] pArr;
	
	public PriorityQ(int size){
		this.maxSize = size;
		pArr = new int[maxSize];
		nItems = 0;
	}
	
	//insert
	public void insert(int obj){
		int i;
		if(nItems >= maxSize) 
			return;// full
		if(nItems == 0){ // empty queue
			pArr[nItems++] = obj;
		}else{
			for(i=nItems-1; i>=0; i--){
				if(obj > pArr[i])
					pArr[i+1] = pArr[i];
				else
					break;
			}
			pArr[i+1] = obj;
			nItems++;
		}
	}
	
	//remove
	public int remove(){
		return pArr[--nItems];
	}
	
	//is full
	public boolean isFull(){
		return nItems == maxSize;
	}
	
	//is empty
	public boolean isEmpty(){
		return nItems == 0;
	}
	
	//peek
	public int peek(){
		return pArr[nItems - 1];
	}
}

// test
public class MQueueTest{
	
	public static void main(String[] args) {
		MQueue queue = new MQueue(3);
		queue.insert(2);
		queue.insert(21);
		queue.insert(12);
		queue.insert(22);
		
		while(!queue.isEmpty()){
			System.out.print(queue.remove()+" ");
		}
		
		PriorityQ pq = new PriorityQ(5);
		pq.insert(2);
		pq.insert(22);
		pq.insert(23);
		System.out.println();
		while(!pq.isEmpty()){
			System.out.print(pq.remove()+" ");
		}
		
	}
	
}
