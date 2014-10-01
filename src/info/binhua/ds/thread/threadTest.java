package info.binhua.ds.thread;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public  class threadTest implements Runnable {
	@Override
	public void run() {
		synchronized (this) {
			 test();	
			 notify();
		}
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
		
       
	}
	
	public static synchronized void test(){
		
		for(int i=0;i<20;i++)
		System.out.println(Thread.currentThread().getName()+"    runnaable 1 test");	
		
	}
	
	public static void main(String[] args) {
		
		/*Lock lock = new ReentrantLock();
		threadTest td=new threadTest();
		//threadTest td1=new threadTest();
		new Thread(td).start();
		//Thread.yield();
		new Thread(td).start();
		ExecutorService pool = Executors.newScheduledThreadPool(2);
		calltest ca = new calltest();
		java.util.concurrent.Future<String>  tca =pool.submit(ca);
		thtest tt= new thtest();
		tt.setPriority(2);		
		tt.start();
		try {
			tt.join();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		td.run();
		
		
		thread t = new thread();
		
		t.start();
		synchronized (t) {
			try {
				t.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(t.total);
		}*/
		count c = new count();
		countth1 a1 = new countth1(c);
		countth2 a2 = new countth2(c);
		a1.start();	
		a2.start();
		
	}

}
    class calltest implements Callable<String>{
    	public String call() throws Exception {
    		
    		return "test";
    		
    	};
    	
    }


	class thtest extends Thread{
		@Override
		public void run() {		
			
			// TODO Auto-generated method stub
			for(int i=0;i<5;i++)
			System.out.println(Thread.currentThread().getName()+"   thread 1  test");
			notify();
		}
	}
	
	class thread extends Thread{
		int total;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (this) {
					
				for(int i=0;i<100;i++){
					total+=i;
				}
				notify();
			}
		}
	}
	
	class count {
		public  static int i=0;
		public  static int[] a = {0,1,2,3,4,5,6,7,8,9};
		public count(){	
		}
		
		public synchronized void countPrint(){			
			System.out.println(Thread.currentThread().getName()+a[i]);
			i++;
		}
	}
	class countth1 extends Thread {
		private count test;
		public countth1(count c) {
			// TODO Auto-generated constructor stub
			this.test=c;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated method stub
			synchronized (test) {
				for(int j=0;j<=9;j++){
					j++;
					System.out.println(Thread.currentThread().getName()+test.a[j]);
					
					test.notifyAll();					
					try {						
						test.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	class countth2 extends Thread {
		private count test;
		public countth2(count c) {
			// TODO Auto-generated constructor stub
			this.test=c;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (test) {	
			
				for(int j=1;j<=9;j++){
					
					try {					
						test.wait();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					j++;
					System.out.println(Thread.currentThread().getName()+test.a[j]);
					test.notifyAll();
				}
			}
		}
	}
