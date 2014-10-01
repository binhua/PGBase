package info.binhua.ds.mstack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MStack<T> {

	public int maxSize;
	public Object[] stackArr;
	public int top;
	
	public MStack(int size){
		this.maxSize = size;
		stackArr = new Object[maxSize];
		top = -1;
	}
	
	//push, no full check 
	public void push(Object obj){
		stackArr[++top] = obj;
	}
	
	//pop
	public Object pop(){
		return stackArr[top--];
	}
	
	//peek at top of stack
	public Object peek(){
		return stackArr[top];
	}
	
	//is empty
	public boolean isEmpty(){
		return top == -1;
	}
	
	//is full
	public boolean isFull(){
		return top == maxSize-1;
	}
	
/*********************************Test**************************************/	
	/*public static void main(String[] args) {

		MStack<Object> stack = new MStack<Object>(10);
		stack.push("task1");
		stack.push("task2");
		stack.push("task3");
		stack.push("task4");
		stack.push("task5");
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+" ");
		}
		
		String  s = "123456";
		System.out.println(doRev(s));
		
		System.out.print("Enter string containing delimeters: ");
		System.out.flush();
		try {
			String bstr = new BufferedReader(new InputStreamReader(System.in)).readLine();
			chkBracketMah(bstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	//reverse the string
	public static String doRev(String word){
		int len = word.length();
		MStack<Object> wstack = new MStack<Object>(len); 
		for(int i=0; i<len; i++){
			wstack.push(word.charAt(i));
		}
		StringBuffer sBuffer = new StringBuffer();
		while(!wstack.isEmpty()){
			sBuffer.append(wstack.pop().toString());
		}
		return sBuffer.toString();
	}
	
	// match the bracket
	public static void chkBracketMah(String strBrac){
		int len = strBrac.length();
		MStack bstack = new MStack(len);
		
		for(int i=0; i<len; i++){
			char c = strBrac.charAt(i);
			switch (c) {
				case '{':
				case '[':
				case '(':
					bstack.push(c);
					break;
				case '}':
				case ']':
				case ')':
					if(!bstack.isEmpty()){
						char cx = (Character)bstack.pop();
						if(c=='}' && cx!='{' || 
								c==']' && cx!= '[' ||
								c==')' && cx != '('){
							System.out.println("Error, miss match: "+c+" at "+i);
							break;
						}
					}else{ //empty opening brackets, closing brackets match nothing
						System.out.println("Error, miss match: "+c+" at "+i);
					}
				default:
					break;
				}
		}
		if(!bstack.isEmpty())// still has the opening brackets not match
			System.out.println("Error, miss match ");
	}
	/*********************************************ENd Test****************************/
}
