package javainternals;

public class SemaphoreTypes{
	
	public class Semaphore {
		  private boolean signal = false;

		  public synchronized void take() {
		    this.signal = true;
		    this.notify();
		  }

		  public synchronized void release() throws InterruptedException{
		    while(!this.signal) wait();
		    this.signal = false;
		  }

		}
	
	public class BoundedSemaphore {
		  private int signals = 0;
		  private int bound   = 0;

		  public BoundedSemaphore(int upperBound){
		    this.bound = upperBound;
		  }

		  public synchronized void take() throws InterruptedException{
		    while(this.signals == bound) wait();
		    this.signals++;
		    this.notify();
		  }

		  public synchronized void release() throws InterruptedException{
		    while(this.signals == 0) wait();
		    this.signals--;
		    this.notify();
		  }
		}
	public class CountingSemaphore {
		  private int signals = 0;

		  public synchronized void take() {
		    this.signals++;
		    this.notify();
		  }

		  public synchronized void release() throws InterruptedException{
		    while(this.signals == 0) wait();
		    this.signals--;
		  }

		}

}
