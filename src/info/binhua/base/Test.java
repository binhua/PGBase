package info.binhua.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Test {
	
	private static float ff = 1;
	
	static class Person{
		static String name;
		int age;
		
		public Person(String n, int a){
			this.name = n;
			this.age = a;
		}
		
		public static void getName(){
			name+="ff";
		}
	}
	
	public static void chaPerson(Person p, String n){
		p.name = n;
	}

	/**
	 * @param <T>
	 * @param args
	 */
	public static void main(String[] args) {

		/*Test t = new Test();
		
		Person p = new Person("hongbin", 25);
		System.out.println(p.name);
		chaPerson(p, "yanhua");
		System.out.println(p.name);
		
		Integer var1 = new Integer(1);
		Integer var2 = var1;
		
		System.out.println(var1.intValue());
		System.out.println(var1 == var2);*/
		
		
		
		//memory leak
		//OutOfMemoryError test
		/*List v = new ArrayList();
		for(int i=1; i<1000000000; i++){
			Object object = new Object();
			v.add(object);
			object = null;
		}*/
		
		System.out.println(4/3);
		System.out.println(4%3);
	}
	
	public static void doSomething(Integer var){
		var = new Integer(33);
	}

}
