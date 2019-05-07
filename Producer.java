package assignment3;

import java.util.Random;

public class Producer extends Thread {
	private FoodItem[] foodBuffer;
	private Controller controller;
	private Buffer2 buffer;
	private String name;
	private volatile boolean producing = false;
	Random rand = new Random();
	
	public Producer(String name, Controller controller, Buffer2 buffer) {
		this.name = name;
		this.controller = controller;
		this.buffer = buffer;
	}
	
	public String getProducerName() {
		return name;
	}
	
	public void initFoodItems(String[] name) {
		foodBuffer = new FoodItem[20];
		for(int i = 0; i < foodBuffer.length; i++) {
			foodBuffer[i] = new FoodItem(name[i], rand.nextInt(5) , rand.nextInt(10));
		}
	}
	
	public void startProducing(boolean bool) {
		this.producing = bool;
	}
	
	public void run() {
		System.out.println(getProducerName() + " started. Producing = " + producing);
		while(true) {
			while(producing) {
				
				try {
					FoodItem item = foodBuffer[rand.nextInt(20)];
					buffer.add(item);
					controller.updateProgressBar(1);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		}
	}
}
