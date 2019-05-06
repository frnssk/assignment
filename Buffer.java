package assignment3;

import java.util.LinkedList;

public class Buffer {

	private LinkedList<FoodItem> buffer = new LinkedList<>();
	private int limit = 50;

	public synchronized void add(FoodItem foodItem) throws InterruptedException{
		while(buffer.size() == limit) {
			wait();
		}
		if(buffer.size() == 0) {
			notifyAll();
		}
		buffer.add(foodItem);
	}

	public synchronized FoodItem remove() throws InterruptedException {
		while(buffer.size() == 0) {
			wait();
		}
		if(buffer.size() == limit) {
			notifyAll();
		}

		return buffer.remove(0);
	}

}
