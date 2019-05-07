package assignment3;

import java.util.Random;

public class Consumer extends Thread {
	private Controller controller;
	private Buffer2 buffer;
	private String name;

	private int weightLimit; 
	private int volumeLimit;
	private int maxItems;

	private int currentWeight = 0;
	private int currentVolume = 0;
	private int currentItems = 0;

	private volatile boolean consuming = false;
	private volatile boolean cont = false;

	public Consumer(String name, Controller controller, Buffer2 buffer) {
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
		if(getConsumerName().equals("Ica")) {
			controller.clearIca();
		} else if(getConsumerName().equals("Coop")) {
			controller.clearCoop();
		} else if(getConsumerName().equals("CityGross")) {
			controller.clearCityGross();
		}
	}

	public void startConsuming(Boolean bool) {
		consuming = bool;
	}
	
	public void setCont(Boolean cont) {
		this.cont = cont;
	}

	public void run() {
		System.out.println(getConsumerName() + " started. Consuming = " + consuming);
		while(true) {

			while(consuming) {
				
					System.out.println("Cont in consumer = " + cont);
					if(currentWeight < weightLimit && currentVolume < volumeLimit && currentItems < maxItems) {
						FoodItem item = buffer.remove();
						currentWeight += item.getWeight();
						currentVolume += item.getVolume();
						currentItems ++;
						controller.updateProgressBar(-1);

						if(getConsumerName().equals("Ica")) {
							controller.updateIca(currentItems, currentWeight, currentVolume, item.getName());
							cont = controller.getIcaCont();
						} else if(getConsumerName().equals("Coop")) {
							controller.updateCoop(currentItems, currentWeight, currentVolume, item.getName());
							cont = controller.getCoopCont();
						} else if(getConsumerName().equals("CityGross")) {
							controller.updateCityGross(currentItems, currentWeight, currentVolume, item.getName());
							cont = controller.getCGCont();
						} 
						controller.setStatusConsumer(getConsumerName(), "Loading truck");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						controller.setStatusConsumer(getConsumerName(), "Truck is full");
						try {
							Thread.sleep(2000);
							controller.setStatusConsumer(getConsumerName(), "Emptying truck");
							Thread.sleep(2000);
							clear();
							controller.setStatusConsumer(getConsumerName(), "Truck is empty");
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(!cont) {
							consuming = false;
						}
					}
				}


		}
	}
}
