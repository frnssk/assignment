package assignment3;

public class FoodItem {
	private String name;
	private int volume;
	private int weight;
	
	public FoodItem(String name, int volume, int weight) {
		this.name = name;
		this.volume = volume;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
