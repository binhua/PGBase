package info.binhua.base;
public class TestPriortyQueue {

	public static void main(String[] args) {
		
		PriortyQueueArray pQArray = new PriortyQueueArray(6);
		pQArray.insertQ(3);
		pQArray.insertQ(5);
		pQArray.insertQ(12);
		pQArray.insertQ(4);
		pQArray.insertQ(43);
		pQArray.insertQ(23);
		
		while(!pQArray.isEmpty()){
			int item = pQArray.removeQ();
			System.out.print(item+" ");
		}
	}
}

//priority queue
class PriortyQueueArray{
	private int MAXSIZE;
	private int[] qArray;
	private int num;
	
	public PriortyQueueArray(int size){
		MAXSIZE = size;
		qArray = new int[MAXSIZE];
		num = 0;
	}
	
	//the minimum item has the priority
	public void insertQ(int item){
		int j;
		if(num == 0){
			qArray[num++] = item;
		}else{
			for(j=num-1; j>=0; j--){
				if(item > qArray[j])
					qArray[j+1] = qArray[j];
				else
					break;
			}
			qArray[j+1] = item;
			num++;
		}
	}
	
	public int removeQ(){
		return qArray[--num];
	}
	
	public int peekMin(){
		return qArray[num-1];
	}
	
	public boolean isEmpty(){
		return num==0;
	}
	
	public boolean isFull(){
		return num == MAXSIZE;
	}
	
	public String toString(){
		StringBuffer sbBuffer = new StringBuffer();
		for (int i = 0; i < qArray.length; i++) {
			sbBuffer.append(qArray[i]+" ");
		}
		return sbBuffer.toString();
	}
}
