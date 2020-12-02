package ch3;

public class Apple {
	private Integer weight = 0;
	private Color color;
	private String country;


	public Apple(int weight, Color color) {
		this.weight = weight;
		this.color = color;
	}

	public Apple(Integer weight, Color color, String country) {
		this.weight = weight;
		this.color = color;
		this.country = country;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return String.format("ch2.Apple{color=%s, weight=%d}", color, weight);
	}
}
