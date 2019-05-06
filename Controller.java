package assignment3;

public class Controller {
	private Buffer buffer = new Buffer();
	private GUISemaphore gui = new GUISemaphore();
	private Consumer ica;
	private Consumer coop;
	private Consumer cityGross;
	private Producer scan;
	private Producer arla;
	private Producer axFood;
	private int storageUsage = 0;

	private String[] daryItems = { "Milk", "Ice cream", "Yoghurt", "Cottage cheese", "Cheese",
			"Butter", "Cream", "Cream cheese", "Sour cream", "Skyr",
			"Aged chees", "Kefir", "Goat milk", "Goat cheese", "Frozen yoghurt",
			"Eggnod", "Curd", "Condensed milk", "Chocolate milk", "ECO Butter"};

	private String[] meetItems = {"Bacon", "Meetbals", "Smoked ham", "Cooked ham", "Christmas ham", 
			"Smoked bacon", "Tenderloin", "Beef", "BBQ Bacon", "Spare ribs",
			"Ribs", "BBQ Steak", "Steak", "Pork", "Smoked pork",
			"liverwurst", "Salami", "Pepper salami", "Hot dogs", "Chorizo"};

	private String[] otherItems = {"Olive oil", "Pepsi", "Zingo", "7up", "Coca cola", 
			"Fanta", "Sprite", "Chips", "Tomatos", "Lemons", 
			"Rice", "Spagetti", "Onions", "Corn Flakes", "Bread",
			"Soy sauce", "Blueberrys", "Strawberrys", "Cookies", "Canned foods"};


	public Controller() {
		gui.setController(this);
		gui.Start();
		
		ica = new Consumer("Ica", this, buffer);
		ica.setLimits(50, 20, 20);
		ica.start();
		
		coop = new Consumer("Coop", this, buffer);
		coop.setLimits(30, 40, 50);
		coop.start();
		
		cityGross = new Consumer("CityGross", this, buffer);
		cityGross.setLimits(50, 40, 30);
		cityGross.start();

		scan = new Producer("Scan", this, buffer);
		scan.initFoodItems(meetItems);
		scan.start();

		arla = new Producer("Arla", this, buffer);
		arla.initFoodItems(daryItems);
		arla.start();

		axFood = new Producer("Axfood", this, buffer);
		axFood.initFoodItems(otherItems);
		axFood.start();
	}

	public void startScan() {
		scan.startProducing(true);
	}

	public void stopScan() {
		scan.startProducing(false);
	}

	public void startArla() {
		arla.startProducing(true);
	}

	public void stopArla() {
		arla.startProducing(false);
	}

	public void startAxFood() {
		axFood.startProducing(true);
	}

	public void stopAxFood() {
		axFood.startProducing(false);
	}
	
	public void startIca() {
		ica.startConsuming(true);
	}
	
	public void stopIca() {
		ica.startConsuming(false);
	}
	
	public void startCoop() {
		coop.startConsuming(true);
	}
	
	public void stopCoop() {
		coop.startConsuming(false);
	}
	
	public void startCityGross() {
		cityGross.startConsuming(true);
	}
	
	public void stopCityGross() {
		cityGross.startConsuming(false);
	}

	public void updateProgressBar(int value) {
		storageUsage += value;
		gui.updateStatusBar(storageUsage);
	}
	
	public void updateIca(int items, int weight, int volume) {
		gui.updateIca(items, weight, volume);
	}
	
	public void clearIca() {
		gui.clearIca();
	}
	
	public void updateCoop(int items, int weight, int volume) {
		gui.updateCoop(items, weight, volume);
	}
	
	public void clearCoop() {
		gui.clearCoop();
	}
	
	public void updateCityGross(int items, int weight, int volume) {
		gui.updateCityGross(items, weight, volume);
	}
	
	public void clearCityGross() {
		gui.clearCityGross();
	}

}
