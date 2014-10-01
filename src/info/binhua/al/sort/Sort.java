package info.binhua.al.sort;

import java.util.Random;

public class Sort {

	//create a random array
	public int[] createArr(int N){
		Random random = new Random();
		int[] arr = new int[N];
		for(int i=0; i<N; i++){
			arr[i] = random.nextInt(100);
		}
		return arr;
	}
	
	//display array
	public void display(String name, int[] arr){
		System.out.print(name);
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	//bubble soft
	public void bubbleSoft(int[] arr){
		int len = arr.length;
		int out, in;
		for(out=len-1; out>1; out--){
			for(in=0; in<out; in++){
				if(arr[in] > arr[in+1])
					swap(arr, in, in+1);
			}
		}
	}
	
	//select sort
	public void selectSoft(int[] arr){
		int len = arr.length;
		int out, in, min;
		for(out=0; out<len-1; out++){
			min = out;
			for(in=out+1; in<len; in++){
				if(arr[in] < arr[min]){
					min = in;
				}
			}
			swap(arr, out, min);
		}
	}
	
	//insert soft
	public void insertSort(int[] arr){
		int len = arr.length;
		int out, in;
		for(out=1; out<len; out++){
			int temp = arr[out];
			in = out;
			while(in>0 && arr[in-1]>=temp){
				arr[in] = arr[in-1];
				in = in-1;
			}
			arr[in] = temp;
		}
	}
	
	
	
	//swap two element
	public void swap(int[] arr, int a, int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	//test main()
	public static void main(String[] args){
		Sort sort = new Sort();
		int[] arr = sort.createArr(10);
		sort.display("Original Array: ", arr);
//		sort.bubbleSoft(arr);
		sort.insertSort(arr);
		sort.display("insert    Soft: ", arr);
	}
}
