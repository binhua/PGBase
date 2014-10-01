package info.binhua.al.trasm;

import java.util.Random;

/**
 *一个矩阵存储在一个一维数组里面
 *空间复杂度O(1)转置矩阵
 */
public class TransM {
	public int[] theM;
	public int N;
	public int Row;
	public int Col;
	
	public TransM(int r, int c){
		this.N = r*c;
		this.theM = new int[N];
		this.Row = r;
		this.Col = c;
	}
	
	//get the pre
	public int pre(int index){
		return (index%Row)*Col+(index/Row);
	}
	
	//create the M
	public void createM(){
		Random random = new Random();
		for(int i=0; i<N; i++){
			theM[i] = random.nextInt(N); 
		}
	}
	
	//display the mutilM
	public void displayM(int[] arr){
		for(int j=0; j<arr.length; j++)
			System.out.print(arr[j]+"   ");
		System.out.println();
		for(int i=0; i<arr.length; i++){
			if(i%Col == 0){
				System.out.println();
			}
			System.out.print(arr[i]+"   ");
		}
	}
	
	// display the mutilplay after trans
	public void displayTansM(int[] arr){
		System.out.println();
		for(int i=0; i<arr.length; i++){
			if(i%Row == 0){
				System.out.println();
			}
			System.out.print(arr[i]+"   ");
		}
	}
	
	//trans
	public void trans(int[] arr){
		for(int i=0; i<arr.length; i++){
			int index = i;
			int preIndex = pre(index);
			while(preIndex > index){
				preIndex = pre(preIndex);
			}
			if(preIndex < index) continue;
			else{ // no more preIndex biger than index
				//to do trans
				int temp = arr[index]; //store the first value
				int pIndex = pre(index); //find the first pre
				int cIndex = index;
				while(pIndex != index){
					arr[cIndex] = arr[pIndex];
					cIndex = pIndex;
					pIndex = pre(cIndex);
				}
				arr[cIndex] = temp;
			}
		}
	}
	
	//test
	public static void main(String[] args) {
		TransM arr = new TransM(3, 4);
		arr.createM();
		arr.displayM(arr.theM);
		arr.trans(arr.theM);
		arr.displayTansM(arr.theM);
	}
}
