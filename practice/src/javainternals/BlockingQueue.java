package javainternals;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

	private List queue = new LinkedList<>();
	private int limit;

	public BlockingQueue(int limit) {
		this.limit = limit;

	}

	public synchronized void enque(Object ItemToAdd) throws InterruptedException {
		while (queue.size() == limit)
			wait();
		if (queue.size() == 0)
			notifyAll();
		queue.add(ItemToAdd);

	}
	
	public synchronized void deque(Object ItemToAdd) throws InterruptedException{
		while(queue.size()==0)
			wait();
		if(queue.size()==limit)
			notifyAll();
		queue.remove(0);
	}
	

}
