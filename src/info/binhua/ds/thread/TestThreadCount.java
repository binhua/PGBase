package info.binhua.ds.thread;

public class TestThreadCount {

	public static void main(String[] args) {
		
		MCount count = new MCount(10);
		CountThread ct1 = new CountThread(count);
		CountThread ct2 = new CountThread(count);
		
		Thread t1 = new Thread(ct1);
		Thread t2 = new Thread(ct2);
		
		t1.start();
		t2.start();
	}
}

class MCount{
	private int n = 0;
	
	public MCount(int count){
		this.n = count;
	}
	
	public synchronized void sysOUt(){
	
		if(n>=0) {
			this.notifyAll();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"...."+n--);
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class CountThread implements Runnable{
	private MCount count;

	public CountThread(MCount count){
		this.count = count;
	}
	
	@Override
	public void run(){
		while(true) count.sysOUt();
	}
}