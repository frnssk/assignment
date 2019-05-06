package assignment3;

import java.util.Random;

public class Consumer extends Thread {
	private Controller controller;
	private Buffer buffer;
	private String name;

	private int weightLimit; 
	private int volumeLimit;
	private int maxItems;

	private int currentWeight = 0;
	private int currentVolume = 0;
	private int currentItems = 0;

	private volatile boolean consuming = false;

	public Consumer(String name, Controller controller, Buffer buffer) {
		this.name = name;
		this.controller = controller;
		this.buffer = buffer;
	}
	
	public String getConsumerName() {
		return name;
	}

	public void setLimits(int weight, int volume, int items) {
		weightLimit = weight;
		volumeLimit = volume;
		maxItems = items;	
	}
	
	public void clear() {
		currentWeight = 0;
		currentVolume = 0;
		currentItems = 0;
	}
	
	public void startConsuming(Boolean bool) {
		consuming = bool;
	}

	public void run() {
		System.out.println(getConsumerName() + " started. Consuming = " + consuming);
		while(true) {
			while(consuming) {
				System.out.println("Consuming start: " + getConsumerName());
				if(currentWeight < weightLimit && currentVolume < volumeLimit && currentItems < maxItems) {
					try {
						FoodItem item = buffer.remove();
						currentWeight += item.getWeight();
						currentVolume += item.getVolume();
						currentItems ++;
						controller.updateProgressBar(-1);
						
						if(getConsumerName().equals("Ica")) {
							controller.updateIca(currentItems, currentWeight, currentVolume);
						} else if(getConsumerName().equals("Coop")) {
							controller.updateCoop(currentItems, currentWeight, currentVolume);
						} else if(getConsumerName().equals("CityGross")) {
							controller.updateCityGross(currentItems, currentWeight, currentVolume);
						} 
						
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				} else {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					clear();
					if(getConsumerName().equals("Ica")) {
						controller.clearIca();
					} else if(getConsumerName().equals("Coop")) {
						controller.clearCoop();
					} else if(getConsumerName().equals("CityGross")) {
						controller.clearCityGross();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
