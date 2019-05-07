package assignment3;

public class Controller {
	private Buffer2 buffer = new Buffer2();
	private GUISemaphore gui = new GUISemaphore();
	private Consumer ica;
	private Consumer coop;
	private Consumer cityGross;
	private Producer scan;
	private Producer arla;
	private Producer axFood;
	private int storageUsage = 0;

	private String[] arlaItemNames = { "Milk", "Ice cream", "Yoghurt", "Cottage cheese", "Cheese",
			"Butter", "Cream", "Cream cheese", "Sour cream", "Skyr",
			"Aged chees", "Kefir", "Goat milk", "Goat cheese", "Frozen yoghurt",
			"Eggnod", "Curd", "Condensed milk", "Chocolate milk", "ECO Butter"};

	private String[] scanItemNames = {"Bacon", "Meatballs", "Smoked ham", "Cooked ham", "Christmas ham", 
			"Smoked bacon", "Tenderloin", "Beef", "BBQ Bacon", "Spare ribs",
			"Ribs", "BBQ Steak", "Steak", "Pork", "Smoked pork",
			"liverwurst", "Salami", "Pepper salami", "Hot dogs", "Chorizo"};

	private String[] axFoodItemNames = {"Olive oil", "Pepsi", "Zingo", "7up", "Coca cola", 
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
		scan.initFoodItems(scanItemNames);
		scan.start();

		arla = new Producer("Arla", this, buffer);
		arla.initFoodItems(arlaItemNames);
		arla.start();

		axFood = new Producer("AxFood", this, buffer);
		axFood.initFoodItems(axFoodItemNames);
		axFood.start();
	}

	public void startScan() {
		scan.startProducing(true);
		gui.setStatusProducer("S", "Producing");
	}

	public void stopScan() {
		scan.startProducing(false);
		gui.setStatusProducer("S", "Stopped");
	}

	public void startArla() {
		arla.startProducing(true);
		gui.setStatusProducer("A", "Producing");
	}

	public void stopArla() {
		arla.startProducing(false);
		gui.setStatusProducer("A", "Stopped");
	}

	public void startAxFood() {
		axFood.startProducing(true);
		gui.setStatusProducer("X", "Producing");
	}

	public void stopAxFood() {
		axFood.startProducing(false);
		gui.setStatusProducer("X", "Stopped");
	}
	
	public void startIca() {
		ica.startConsuming(true);
	}
	
	public boolean getIcaCont() {
		return gui.getIcaCont();
	}
	
	public void stopIca() {
		ica.startConsuming(false);
	}
	
	public void startCoop() {
		coop.startConsuming(true);
	}
	
	public boolean getCoopCont() {
		return gui.getCoopCont();
	}
	
	public void stopCoop() {
		coop.startConsuming(false);
	}
	
	public void startCityGross() {
		cityGross.startConsuming(true);
	}
	
	public boolean getCGCont() {
		return gui.getCGCont();
	}
	
	public void stopCityGross() {
		cityGross.startConsuming(false);
	}

	public void updateProgressBar(int value) {
		storageUsage += value;
		gui.updateStatusBar(storageUsage);
	}
	
	public void updateIca(int items, int weight, int volume, String cargo) {
		gui.updateIca(items, weight, volume, cargo);
	}
	
	public void clearIca() {
		gui.clearIca();
	}
	
	public void updateCoop(int items, int weight, int volume, String cargo) {
		gui.updateCoop(items, weight, volume, cargo);
	}
	
	public void clearCoop() {
		gui.clearCoop();
	}
	
	public void updateCityGross(int items, int weight, int volume, String cargo) {
		gui.updateCityGross(items, weight, volume, cargo);
	}
	
	public void clearCityGross() {
		gui.clearCityGross();
	}
	
	public void setStatusConsumer(String consumer, String status) {
		gui.setStatusConsumer(consumer, status);
	}

}
