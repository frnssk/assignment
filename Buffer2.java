package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Buffer2 {

	private LinkedList<FoodItem> buffer = new LinkedList<>();
	private Semaphore full = new Semaphore(0, true);
	private Semaphore empty = new Semaphore(50, true);
	private Semaphore lock = new Semaphore(1, true);

	public void add(FoodItem foodItem) {
		try {
			empty.acquire();
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		buffer.add(foodItem);

		lock.release();
		full.release();
	}
	
	public FoodItem remove() {
		try {
			full.acquire();
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		FoodItem item = buffer.remove(0);
		
		lock.release();
		empty.release();
		
		return item;
	}
}
